Feature: Westpac KiwiSaver Scheme Retirement Calculator

  Background:
	Given I am on KiwiSaver Retirement Calculator

  Scenario: To verify information icon present for Current Age field
	When I click on the information icon besides "CurrentAge" field
	Then I should verify the information message for "CurrentAge" field as : "This calculator has an age limit of 64 years old as you need to be under the age of 65 to join KiwiSaver."

  Scenario Outline: To verify calculated projected balance for different users
	And I input information in the Retirement Calculator for "CurrentAge" as : "<age>"
	And I select "EmploymentStatus" as "<status>" from the dropdown
	And I input information in the Retirement Calculator for "AnnualIncome" as : "<salary>"
	And I input information in the Retirement Calculator for "KiwiSaverBalance" as : "<currentKiwiSaverBalance>"
	And I input information in the Retirement Calculator for "VoluntaryContributions" as : "<voluntaryContribution>"
	And I select KiwiSaverMember Contribution as : "<Member Contribution>"
	And I select "VoluntaryContributions" as "<frequency>" from the frequency dropdown
	And I select "PIRRate" as "<rate>" from the dropdown
	And I select the "RiskProfile" as "<profile>"
	And I input information in the Retirement Calculator for "SavingsGoal" as : "<savingGoal>"
	When I click button View your KiwiSaver retirement projections >
	Then I should see the projected balance at retirement is : "<balance>"
	Examples:
	  | age | status        | salary | Member Contribution | voluntaryContribution | frequency    | rate   | currentKiwiSaverBalance  | profile | savingGoal | balance  |
	  | 45  | Self-employed |        |                     | 90                    | Fortnightly  | 10.5% | 100000                   | Medium  | 290000     | $230,481 |
      | 30  | Employed      | 82000  | 4%                  |                       |              | 17.5% |                          | High    |            | $313,971 |
      | 55  | Not employed  |        |                     | 10                    | Annually     | 10.5% | 140000                   | Medium  | 200000     | $176,557 |