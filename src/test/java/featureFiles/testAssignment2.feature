Feature: Check the currency converter

  Background:
	Given I am on westpac main page
	And I hover over the "FX, travel & migrant" option in the menu
	And I click the Currency converter button
	And I am on the Current converter page

  Scenario: Verify the error message when no amount entered
	When I enter value : "" in the enter amount box
	And I click the convert button on the currency converter page
	Then I verify the error message "Please enter the amount you want to convert." on the currency converter page

  Scenario Outline: Verify the user can convert from Currency A to Currency B
	When I select "ConvertFrom" dropdown option as : "<currencyFrom>"
	And I select "ConvertTo" dropdown option as : "<currencyTo>"
	And  I enter value : "<amount>" in the enter amount box
	And I click the convert button on the currency converter page
	Then the conversion is successful
	Examples:
	  | currencyFrom         | currencyTo           | amount |
	  | New Zealand Dollars  | United States Dollar | 1      |
	  | United States Dollar | New Zealand Dollars  | 1      |
	  | Pound Sterling       | New Zealand Dollars  | 1      |
	  | Swiss Franc          | Euro                 | 1      |


