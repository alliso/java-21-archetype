Feature: Create items

  Scenario: Create new item using api
    When I do a POST request to /items with body:
    """json
    {
    "name": "pepino",
    "price": 2.2
  }
    """
    Then I receive status code 204
    And Item with name "pepino" is stored in db