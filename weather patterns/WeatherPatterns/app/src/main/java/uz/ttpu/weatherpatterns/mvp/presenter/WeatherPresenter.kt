class WeatherPresenter(
    private val view: WeatherContract.View,
    private val repository: WeatherRepository,
    private val scope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main),
) {

    fun onCitySelected(city: String) {
        view.showLoading()
        scope.launch {
            repository.getWeather(city)
                .onSuccess { weather -> view.showWeather(weather) }
                .onFailure { e -> view.showError(e.message ?: "Unknown error") }
        }
    }

    // Question 9 fix
    fun destroy() {
        scope.cancel()   // no callback can reach the destroyed View anymore
    }
}
