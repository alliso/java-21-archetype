package com.example.demo.cucumber;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.docstring.DocString;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

public class HttpUtils {
  @LocalServerPort private int port;
  private Response response;

  @When("I do a GET request to {}")
  public void i_do_a_get_request_to(String uri) {
    response = given().port(port).get(uri);
  }

  @When("I do a POST request to {} with body:")
  public void i_do_a_post_request_to(String uri, DocString body) {
    response = given().contentType("application/json").body(body.getContent()).port(port).post(uri);
  }

  @Then("I receive status code {int}")
  public void i_receive_status_code(int statusCode) {
    assertEquals(statusCode, response.statusCode());
  }

  @Then("{} list is not empty")
  public void list_not_empty(String nameList) throws JsonProcessingException {
    List<Object> list = response.jsonPath().getList(nameList);
    assertThat(list).isNotEmpty();
  }
}
