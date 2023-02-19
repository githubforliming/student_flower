package com.example.droolstest02.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author 木子的昼夜编程
 */
@Configuration
public class DroolsAutoConfiguration {
    private static final String RULES_PATH = "rules/";

    // 注入KieServices
    @Bean
    @ConditionalOnMissingBean(KieServices.class)
    public KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    // 加载规则文件使用
    // 注意：一般而言，你放到resource下边的规则文件，每当执行一个规则匹配的时候，他会全部的扫描一遍。
    // 而不是每一个文件写一个kieFileSystem来加载
    @Bean
    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }
    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }

    // 注入KieContainer
    @Bean
    @ConditionalOnMissingBean(KieContainer.class)
    public KieContainer kieContainer() throws IOException {
        final KieRepository kieRepository = getKieServices().getRepository();
        kieRepository.addKieModule(() -> kieRepository.getDefaultReleaseId());
        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        KieContainer kieContainer = getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
        return kieContainer;
    }

    // 注入 KieBase
    @Bean
    @ConditionalOnMissingBean(KieBase.class)
    public KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }
}
