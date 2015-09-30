require 'calabash-android/abase'
# methods shared by all screens

class Base < Calabash::ABase

  def tap_button(button_name)
    button_id = $buttons.fetch(button_name, "unnamed_button")
    if button_id != "unnamed_button"
      tap_when_element_exists("* id:'#{button_id}'")
    else
      raise "\"#{button_name}\" is an unknown button. Please update the constants.rb file"
    end
  end

  def enter_text_in_field(text, field_name)
    field_id = $fields.fetch(field_name, "unnamed_field")
    if field_id != "unnamed_field"
      enter_text("android.widget.EditText id:'#{field_id}'", text)
    else
      raise "\"#{field_name}\" is an unknown field. Please update the constants.rb file"
    end
  end

  def check_text_in_field(text, field_name)
    field_id = $fields.fetch(field_name, "unnamed_field")
    if field_id != "unnamed_field"
      text_in_field = query("android.widget.EditText id:'#{field_id}'", :text).first
    else
      raise "\"#{field_name}\" is an unknown field. Please update the constants.rb file"
    end
    unless text == text_in_field
      raise "Expected #{text} but instead found #{text_in_field}"
    end
  end

end
