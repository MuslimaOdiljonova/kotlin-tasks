class WeatherController(
    private val model: WeatherModel,
    private val view: WeatherView,
    private val scope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main),
) {

    fun onCitySelected(city: String) {
        scope.launch {
            model.startLoading()   // 1. Model goes into loading state
            view.render()          //    and the View redraws ("Loading...")
            model.load(city)       // 2. suspends until the data (or error) arrives
            view.render()          // 3. View reads the Model again
        }
    }
}
