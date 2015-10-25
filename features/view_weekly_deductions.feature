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
    | Personal allowance:               | £203.85 |
    | Total tax deductions:             |  £61.15 |
    | National Insurance contributions: |  £42.55 |
    | Total deductions:                 | £103.71 |
    | Net weekly income:                | £405.91 |

  Scenario: Calculate weekly deductions against the UK Prime Minister's salary
    When I enter an annual income of "142500"
    And I choose to view a weekly breakdown
    Then I should see the following breakdown
    | Gross weekly income:              | £2,740.38 |
    | Personal allowance:               |     £0.00 |
    | Total tax deductions:             |   £973.90 |
    | National Insurance contributions: |   £117.72 |
    | Total deductions:                 | £1,091.62 |
    | Net weekly income:                | £1,648.76 |

  Scenario: Calculate weekly deductions against a senior London banker's salary
    When I enter an annual income of "1300000"
    And I choose to view a weekly breakdown
    Then I should see the following breakdown
    | Gross weekly income:              |  £25,000.00 |
    | Personal allowance:               |       £0.00 |
    | Total tax deductions:             |  £10,983.52 |
    | National Insurance contributions: |     £562.91 |
    | Total deductions:                 |  £11,546.43 |
    | Net weekly income:                |  £13,453.57 |
