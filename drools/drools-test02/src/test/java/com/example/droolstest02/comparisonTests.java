/*
package com.example.droolstest02;

import com.example.droolstest02.entity.TestEntity;
import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ComparisonTests {

    @Autowired
    KieBase kieBase;

    // 测试
    @Test
    public void Test() throws Exception {
        KieSession kieSession = kieBase.newKieSession();
        List<TestEntity> list = getInitData();
        System.out.println("==============开始匹配===================");
        for (TestEntity testEntity: list) {
            // 1-规则引擎处理逻辑
            kieSession.insert(testEntity);
            // 指定某个规则
            kieSession.fireAllRules(
                    new RuleNameEqualsAgendaFilter("rule_test_contains")
            );
            kieSession.fireUntilHalt();
        }
        System.out.println("==============匹配结束===================");
        kieSession.dispose();
    }



    // 初始化数据（模拟数据库获取的数据）
    private static List<TestEntity> getInitData(){
        List<TestEntity> list = new ArrayList<>();
        //
        {
            TestEntity entity = TestEntity.builder()
                    .names("张三")
                    .list(Arrays.asList("张三", "李四"))
                    .arr(new String[]{"张三", "王五"})
                    .build();
            list.add(entity);
        }
        {
            TestEntity entity = TestEntity.builder()
                    .names("诸葛十三")
                    .list(Arrays.asList("诸葛十四", "欧阳哇哇"))
                    .arr(new String[]{"诸葛十四", "欧阳哇哇"})
                    .build();
            list.add(entity);
        }
        return list;
    }

}
*/
