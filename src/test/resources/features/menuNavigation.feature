Feature: Menu navigation functionality

  Background:
    Given user logs in "teacherEmail" "teacherPass"

  Scenario: navigate to developers page
    When user clicks on the "Developers" menu
    Then verify that user is on the "developers" page


  Scenario: navigate to Charts Sub-Menu
    When user clicks on the "Components" menu and "Charts" sub-menu
    Then verify that user is on the "charts" page


  Scenario Outline: navigate sub-menus
    When user clicks on the "<menu>" menu and "<subMenu>" sub-menu
    Then verify that user is on the "<page>" page
    Examples:
      | menu       | subMenu | page   |
      | Components | Iframe  | iframe |
      | Forms      | Submit  | submit |
      | JavaScript | Alerts  | alerts |