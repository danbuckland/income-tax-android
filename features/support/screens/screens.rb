class Screens < Base
	def home
		@summary ||= page(HomeScreen)
	end
end
