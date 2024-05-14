package com.example.demo;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
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
	@Container
	protected static KafkaContainer kafka =
			new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

	@Test
	void contextLoads() {
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		mongoDBContainer.start();
		kafka.start();
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
		registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
		registry.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");
	}

}
