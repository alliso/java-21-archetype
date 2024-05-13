Feature: Get items
  Scenario: Get non empty items list
    Given I store in "items" db
    """json
    {
      "name": "pepino",
      "price": 2.2
    }
    """
    When I do a GET request to /items
    Then I receive status code 200
    And Items list is not empty