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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class DroolsTestQueryApplicationTests {

    @Autowired
    KieBase kieBase;

    // 测试
    @Test
    public void droolsTest(){
        System.out.println("开始执行--------------");
        KieSession kieSession = kieBase.newKieSession();
        TestQueryEntity test01 = TestQueryEntity.builder().name("张三").age(10).build();
        TestQueryEntity test02 = TestQueryEntity.builder().name("李四").age(18).build();
        // 1-规则引擎处理逻辑
        kieSession.insert(test01);
        kieSession.insert(test02);

        QueryResults queryReesult1 = kieSession.getQueryResults("query_1");
        QueryResults queryReesult2 = kieSession.getQueryResults("query_2", "张三", 10);
        for (QueryResultsRow queryResultsRow : queryReesult1) {
            TestQueryEntity test = (TestQueryEntity)queryResultsRow.get("$test");
            System.out.println("query_1结果："+test);
        }
        for (QueryResultsRow queryResultsRow : queryReesult2) {
            TestQueryEntity test = (TestQueryEntity)queryResultsRow.get("$test");
            System.out.println("query_2结果："+test);
        }

        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("query"));
        kieSession.dispose();
    }


}
