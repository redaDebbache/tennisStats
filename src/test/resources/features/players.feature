Feature: Player functionnalities

  @Nominal
  Scenario: Get all players
    When The user requests all players
    Then The user receives a 200 response
    And A valid list of players is returned

  @Nominal
  Scenario: Get a single player identified by id
    When The user requests a player having id 54
    Then The user receives a 200 response
    And A valid player having id 54 is returned

  @Fail
  Scenario: Get a single player identified by id
    When The user requests a player having id 9999
    Then The user receives a 404 response
    And An error message containing "Player having id 9999 not found."
