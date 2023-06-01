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

    @Given("the user add a new pet with post petition")
    public void postAddPet() {
        File file = new File("src/test/java/resources/data/bodyRequestPets.json");
        petResponse = given().contentType(ContentType.JSON).body(file).post("https://petstore.swagger.io/v2/pet");
    }

    @And("returns {int}")
    public void validatePetition(int arg0) {
        petResponse.statusCode();
        assertEquals(200, petResponse.getStatusCode());
    }

    @Given("the user update a name of pet with put petition")
    public void theUserUpdateANameOfPetWithPutPetition() {
        File file = new File("src/test/java/resources/data/putBodyRequestPets.json");
        petResponse = given().contentType(ContentType.JSON).body(file).put("https://petstore.swagger.io/v2/pet");
    }

    @Then("validate the update name of the pet")
    public void validateTheUpdateNameOfThePet() {
        petResponse.then().body("name", equalTo("cambiado"));
    }

    @Given("the user remove a pet with delete petition")
    public void theUserRemoveAPetWithDeletePetition() {
        String petId = "1999";
        deletePet = given().log().all().delete("https://petstore.swagger.io/v2/pet/" + petId);
    }
}
