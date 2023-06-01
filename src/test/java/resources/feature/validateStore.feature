Feature: (e2e) Validate store
  @addPurchase
  Scenario: (e2e) Post purchasing the pet
    Given the user add new purchasing with post-petition
    Then returns 200 ok add purchasing

  @putRemovePurchase
  Scenario: (e2e) Remove a exists
    Given the user add new purchasing with post-petition
    When the user remove purchasing with post-petition
    Then returns 200 ok remove purchasing