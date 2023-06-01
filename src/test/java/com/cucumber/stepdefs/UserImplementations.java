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
    @Given("the user create a new user")
    public void postAddPet() {
        File file = new File("src/test/java/resources/data/bodyRequestUser.json");
        userResponse = given().contentType(ContentType.JSON).body(file).post("https://petstore.swagger.io/v2/user");
    }

    @Then("returns {int} ok and create user")
    public void returnsOkAndCreateUser(int arg0) {
        userResponse.statusCode();
        assertEquals(200, userResponse.getStatusCode());
    }

    @When("the user update username")
    public void theUserUpdateUsername() {
        File file = new File("src/test/java/resources/data/putBodyRequestUsers.json");
        userResponse = given().contentType(ContentType.JSON).body(file).put("https://petstore.swagger.io/v2/user/" + username);
    }

    @Then("returns {int} and validate the username")
    public void returnsAndValidateTheUsername(int arg0) {
        userResponse.statusCode();
        assertEquals(200, userResponse.getStatusCode());
    }

    @And("the user remove user")
    public void theUserRemoveUser() {
        deleteUser = given().log().all().delete("https://petstore.swagger.io/v2/user/" + username);
    }

    @Given("the create an array with a list of user")
    public void theCreateAnArrayWithAListOfUser() {
        File file = new File("src/test/java/resources/data/bodyRequestUserArray.json");
        userResponse = given().contentType(ContentType.JSON).body(file).post("https://petstore.swagger.io/v2/user/createWithArray");
    }

    @Then("returns {int} remove user")
    public void returnsRemoveUser(int arg0) {
        userResponse.statusCode();
        assertEquals(200, userResponse.getStatusCode());
    }
}
