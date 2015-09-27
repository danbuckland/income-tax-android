When(/^I enter an annual income of "(.*?)"$/) do |amount|
  @screens.home.set_annual_income(amount)
end

Then(/^I should see the following breakdown$/) do |table|
  # table is a Cucumber::Ast::Table
  pending # express the regexp above with the code you wish you had
end

When(/^I attempt to enter an annual income of "(.*?)"$/) do |arg1|
  pending # express the regexp above with the code you wish you had
end

Then(/^I should be prevented from typing more than "(.*?)"$/) do |arg1|
  pending # express the regexp above with the code you wish you had
end
