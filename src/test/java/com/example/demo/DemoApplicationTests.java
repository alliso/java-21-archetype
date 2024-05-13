package com.example.demo;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@Testcontainers
class DemoApplicationTests {
	@Container
	protected static MongoDBContainer mongoDBContainer =
			new MongoDBContainer(DockerImageName.parse("mongo:6")).withReuse(true);

	@Test
	void contextLoads() {
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		mongoDBContainer.start();
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

}
