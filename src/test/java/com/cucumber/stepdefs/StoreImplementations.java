package com.cucumber.stepdefs;

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
    @Given("the user add new purchasing with post-petition")
    public void postAddPurchase() {
        File file = new File("src/test/java/resources/data/bodyRequestStore.json");
        storeResponse = given().contentType(ContentType.JSON).body(file).post("https://petstore.swagger.io/v2/store/order");
    }

    @Then("returns {int} ok add purchasing")
    public void returnsOkAddPurchasing(int arg0) {
        storeResponse.statusCode();
        assertEquals(200, storeResponse.getStatusCode());
        storeResponse.then().body("status", equalTo("placed"));
    }

    @When("the user remove purchasing with post-petition")
    public void theUserRemovePurchasingWithPostPetition() {
        String orderId = "1999";
        deleteOrder = given().log().all().delete("https://petstore.swagger.io/v2/store/order/" + orderId);
    }

    @Then("returns {int} ok remove purchasing")
    public void returnsOkRemovePurchasing(int arg0) {
        storeResponse.statusCode();
        assertEquals(200, storeResponse.getStatusCode());
    }
}
