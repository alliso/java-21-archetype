package com.example.demo.cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.example.demo.infrastructure.mongo.repository.dbo.ItemMongoRepositoryDBO;
import io.cucumber.docstring.DocString;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

public class RepositoryUtils {
  private final ItemMongoRepositoryDBO itemMongoRepositoryDBO;
  private final MongoTemplate mongoTemplate;

  public RepositoryUtils(ItemMongoRepositoryDBO itemMongoRepositoryDBO, MongoTemplate mongoTemplate) {
    this.itemMongoRepositoryDBO = itemMongoRepositoryDBO;
    this.mongoTemplate = mongoTemplate;
  }

  @Given("I store in {string} db")
  public void storeItem(String collection, DocString docString) throws IOException {
    mongoTemplate.save(docString.getContent(), collection);
  }

  @Then("Document is stored in collection {string}")
  public void itemIsStoredInCollection(String collection, DocString docString) {
    Query query = new BasicQuery(docString.getContent());

    await()
        .pollInterval(3, TimeUnit.SECONDS)
        .atMost(10, TimeUnit.SECONDS)
        .untilAsserted(() -> assertTrue(mongoTemplate.exists(query, collection)));
  }
}
