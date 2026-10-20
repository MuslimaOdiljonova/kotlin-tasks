class WeatherMviViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    fun onIntent(intent: WeatherIntent) {
        when (intent) {
            is WeatherIntent.SelectCity -> load(intent.city)
            WeatherIntent.DismissError -> {
                _state.update { it.copy(error = null) }
            }
        }
    }

    private fun load(city: String) {
        _state.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            repository.getWeather(city)
                .onSuccess { weather ->
                    _state.update { it.copy(isLoading = false, weather = weather, error = null) }
                }
                .onFailure { e ->
                    // old weather is kept on purpose, only the error is added
                    _state.update { it.copy(isLoading = false, error = e.message ?: "Unknown error") }
                }
        }
    }
}
