@view_weekly_deductions
Feature: View weekly deductions

As a user,
I want to provide information about my income,
so that I can see a detailed breakdown of how much tax I am paying weekly.

  Background: Launch the application to the Home screen
    Given I am on the Home screen

  Scenario: Calculate weekly deductions against the average UK salary
    When I enter an annual income of "26500"
    And I choose to view a weekly breakdown
    Then I should see the following breakdown
    | Gross weekly income:              | £509.62 |
    | Personal allowance:               | £227.88 |
    | Total tax deductions:             |  £56.35 |
    | National Insurance contributions: |  £41.71 |
    | Total deductions:                 |  £98.06 |
    | Net weekly income:                | £411.56 |

  Scenario: Calculate weekly deductions against the UK Prime Minister's salary
    When I enter an annual income of "152532"
    And I choose to view a weekly breakdown
    Then I should see the following breakdown
    | Gross weekly income:              | £2,933.31 |
    | Personal allowance:               |     £0.00 |
    | Total tax deductions:             | £1,043.07 |
    | National Insurance contributions: |   £128.37 |
    | Total deductions:                 | £1,171.44 |
    | Net weekly income:                | £1,761.87 |

  Scenario: Calculate weekly deductions against a senior London banker's salary
    When I enter an annual income of "1300000"
    And I choose to view a weekly breakdown
    Then I should see the following breakdown
    | Gross weekly income:              |  £25,000.00 |
    | Personal allowance:               |       £0.00 |
    | Total tax deductions:             |  £10,973.08 |
    | National Insurance contributions: |     £569.71 |
    | Total deductions:                 |  £11,542.78 |
    | Net weekly income:                |  £13,457.22 |
