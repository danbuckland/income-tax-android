Feature: View salary deductions

As a user,
I want to provide information about my income,
so that I can see a detailed breakdown of how much tax I am paying.

  Scenario:
    Given I have entered a salary of 25000
    Then I should see the following breakdown
    | Gross annual income             | £25,000.00 |
    | Tax deductions                  |  £2,880.00 |
    | National Insurance contribution |  £2,032.80 |
    | Total deductions                |  £4,912.80 |
    | Net annual income               | £20,087.20 |

  Scenario:
    Given I have entered a salary of 40000
    Then I should see the following breakdown
    | Gross annual income             | £40,000.00 |
    | Tax deductions                  |  £5,880.00 |
    | National Insurance contribution |  £3,832.80 |
    | Total deductions                |  £9,712.80 |
    | Net annual income               | £30,287.20 |
