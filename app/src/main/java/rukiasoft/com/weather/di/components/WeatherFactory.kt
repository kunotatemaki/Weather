package rukiasoft.com.weather.di.components

import rukiasoft.com.weather.WeatherApplicationBase

object WeatherFactory {

    fun component(context: WeatherApplicationBase): WeatherComponent {
        return DaggerWeatherComponent.builder()
                .application(context)
                .build()
    }

}