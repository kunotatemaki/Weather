package rukiasoft.com.weather.persistence.utils

import rukiasoft.com.weather.utils.DateUtils
import rukiasoft.com.weather.persistence.entities.Weather
import rukiasoft.com.weather.repository.WeatherResponseFromServer


object PersistenceUtils {
    fun getWeatherFromServerResponse(server: WeatherResponseFromServer): Weather? {
        //the id is set to 1, so there will be only one register in the db
        //it maybe doesn't make sense to create a db only for one registry, but thus the app will be scalable
        return Weather(1, server.coord?.lon, server.coord?.lat,
                if(server.weather != null && server.weather!!.isNotEmpty()) server.weather!!.first().main else null,
                if(server.weather != null && server.weather!!.isNotEmpty()) server.weather!!.first().description else null,
                if(server.weather != null && server.weather!!.isNotEmpty()) server.weather!!.first().icon else null,
                server.main?.temp, server.wind?.speed, server.wind?.deg?.toInt(), server.name, server.sys?.country, DateUtils.currentTime)
    }

}