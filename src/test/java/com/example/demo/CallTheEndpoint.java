package com.example.demo;

import com.example.demo.infrastructure.api.v1.ItemController;
import com.example.demo.infrastructure.mongo.model.ItemDBO;
import com.example.demo.infrastructure.mongo.repository.dbo.ItemMongoRepositoryDBO;
import io.cucumber.docstring.DocString;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class CallTheEndpoint {
    private final ItemController itemController;
    private final ItemMongoRepositoryDBO itemMongoRepositoryDBO;

    @When("I call the endpoint:")
    public void  iCallTheEndpoint(DocString doc) throws Throwable{
        ItemDBO dbo = new ObjectMapper().readValue(doc.getContent(), ItemDBO.class);
        //itemController.createItem(dbo);
    }

    @Then("I should receive status {}")
    public void  iShouldReceiveStatus(String status){
        assertThat(itemMongoRepositoryDBO.findByName(status)).isPresent();
    }
}
