class HomeScreen < Base

  def trait
    'MainActivity'
  end

  def await
    wait_for_activity(trait)
  end

  def gross_annual_income_is(value)
    wait_for_element_exists("* id:'main_txt_gross_value'")
    value_displayed = query("* id:'main_txt_gross_value'", :text).first
    unless value == value_displayed
      raise "Expected #{value} but instead saw #{value_displayed}"
    end
  end
end
