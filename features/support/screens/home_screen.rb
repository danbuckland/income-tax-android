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

  def view_weekly
    tap_button('Weekly')
  end

  def check_values(table)
    values = table.rows_hash
    values.each do |string, value|
      value_id = $values.fetch(string, "unnamed_value")
      if value_id != "unnamed_value"
        value_displayed = query("* id:'#{value_id}'", :text).first
        unless value_displayed == value
          raise "Expected #{string} to be #{value} but instead saw #{value_displayed}"
        end
      else
        raise "\"#{string}\" is an unknown value. Please update the constants.rb file"
      end
    end
  end

  def check_strings(table)
    strings = table.rows_hash
    strings.each do |string, value|
      string_id = $strings.fetch(string, "unnamed_string")
      if string_id != "unnamed_string"
        string_displayed = query("* id:'#{string_id}'", :text).first
        unless string_displayed == string
          raise "App displayed #{string_displayed} instead of expected #{string}"
        end
      else
        raise "\"#{string}\" is an unknown string. Please update the constants.rb file"
      end
    end
  end

end
