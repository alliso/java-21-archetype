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
    And Document is stored in collection "items"
    """json
    {"name":  "pepino"}
    """
    
  Scenario: Create new item using kafka
    When I produce a message in topic "items.topic"
    """json
    {
    "name": "tomate",
    "price": 2.2
  }
    """
    Then Document is stored in collection "items"
    """json
    {"name":  "tomate"}
    """
