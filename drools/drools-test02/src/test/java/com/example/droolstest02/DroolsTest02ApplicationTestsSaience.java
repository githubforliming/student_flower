package com.example.droolstest02;

import com.example.droolstest02.entity.Order;
import com.example.droolstest02.entity.SalienceEntity;
import com.example.droolstest02.entity.User;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DroolsTest02ApplicationTestsSaience {

    @Autowired
    KieBase kieBase;

    // 测试
    @Test
    public void droolsOrderTest() throws Exception {
        KieSession kieSession = kieBase.newKieSession();
        SalienceEntity entity = new SalienceEntity();
        entity.setYesOrNo(true);
        // 1-规则引擎处理逻辑
        kieSession.insert(entity);
        kieSession.fireAllRules();
        kieSession.dispose();
    }



}
