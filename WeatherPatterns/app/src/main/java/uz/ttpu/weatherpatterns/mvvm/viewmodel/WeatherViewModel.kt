class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun onCitySelected(city: String) {
        _uiState.value = WeatherUiState.Loading
        viewModelScope.launch {
            repository.getWeather(city)
                .onSuccess { weather -> _uiState.value = WeatherUiState.Success(weather) }
                .onFailure { e -> _uiState.value = WeatherUiState.Error(e.message ?: "Unknown error") }
        }
    }
}
