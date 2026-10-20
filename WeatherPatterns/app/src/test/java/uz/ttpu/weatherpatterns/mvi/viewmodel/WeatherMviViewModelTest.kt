@OptIn(ExperimentalCoroutinesApi::class)
class WeatherMviViewModelTest {

    @Before
    fun setUp() {
        // viewModelScope runs on Dispatchers.Main, so replace it for the test
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun newViewModel() =
        WeatherMviViewModel(WeatherRepository(FakeWeatherApi(latencyMs = 0)))

    @Test
    fun selecting_a_known_city_ends_with_weather_and_no_loading() {
        val viewModel = newViewModel()

        viewModel.onIntent(WeatherIntent.SelectCity("Bukhara"))

        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertEquals("Bukhara", state.weather?.city)
        assertNull(state.error)
    }

    @Test
    fun selecting_an_unknown_city_ends_with_error() {
        val viewModel = newViewModel()

        viewModel.onIntent(WeatherIntent.SelectCity("Atlantis"))

        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertEquals("No forecast for Atlantis", state.error)
        assertNull(state.weather)
    }

    @Test
    fun dismissing_the_error_clears_it_and_keeps_the_old_weather() {
        val viewModel = newViewModel()
        viewModel.onIntent(WeatherIntent.SelectCity("Tashkent"))
        viewModel.onIntent(WeatherIntent.SelectCity("Atlantis"))
        assertNotNull(viewModel.state.value.error)

        viewModel.onIntent(WeatherIntent.DismissError)

        val state = viewModel.state.value
        assertNull(state.error)
        assertEquals("Tashkent", state.weather?.city)
        assertFalse(state.isLoading)
    }
}
