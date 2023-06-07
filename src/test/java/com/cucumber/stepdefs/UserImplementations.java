package com.cucumber.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class UserImplementations {
    private Response userResponse;
    private Response deleteUser;
    private String username = "gabiqa";

    public void setUp() {
        String baseUri = "https://petstore.swagger.io/v2";
    }

    @Given("the user create a new user")
    public void postAddPet(String baseUri) {
        File file = new File("src/test/java/resources/data/bodyRequestUser.json");
        userResponse = given().contentType(ContentType.JSON).body(file).post(baseUri + "/user");
    }

    @Then("returns 200 ok and create user")
    public void returnsOkAndCreateUser() {
        userResponse.statusCode();
        assertEquals(200, userResponse.getStatusCode());
    }

    @When("the user update username")
    public void theUserUpdateUsername(String baseUri) {
        File file = new File("src/test/java/resources/data/putBodyRequestUsers.json");
        userResponse = given().contentType(ContentType.JSON).body(file).put(baseUri + "/user/" + username);
    }

    @Then("returns 200 and validate the username")
    public void returnsAndValidateTheUsername() {
        userResponse.statusCode();
        assertEquals(200, userResponse.getStatusCode());
    }

    @And("the user remove user")
    public void theUserRemoveUser(String baseUri) {
        deleteUser = given().log().all().delete(baseUri + "/user/" + username);
    }

    @Given("the create an array with a list of user")
    public void theCreateAnArrayWithAListOfUser(String baseUri) {
        File file = new File("src/test/java/resources/data/bodyRequestUserArray.json");
        userResponse = given().contentType(ContentType.JSON).body(file).post(baseUri + "/user/createWithArray");
    }

    @Then("returns 200 remove user")
    public void returnsRemoveUser() {
        userResponse.statusCode();
        assertEquals(200, userResponse.getStatusCode());
    }
}
