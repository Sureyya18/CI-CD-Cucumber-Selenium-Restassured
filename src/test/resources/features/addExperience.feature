Feature: Add new experience

  Background:
    Given user logs in "testerEmail" "testerPass"
    When user clicks on the "Vadim" menu and "My Profile" sub-menu

    Scenario: add new experience
      When user clicks on the "Add Experience" section
      And user enters "Job Title *" "SDET" in Add Experience
      And user enters "Company *" "Google" in Add Experience
      And user enters "Location" "New York" in Add Experience
      And user enters "From Date" "22112020" in Add Experience
      And user enters "To Date " "02102024" in Add Experience
      And user enters "Job Description" "Senior Test Consult" in Add Experience
      And user clicks on the Add Experience Button
      Then verify that user sees experience details on the Overview section
