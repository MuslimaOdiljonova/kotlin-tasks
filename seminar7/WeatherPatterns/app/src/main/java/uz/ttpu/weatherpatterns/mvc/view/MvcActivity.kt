package uz.ttpu.weatherpatterns.mvc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import uz.ttpu.weatherpatterns.di.WeatherModule
import uz.ttpu.weatherpatterns.mvc.controller.WeatherController
import uz.ttpu.weatherpatterns.mvc.model.WeatherModel
import uz.ttpu.weatherpatterns.ui.WeatherScreen

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

    // render() has no parameters because in MVC the View can look at the Model itself,
    // the Controller only says "the Model changed". If the Controller passed the Weather
    // into the View, the View would not need the Model anymore, so it would be like MVP.
    override fun render() {
        val weather = model.weather
        status = when {
            model.isLoading -> "Loading…"
            model.error != null -> "Error: ${model.error}"
            weather != null -> "${weather.city}: ${weather.temperatureC}°C, ${weather.condition}"
            else -> "Pick a city"
        }
    }
}
