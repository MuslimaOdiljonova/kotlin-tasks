object WeatherModule {
    // One shared Model layer for all four patterns.
    // 3 seconds of fake latency leaves time to rotate the device in the experiments.
    val repository = WeatherRepository(FakeWeatherApi(latencyMs = 3_000))

    val weatherViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer { WeatherViewModel(repository) }
    }

    val weatherMviViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer { WeatherMviViewModel(repository) }
    }
}
