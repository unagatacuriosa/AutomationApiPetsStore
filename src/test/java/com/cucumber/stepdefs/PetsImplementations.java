package com.cucumber.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PetsImplementations {
    private Response petResponse;
    private Response deletePet;

    public void setUp() {
        String baseUri = "https://petstore.swagger.io/v2";
    }

    @Given("the user add a new pet with post petition")
    public void postAddPet(String baseUri) {
        File file = new File("src/test/java/resources/data/bodyRequestPets.json");
        petResponse = given().contentType(ContentType.JSON).body(file).post(baseUri + "/pet");
    }

    @And("returns 200")
    public void validatePetition() {
        petResponse.statusCode();
        assertEquals(200, petResponse.getStatusCode());
    }

    @Given("the user update a name of pet with put petition")
    public void theUserUpdateANameOfPetWithPutPetition(String baseUri) {
        File file = new File("src/test/java/resources/data/putBodyRequestPets.json");
        petResponse = given().contentType(ContentType.JSON).body(file).put(baseUri + "/pet");
    }

    @Then("validate the update name of the pet")
    public void validateTheUpdateNameOfThePet() {
        petResponse.then().body("name", equalTo("cambiado"));
    }

    @Given("the user remove a pet with delete petition")
    public void theUserRemoveAPetWithDeletePetition(String baseUri) {
        String petId = "1999";
        deletePet = given().log().all().delete(baseUri + "/pet/" + petId);
    }
}
