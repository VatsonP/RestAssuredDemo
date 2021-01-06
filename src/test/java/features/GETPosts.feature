Feature: GETPosts
  Verify different GET operations using REST-assured library

  Scenario: Verify one author of the post
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Karthik KK"

  Scenario: Verify collection of authors in the post
    Given I perform GET operation for "/posts"
    Then I should see the author names

  @smoke
  Scenario: Verify Parameter of Get
    Given I perform GET Parameter operation for "posts/{postid}"
      | postid |
      | 2     |
    Then I should see verify GET Parameter
      | postid |     author |
      |      2 | Karthik KK |


  @smoke
  Scenario: Verify GET operation with bearer authentication token
    Given I perform authentication operation for "/auth/login" with body
      | email              | password  |
      | karthik@email.com | haha123 |
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Karthik KK"

  @smoke
  Scenario: Verify GET operation with json validation
    Given I perform authentication operation for "/auth/login" with body
      | email              | password  |
      | karthik@email.com | haha123 |
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Karthik KK" with json validation