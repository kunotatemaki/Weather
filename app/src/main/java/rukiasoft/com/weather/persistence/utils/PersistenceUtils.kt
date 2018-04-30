package rukiasoft.com.weather.persistence.utils

import rukiasoft.com.weather.utils.DateUtils
import rukiasoft.com.weather.persistence.entities.Weather
import rukiasoft.com.weather.repository.WeatherResponseFromServer


object PersistenceUtils {
    fun getWeatherFromServerResponse(server: WeatherResponseFromServer): Weather? {
        //the id is set to 1, so there will be only one register in the db
        //it maybe doesn't make sense to create a db only for one registry, but thus the app will be scalable
        return Weather(1, server.coord?.lon, server.coord?.lat, server.weather?.main, server.weather?.description, server.weather?.icon,
                server.main?.temp, server.wind?.speed, server.wind?.deg, server.name, server.sys?.country, DateUtils.currentTime)
    }



}