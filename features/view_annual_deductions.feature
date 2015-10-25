@view_annual_discussions
Feature: View annual deductions

As a user,
I want to provide information about my income,
so that I can see a detailed breakdown of how much tax I am paying annually.

  Background: Launch the application to the Home screen
    Given I am on the Home screen

  Scenario: Calculate annual deductions against the average UK salary
    When I enter an annual income of "26500"
    Then I should see the following breakdown
    | Gross annual income:              | £26,500.00 |
    | Personal allowance:               | £10,600.00 |
    | Total tax deductions:             |  £3,180.00 |
    | National Insurance contributions: |  £2,212.80 |
    | Total deductions:                 |  £5,392.80 |
    | Net annual income:                | £21,107.20 |

  Scenario: Calculate annual deductions against the UK Prime Minister's salary
    When I enter an annual income of "142500"
    Then I should see the following breakdown
    | Gross annual income:              | £142,500.00 |
    | Personal allowance:               |       £0.00 |
    | Total tax deductions:             |  £50,643.00 |
    | National Insurance contributions: |   £6,121.30 |
    | Total deductions:                 |  £56,764.30 |
    | Net annual income:                |  £85,735.70 |

  Scenario: Calculate annual deductions against a senior London banker's salary
    When I enter an annual income of "1300000"
    Then I should see the following breakdown
    | Gross annual income:              | £1,300,000.00 |
    | Personal allowance:               |         £0.00 |
    | Total tax deductions:             |   £571,143.00 |
    | National Insurance contributions: |    £29,271.30 |
    | Total deductions:                 |   £600,414.30 |
    | Net annual income:                |   £699,585.70 |

  Scenario: Attempt to calculate deductions against a massive salary
    When I attempt to enter an annual income of "12345678"
    Then I should be prevented from typing more than "1234567"
