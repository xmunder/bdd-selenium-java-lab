@wikipedia @appearance @text-size
Feature: Ajustar tamano de texto en Wikipedia

  Scenario: Cambiar el tamano de texto a Grande
    Given I am on the Wikipedia home page
    When I set text size to "Grande"
    Then the selected text size should be "Grande"
