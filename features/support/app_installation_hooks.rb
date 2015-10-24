require 'calabash-android/management/app_installation'

AfterConfiguration do |config|
  FeatureMemory.feature = nil
end

Before do |scenario|

  @base ||= page(Base)
	@screens ||= page(Screens)

  scenario = scenario.scenario_outline if scenario.respond_to?(:scenario_outline)

  feature = scenario.feature
  # Only reinstall on a new test run
  if FeatureMemory.feature == nil || ENV['RESET_BETWEEN_SCENARIOS'] == '1'
    if ENV['RESET_BETWEEN_SCENARIOS'] == '1'
      log 'New scenario - reinstalling apps'
    else
      log 'First scenario in feature - reinstalling apps'
    end

    uninstall_apps
    install_app(ENV['TEST_APP_PATH'])
    install_app(ENV['APP_PATH'])
    FeatureMemory.feature = feature
    FeatureMemory.invocation = 1
  else
    FeatureMemory.invocation += 1
  end
end

FeatureMemory = Struct.new(:feature, :invocation).new
