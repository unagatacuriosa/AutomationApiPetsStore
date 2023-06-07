package com.cucumber.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class StoreImplementations {
    private Response storeResponse;
    private Response deleteOrder;

    @Before
    public void setUp() {
        String baseUri = "https://petstore.swagger.io/v2";
    }

    @Given("the user add new purchasing with post-petition")
    public void postAddPurchase(String baseUri) {
        File file = new File("src/test/java/resources/data/bodyRequestStore.json");
        storeResponse = given().contentType(ContentType.JSON).body(file).post(baseUri + "/store/order");
    }

    @Then("returns 200 ok add purchasing")
    public void returnsOkAddPurchasing() {
        storeResponse.statusCode();
        assertEquals(200, storeResponse.getStatusCode());
        storeResponse.then().body("status", equalTo("placed"));
    }

    @When("the user remove purchasing with post-petition")
    public void theUserRemovePurchasingWithPostPetition(String baseUri) {
        String orderId = "1999";
        deleteOrder = given().log().all().delete(baseUri + "/store/order/" + orderId);
    }

    @Then("returns 200 ok remove purchasing")
    public void returnsOkRemovePurchasing() {
        storeResponse.statusCode();
        assertEquals(200, storeResponse.getStatusCode());
    }
}
