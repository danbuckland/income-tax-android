@wip @view_monthly_deductions
Feature: View monthly deductions

As a user,
I want to provide information about my income,
so that I can see a detailed breakdown of how much tax I am paying monthly.

  Background: Launch the application to the Home screen
    Given I am on the Home screen

  Scenario: Calculate monthly deductions against the average UK salary
    When I enter an annual income of "26500"
    And I choose to view a monthly breakdown
    Then I should see the following breakdown
    | Gross monthly income            | £2,208.33 |
    | Personal allowance              |   £883.33 |
    | Tax deductions                  |   £265.00 |
    | National Insurance contribution |   £184.40 |
    | Total deductions                |   £449.40 |
    | Net monthly income              | £1,758.93 |

  Scenario: Calculate monthly deductions against the UK Prime Minister's salary
    When I enter an annual income of "142500"
    And I choose to view a monthly breakdown
    Then I should see the following breakdown
    | Gross monthly income            | £11,875.00 |
    | Personal allowance              |      £0.00 |
    | Tax deductions                  |  £4,220.25 |
    | National Insurance contribution |    £510.11 |
    | Total deductions                |  £4,730.36 |
    | Net monthly income              |  £7,144.64 |

  Scenario: Calculate monthly deductions against a senior London banker's salary
    When I enter an annual income of "1300000"
    And I choose to view a monthly breakdown
    Then I should see the following breakdown
    | Gross monthly income            | £108,333.33 |
    | Personal allowance              |       £0.00 |
    | Tax deductions                  |  £47,595.25 |
    | National Insurance contribution |   £2,439.28 |
    | Total deductions                |  £50,034.53 |
    | Net monthly income              |  £58,298.81 |
