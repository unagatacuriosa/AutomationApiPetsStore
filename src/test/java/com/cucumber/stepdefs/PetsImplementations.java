package com.cucumber.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;

public class PetsImplementations {
    private Response petResponse;
    private Response deletePet;

    @Given("the user add a new pet with post petition")
    public void postAddPet() {
        File file = new File("src/test/java/resources/data/bodyRequestPets.json");
        System.out.println(file);
        petResponse = given().contentType(ContentType.JSON).body(file).post("https://petstore.swagger.io/v2/pet");
        System.out.println("postAddPet ok");
    }

    @And("returns 200")
    public void validatePetition() {
        petResponse.statusCode();
        assertEquals(200, petResponse.getStatusCode());
        System.out.println("validate 200 ok");
    }

    @Given("the user update a name of pet with put petition")
    public void theUserUpdateANameOfPetWithPutPetition() {
        File file = new File("src/test/java/resources/data/putBodyRequestPets.json");
        petResponse = given().contentType(ContentType.JSON).body(file).post("https://petstore.swagger.io/v2/pet");
        System.out.println("update name ok");
    }

    @Then("validate the update name of the pet")
    public void validateTheUpdateNameOfThePet() {
        petResponse.then().body("name",equalTo("cambiado"));
        System.out.println("validate name ok");
    }

    @Given("the user remove a pet with delete petition")
    public void theUserRemoveAPetWithDeletePetition() {
        String petId = "1999";
        deletePet = given().log().all().delete("https://petstore.swagger.io/v2/pet/" + petId);
        System.out.println("delete pet ok");
    }
}
