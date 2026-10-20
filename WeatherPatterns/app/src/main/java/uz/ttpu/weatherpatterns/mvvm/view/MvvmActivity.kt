class MvvmActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels {
        WeatherModule.weatherViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                WeatherScreen(
                    status = uiState.toStatusText(),
                    onCitySelected = viewModel::onCitySelected,
                )
            }
        }
    }
}

private fun WeatherUiState.toStatusText(): String = when (this) {
    WeatherUiState.Idle -> "Pick a city"
    WeatherUiState.Loading -> "Loading..."
    is WeatherUiState.Success ->
        "${weather.city}: ${weather.temperatureC}°C, ${weather.condition}"
    is WeatherUiState.Error -> "Error: $message"
}
