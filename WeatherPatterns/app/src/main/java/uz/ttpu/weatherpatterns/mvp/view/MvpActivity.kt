class MvpActivity : ComponentActivity(), WeatherContract.View {

    private var status by mutableStateOf("Pick a city")
    private val presenter = WeatherPresenter(
        view = this,
        repository = WeatherModule.repository,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MVP", "onCreate activity=${hashCode()}")
        setContent {
            MaterialTheme {
                WeatherScreen(status = status, onCitySelected = presenter::onCitySelected)
            }
        }
    }

    override fun onDestroy() {
        presenter.destroy()   // Question 9 fix
        Log.d("MVP", "onDestroy activity=${hashCode()}")
        super.onDestroy()
    }

    override fun showLoading() { status = "Loading..." }

    override fun showWeather(weather: Weather) {
        Log.d("MVP", "showWeather called on activity=${hashCode()}")
        status = "${weather.city}: ${weather.temperatureC}°C, ${weather.condition}"
    }

    override fun showError(message: String) { status = "Error: $message" }
}
