When(/^I enter an annual income of "(.*?)"$/) do |amount|
  @screens.home.set_annual_income(amount)
end

Then(/^I should see the following breakdown$/) do |table|
  deductions = table.rows_hash
  deductions.each do |string, value|
    string_id = $strings.fetch(string, "unnamed_string")
    if string_id != "unnamed_string"
      value_displayed = query("* id:'#{string_id}'", :text).first
      unless value_displayed == value
        raise "expected #{value} but saw #{value_displayed}"
      end
    else
      raise "\"#{string}\" is an unknown string. Please update the constants.rb file"
    end
  end
end

When(/^I attempt to enter an annual income of "(.*?)"$/) do |arg1|
  pending # express the regexp above with the code you wish you had
end

Then(/^I should be prevented from typing more than "(.*?)"$/) do |arg1|
  pending # express the regexp above with the code you wish you had
end
