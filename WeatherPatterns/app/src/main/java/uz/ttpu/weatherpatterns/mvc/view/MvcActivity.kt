class MvcActivity : ComponentActivity(), WeatherView {

    private val model = WeatherModel(WeatherModule.repository)
    private val controller = WeatherController(model, view = this)
    private var status by mutableStateOf("Pick a city")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                WeatherScreen(status = status, onCitySelected = controller::onCitySelected)
            }
        }
    }

    // Why no parameters: in MVC the View is allowed to look at the Model itself.
    // The Controller only says "the Model changed", and the View decides what to
    // read from it (isLoading, error, weather).
    // If the Controller passed the Weather in (render(weather)), the View would
    // not need the Model anymore. The Controller would then decide what is shown
    // and we would also need extra methods for loading and error, which is
    // basically MVP (Presenter pushes data into a passive View).
    override fun render() {
        val error = model.error
        val weather = model.weather
        status = when {
            model.isLoading -> "Loading..."
            error != null -> "Error: $error"   // checked before weather, old weather may still be there
            weather != null -> "${weather.city}: ${weather.temperatureC}°C, ${weather.condition}"
            else -> "Pick a city"
        }
    }
}
