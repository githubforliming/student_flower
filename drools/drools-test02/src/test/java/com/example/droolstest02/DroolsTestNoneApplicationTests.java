package com.example.droolstest02;

import com.example.droolstest02.entity.TestNoneEntity;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DroolsTestNoneApplicationTests {

    @Autowired
    KieBase kieBase;

    // 测试
    @Test
    public void droolsTest(){
        KieSession kieSession = kieBase.newKieSession();
        TestNoneEntity test01 = TestNoneEntity.builder().i(18).build();
        // 1-规则引擎处理逻辑
        kieSession.insert(test01);
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("rhsplus_getworkingmemory"));
        kieSession.dispose();
    }
}
