Feature: Order tickets

  Scenario: Allowed to buy tickets
    Given bdd@acagroup.be is allowed to buy tickets
    Then bdd@acagroup.be has an empty basket

  Scenario: Not allowed to buy tickets
    When bdd@acagroup.be is not allowed to buy tickets
    Then bdd@acagroup.be has no basket

  Scenario: Add a ticket to the basket
    Given bdd@acagroup.be is allowed to buy tickets
    When bdd@acagroup.be adds a ticket to the basket
    Then The basket of bdd@acagroup.be contains 1 ticket

