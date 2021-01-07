Feature: GETPostsAuth
  Verify different GET operations with authentication using REST-assured library


  @smoke
  Scenario: Verify GET operation with bearer authentication token
    Given I perform authentication operation for "/auth/login" with body
      | email              | password  |
      | karthik@gmail.com  | haha123 |
    Given I perform GET operation with token for "/posts/1"
    Then I should check the author name as "Karthik KK"

  @smoke
  Scenario: Verify GET operation with json validation
    Given I perform authentication operation for "/auth/login" with body
      | email              | password  |
      | karthik@gmail.com  | haha123 |
    Given I perform GET operation with token for "/posts/1"
    Then I should see the author name as "Karthik KK" with json validation