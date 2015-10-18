When(/^I enter an annual income of "([^\"]*)"$/) do |amount|
  @screens.home.set_annual_income(amount)
end

When(/^I choose to view a monthly breakdown$/) do
  @screens.home.view_monthly
end

Then(/^I should see the following breakdown$/) do |table|
  @screens.home.check_values(table)
  @screens.home.check_strings(table)
end

When(/^I attempt to enter an annual income of "([^\"]*)"$/) do |amount|
  @base.enter_text_in_field(amount, 'Salary')
end

Then(/^I should be prevented from typing more than "([^\"]*)"$/) do |amount|
  @base.check_text_in_field(amount, 'Salary')
end
