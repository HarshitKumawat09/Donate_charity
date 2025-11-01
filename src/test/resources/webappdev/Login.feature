Feature: test the login functionality of the homework app
  Scenario: a normal successful login
    Given I am on the login page
    When I enter the username "Tommy"
    And I enter the password "Trojan"
    And I press the Login button
    Then I should get a "Login Successful" message

  Scenario: an incorrect login
    Given I am on the login page
    When I enter the username "Tommy"
    And I enter the password "Bruin"
    And I press the Login button
    Then I should get a "Login Unsuccessful" message

  Scenario: blank login attempt
    Given I am on the login page
    When I enter the username ""
    And I enter the password ""
    And I press the Login button
    Then I should get a "Login Unsuccessful, username and password required" message

  Scenario: empty password
    Given I am on the login page
    When I enter the username "Tommy"
    And I enter the password ""
    And I press the Login button
    Then I should get a "Login Unsuccessful, password required" message

  Scenario: empty username
    Given I am on the login page
    When I enter the username ""
    And I enter the password "Trojan"
    And I press the Login button
    Then I should get a "Login Unsuccessful, username required" message

  Scenario: three incorrect logins in a row
    Given I am on the login page
    And I have tried unsuccessfully to log in the two previous attempts
    When I enter the username "Tommy"
    And I enter the password "Bruin"
    And I press the Login button
    Then I should be redirected to the Account Blocked page
    And I should get a "Account Blocked" message

  Scenario: two incorrect logins in a row
    Given I am on the login page
    And I have tried unsuccessfully to log in on the previous attempt
    When I enter the username "Tommy"
    And I enter the password "Bruin"
    And I press the Login button
    Then I should get a "Login Unsuccessful, one more attempt to log in allowed" message



