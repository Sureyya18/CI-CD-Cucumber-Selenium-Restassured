Feature: Login Page Functionality

  Background:
    Given user is on the login page

    Scenario: login as a teacher
      When user enters "teacherEmail" email
      And user enters "teacherPass" password
      And user clicks on the login button
      Then verify that user is on the home page

  Scenario: login as a dev
    When user enters "devEmail" email
    And user enters "devPass" password
    And user clicks on the login button
    Then verify that user is on the home page