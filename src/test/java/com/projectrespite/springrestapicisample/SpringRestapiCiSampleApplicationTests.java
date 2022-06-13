package com.projectrespite.springrestapicisample;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Testcontainers
public class SpringRestapiCiSampleApplicationTests {

	@Autowired
	public MockMvc mockMvc;
	@Container
	private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres"))
			.withUsername("devuser")
			.withPassword("devuser")
			.withDatabaseName("devdb")
			.withInitScript("create_table.sql");

	@DynamicPropertySource
	static void setup(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
	}

	@Test
	void contextLoads() {

		Assertions.assertThat(postgres.isRunning()).isTrue();
		System.out.println(postgres.getJdbcUrl());

		try {
			MvcResult result = mockMvc.perform(get("/"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().json("[{\"id\":1,\"firstName\":\"Daiki\",\"lastName\":\"Kawanuma\",\"fullName\":\"Daiki Kawanuma\",\"age\":30}]", true))
					.andReturn();

			System.out.println(result.getResponse().getContentAsString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
