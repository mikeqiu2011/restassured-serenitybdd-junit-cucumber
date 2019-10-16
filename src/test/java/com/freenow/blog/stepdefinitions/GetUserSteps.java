package com.freenow.blog.stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import com.freenow.blog.users.UserCalls;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;


public class GetUserSteps {

  Response response;

  @When("I call the get user details endpoint for user \"(.*)\"")
  public void i_call_the_get_user_endpoint_for_user(String user) {
    response = UserCalls.getUserDetails(user);
  }

  @Then("user details should be retrieved")
  public void user_details_should_be_retrieved() {
    assertThat(response.statusCode()).as("Response code should be 200").isEqualTo(200);
    assertThat(response.getBody().jsonPath().getList("id").get(0)).as("userid should be present").isNotNull();
  }

  @And("username should be \"(.*)\"")
  public void usernameShouldBe(String userName) {
    assertThat(response.getBody().jsonPath().getList("username").get(0).toString())
        .as("User name should be equal to " + userName).isEqualToIgnoringCase(userName);
  }

  @Then("empty response should be returned")
  public void empty_response_should_be_returned() {
    assertThat(response.statusCode()).as("Response code should be 200").isEqualTo(200);
    assertThat(response.getBody().jsonPath().getList("").size()).as("Response should be empty")
        .isEqualTo(0);
  }
}
