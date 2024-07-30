Feature: Users - Login with user and compare response balance with onscreen balance

  Scenario: Login with user and compare response balance with onscreen balance
      Given Open page and Login with test username: tu_nino and password: Pass112#
      When Get POST request for user balance
      Then verify user balance