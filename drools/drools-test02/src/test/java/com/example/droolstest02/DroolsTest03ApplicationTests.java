package com.example.droolstest02;

import com.example.droolstest02.entity.EnableEntity;
import com.example.droolstest02.entity.TestEntity;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DroolsTest03ApplicationTests {

    @Autowired
    KieBase kieBase;

    // 测试
    @Test
    public void droolsOrderTest() throws Exception {
        KieSession kieSession = kieBase.newKieSession();
        EnableEntity test = EnableEntity.builder().name("诸葛十三").build();
        // 1-规则引擎处理逻辑
        kieSession.insert(test);
        kieSession.fireAllRules();
        kieSession.dispose();
    }


}
