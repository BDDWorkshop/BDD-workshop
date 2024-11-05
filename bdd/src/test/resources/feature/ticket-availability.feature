Feature: Order tickets

  Background:
    Given bdd@acagroup.be is allowed to buy tickets
    Given another@acagroup.be is allowed to buy tickets

  Scenario: Limited availability
    Given bdd@acagroup.be is allowed to buy tickets
    And the ticket service makes 0 combi tickets available
    And bdd@acagroup.be adds a combi ticket to the basket
    Then bdd@acagroup.be has an empty basket

  Scenario: Limited availability when adding multiple tickets
    Given bdd@acagroup.be is allowed to buy tickets
    And the ticket service makes 2 combi tickets available
    When bdd@acagroup.be adds 5 combi tickets to the basket
    Then The basket of bdd@acagroup.be contains 2 combi tickets

  Scenario: Limited availability affects other buyers
    Given bdd@acagroup.be is allowed to buy tickets
    And the ticket service makes 2 combi tickets available
    And another@acagroup.be adds 2 combi tickets to the basket
    When bdd@acagroup.be adds 1 combi tickets to the basket
    Then bdd@acagroup.be has an empty basket