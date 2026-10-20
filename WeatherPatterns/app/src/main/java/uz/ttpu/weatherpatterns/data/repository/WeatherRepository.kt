class WeatherRepository(private val api: WeatherApi) {

    suspend fun getWeather(city: String): Result<Weather> {
        return try {
            val weather = api.fetchWeather(city)
            Result.success(weather)
        } catch (e: IllegalArgumentException) {
            // only the "unknown city" error is turned into a failure,
            // CancellationException is not caught so cancelling still works
            Result.failure(e)
        }
    }
}
