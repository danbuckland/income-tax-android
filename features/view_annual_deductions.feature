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
    | Personal allowance:               | £11,850.00 |
    | Total tax deductions:             |  £2,930.00 |
    | National Insurance contributions: |  £2,169.12 |
    | Total deductions:                 |  £5,099.12 |
    | Net annual income:                | £21,400.88 |

  Scenario: Calculate annual deductions against the UK Prime Minister's salary
    When I enter an annual income of "152532"
    Then I should see the following breakdown
    | Gross annual income:              | £152,532.00 |
    | Personal allowance:               |       £0.00 |
    | Total tax deductions:             |  £54,239.40 |
    | National Insurance contributions: |   £6,675.36 |
    | Total deductions:                 |  £60,914.76 |
    | Net annual income:                |  £91,617.24 |

  Scenario: Calculate annual deductions against a senior London banker's salary
    When I enter an annual income of "1300000"
    Then I should see the following breakdown
    | Gross annual income:              | £1,300,000.00 |
    | Personal allowance:               |         £0.00 |
    | Total tax deductions:             |   £570,600.00 |
    | National Insurance contributions: |    £29,624.72 |
    | Total deductions:                 |   £600,224.72 |
    | Net annual income:                |   £699,775.28 |

  Scenario: Attempt to calculate deductions against a massive salary
    When I attempt to enter an annual income of "12345678"
    Then I should be prevented from typing more than "1234567"
