Feature: GETPostsBDDStyled
  Verify different GET operations using REST-assured for simpleBDDStyled


  Scenario: Verify one author of the post for simpleBDDStyled
    Given I perform GET operation for simpleBDDStyled
    And I perform GET for the post number "1"
    Then I should see the author name for simpleBDDStyled

  Scenario: Verify collection of authors in the post for simpleBDDStyled
    Given I perform GET operation for simpleBDDStyled
    Then I should see the author names for simpleBDDStyled

  Scenario: Verify Parameter of Get PathParameter for simpleBDDStyled
    Given I perform GET operation for simpleBDDStyled
    Then I should see verify GET PathParameter

  Scenario: Verify Parameter of Get QueryParameter for simpleBDDStyled
    Given I perform GET operation for simpleBDDStyled
    Then I should see verify GET QueryParameter

  Scenario: Verify Parameter of POST WithBodyParameter for simpleBDDStyled
    Given I perform GET operation for simpleBDDStyled
    Then I should see verify POST WithBodyParameter

 