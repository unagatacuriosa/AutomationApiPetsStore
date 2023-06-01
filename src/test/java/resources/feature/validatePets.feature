Feature: (e2e) Validate pets
  @addPet
  Scenario: (e2e) Post add a new pet to the store
  Given the user add a new pet with post petition
  Then returns 200

  @putUpdatePet
  Scenario: (e2e) Put update a exists pet
    Given the user add a new pet with post petition
    And the user update a name of pet with put petition
    When returns 200
    Then validate the update name of the pet

  @deletePet
  Scenario: (e2e) Remove a exists pet
    Given the user add a new pet with post petition
    And the user remove a pet with delete petition
    Then returns 200