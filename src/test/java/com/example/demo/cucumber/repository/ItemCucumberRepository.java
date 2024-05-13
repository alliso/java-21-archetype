package com.example.demo.cucumber.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.example.demo.infrastructure.mongo.repository.dbo.ItemMongoRepositoryDBO;
import io.cucumber.docstring.DocString;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
public class ItemCucumberRepository {
  private final ItemMongoRepositoryDBO itemMongoRepositoryDBO;
  private final MongoTemplate mongoTemplate;

  @Given("I store in {string} db")
  public void storeItem(String collection, DocString docString) throws IOException {
    mongoTemplate.save(docString.getContent(), collection);
  }

  @Then("Item with name {string} is stored in db")
  public void itemWithNameIsStored(String name) {
    await()
        .pollInterval(3, TimeUnit.SECONDS)
        .atMost(10, TimeUnit.SECONDS)
        .untilAsserted(() -> assertThat(itemMongoRepositoryDBO.findByName(name)).isPresent());
  }

}
