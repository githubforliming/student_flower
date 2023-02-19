package com.example.droolstest02;

import com.example.droolstest02.entity.EnableEntity;
import com.example.droolstest02.entity.TestNoneEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
class DroolsTest04ApplicationTests {

    @Autowired
    KieBase kieBase;

    // 测试
    @Test
    public void droolsTest(){
        System.out.println("开始执行时间："+ LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        KieSession kieSession = kieBase.newKieSession();
        TestNoneEntity test = TestNoneEntity.builder().str("global").build();
        // 全局变量
        int count = 100;
        List<String> data = new ArrayList<>();
        data.add("001");
        data.add("002");
        kieSession.setGlobal("count", count);
        kieSession.setGlobal("data", data);
        // 1-规则引擎处理逻辑
        kieSession.insert(test);
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("global"));
        kieSession.dispose();

        log.info("最后输出,count:{}", count);
        log.info("最后输出,data:{}", data);
    }


}
