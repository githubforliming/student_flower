package com.example.student_flower;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest - to test only web layer , that is the controller only, and mock service layer
//@DataJpaTest, @DataJdbcTest - to test only DAO layer

@SpringBootTest //loads complete application context - for integration test
@AutoConfigureMockMvc
class TestWithMockMvc {

	@Autowired
	protected MockMvc mockMvc;

	
	@Test
	void testGet()   {
		System.out.println("mockMvc="+mockMvc);
	}

}
