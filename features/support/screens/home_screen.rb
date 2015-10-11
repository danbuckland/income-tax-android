class HomeScreen < Base

  def trait
    'MainActivity'
  end

  def await
    wait_for_activity(trait)
  end

  def set_annual_income(amount)
    enter_text_in_field(amount, 'Salary')
    tap_button('Go')
  end

  def view_monthly
    tap_button('Monthly')
  end

  def check_deductions(table)
    deductions = table.rows_hash
    deductions.each do |string, value|
      string_id = $strings.fetch(string, "unnamed_string")
      if string_id != "unnamed_string"
        value_displayed = query("* id:'#{string_id}'", :text).first
        unless value_displayed == value
          raise "Expected #{string} to be #{value} but instead saw #{value_displayed}"
        end
      else
        raise "\"#{string}\" is an unknown string. Please update the constants.rb file"
      end
    end
  end

end
