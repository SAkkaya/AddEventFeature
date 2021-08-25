
  Feature: Add Event
@wip
   Scenario Outline: Users can access the "Add Event" page from the "General Information" page in the Vehicles sub-module

       Given the user is on the login page
       When the user logged in as "<userType>"
       And the user navigates to "Fleet", "Vehicles"
       And the user click on any car information row
       Then the user should be able to see the "General Information" page

     Examples:

       | userType      |
       | driver        |
       | store manager |
       | sales manager |


  Scenario: Driver can NOT add an event
      Given the user is on the login page
      When the user logged in as "driver"
      And the user navigates to "Fleet", "Vehicles"
      And the user click on any car information row
      And the user should be able to see the "General Information" page
      Then Driver should not able to click on AddEvent button



    Scenario Outline: Compulsory fields
      Given the user is on the login page
      When the user logged in as "<userType>"
      And the user navigates to "Fleet", "Vehicles"
      And the user click on any car information row
      And the user should be able to see the "General Information" page
      And the user click on AddEvent button
      Then compulsory fields are as below

        | Title                  |
        | Owner                  |
        | Organiser display name |
        | Organiser email        |
        | Start                  |
        | End                    |


      Examples:

        | userType      |
        | store manager |
        | sales manager |