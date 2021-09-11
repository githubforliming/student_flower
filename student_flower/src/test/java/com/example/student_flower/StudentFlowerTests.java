package com.example.student_flower;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.TimeUnit;


@SpringBootTest
@AutoConfigureMockMvc
class StudentFlowerTests {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void sendFlower() throws Exception {
        final Long classroomId = 7L;
        final Long studengId = 102L;

        Thread thread1 = new Thread(() -> {
            try {
                mockMvc.perform(MockMvcRequestBuilders.get("/test/sendflower/" + classroomId + "/" + studengId).accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                mockMvc.perform(MockMvcRequestBuilders.get("/test/sendflower/" + classroomId + "/" + studengId).accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                mockMvc.perform(MockMvcRequestBuilders.get("/test/sendflower/" + classroomId + "/" + studengId).accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        // 睡会儿 等三个线程跑完 很low? 做测试凑活用吧
        Thread.sleep(TimeUnit.SECONDS.toMillis(20));
    }
}
