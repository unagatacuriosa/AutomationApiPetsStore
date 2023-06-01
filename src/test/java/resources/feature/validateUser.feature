Feature: (e2e) Validate users
  @addUser
  Scenario: (e2e) Post create a user
  Given the user create a new user
  Then returns 200 ok and create user

  @putUpdateUser
  Scenario: (e2e) Put update a user
    Given the user create a new user
    When the user update username
    Then returns 200 and validate the username

  @deleteUser
  Scenario: (e2e) Remove a user
    Given the user create a new user
    And the user remove user
    Then returns 200

  @addUserArray
  Scenario: (e2e) Put update a user
    Given the create an array with a list of user
    When make a post-petition
    And returns 200 ok add pet
    Then compare expected array create vs original array