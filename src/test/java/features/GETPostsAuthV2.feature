Feature: GETPostsAuthV2
  Verify different GET operations with authentication using REST-assured library


  @smoke
  Scenario: V2 Verify GET operation with bearer authentication token
    Given V2 I perform authentication operation for "/auth/login" with body
      | email              | password |
      | karthik@gmail.com  | haha123  |
    Given V2 I perform GET operation with token for "/posts"
    Then V2 I should check the author names like "Karthik KK"


  @smoke
  Scenario: V2 Verify GET operation with queryParam and bearer authentication token
    Given V2 I perform authentication operation for "/auth/login" with body
      | email              | password |
      | karthik@gmail.com  | haha123  |
    Given V2 I perform GET operation with queryParam and token for "/posts"
      | id   |
      | 2    |
    Then V2 I should check the author name as "Karthik KK"


  @smoke
  Scenario: V2 Verify GET operation with json validation
    Given V2 I perform authentication operation for "/auth/login" with body
      | email              | password  |
      | karthik@gmail.com  | haha123   |
    Given V2 I perform GET operation with token for "/posts/1"
    Then V2 I should see the author name as "Karthik KK" with json validation