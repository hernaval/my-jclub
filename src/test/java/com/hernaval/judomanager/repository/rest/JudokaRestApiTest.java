package com.hernaval.judomanager.repository.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class JudokaRestApiTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test 
	void saveJudoKa() throws Exception {
		mockMvc.perform(get("/judokas")
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk());
				
	}
}
