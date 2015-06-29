Feature: Rest Stuff

  Background:
    Given the server knows about the following resources
        |type   |path                           |
        |api    |/hue/api |
        |account|/account/id/{id} |
        |offer  |/offer/id/{id}   |
    And the server knows about the following mime types
        |HTML   |text/html|
        |Cj     |application/vnd.collections+json|

  @restlet
  Scenario: Rest Stuff
    Given I want to interact with an api resource
      Then I can request it

  @groovyrest
  Scenario: Rest Stuff
    Given I want to interact with an api resource
      Then I can request it

  @groovyrest
  Scenario: Rest Stuff
    Given I want to interact with an api resource
    Then I can request it as HTML