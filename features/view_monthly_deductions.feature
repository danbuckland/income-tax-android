@view_monthly_deductions
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
    | Gross monthly income:             | £2,208.33 |
    | Personal allowance:               |   £987.50 |
    | Total tax deductions:             |   £244.17 |
    | National Insurance contributions: |   £180.76 |
    | Total deductions:                 |   £424.93 |
    | Net monthly income:               | £1,783.41 |

  Scenario: Calculate monthly deductions against the UK Prime Minister's salary
    When I enter an annual income of "152532"
    And I choose to view a monthly breakdown
    Then I should see the following breakdown
    | Gross monthly income:             | £12,711.00 |
    | Personal allowance:               |      £0.00 |
    | Total tax deductions:             |  £4,519.95 |
    | National Insurance contributions: |    £556.28 |
    | Total deductions:                 |  £5,076.23 |
    | Net monthly income:               |  £7,634.77 |

  Scenario: Calculate monthly deductions against a senior London banker's salary
    When I enter an annual income of "1300000"
    And I choose to view a monthly breakdown
    Then I should see the following breakdown
    | Gross monthly income:             | £108,333.33 |
    | Personal allowance:               |       £0.00 |
    | Total tax deductions:             |  £47,550.00 |
    | National Insurance contributions: |   £2,468.73 |
    | Total deductions:                 |  £50,018.73 |
    | Net monthly income:               |  £58,314.61 |
