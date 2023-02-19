package com.example.droolstest02;

import com.example.droolstest02.entity.TestNoneEntity;
import com.example.droolstest02.entity.TestQueryEntity;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DroolsTestFunctionApplicationTests {

    @Autowired
    KieBase kieBase;

    // 测试
    @Test
    public void droolsTest(){
        System.out.println("开始执行--------------");
        KieSession kieSession = kieBase.newKieSession();
        TestNoneEntity test01 = TestNoneEntity.builder().str("穿衣服").build();
        // 1-规则引擎处理逻辑
        kieSession.insert(test01);
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("function"));
        kieSession.dispose();
    }


}
