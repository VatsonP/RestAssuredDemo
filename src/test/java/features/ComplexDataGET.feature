Feature: ComplexDataGet
  Verify complex data


#  @smoke
#  Scenario: Verify GET operation for complex data
#    Given I perform authentication operation for "/auth/login" with body
#      | email              | password  |
#      | karthik@email.com | haha123 |
#    And I perform GET operation with path parameter for address "/address/"
#      | id |
#      | 1      |
#    Then I should see the street name as "1st street"

  @smoke
  Scenario: Verify GET operation for complex data
    Given I perform authentication operation for "/auth/login" with body
      | email              | password |
      | karthik@gmail.com  | haha123  |
    And I perform GET operation with with queryParam for address "/location/"
      | id   |
      | 1    |
    Then I should see the street name as "1st street"

  @smoke
  Scenario: V2 Verify GET operation for complex data
    Given V2 I perform authentication operation for "/auth/login" with body
      | email              | password |
      | karthik@gmail.com  | haha123  |
    And V2 I perform GET operation with with queryParam for address "/location/"
      | id   |
      | 1    |
    Then V2 I should see the street name as "1st street"
