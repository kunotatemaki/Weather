package rukiasoft.com.weather.persistence

import android.arch.lifecycle.LiveData
import rukiasoft.com.weather.persistence.entities.Weather
import java.util.*

interface PersistenceManager {

    fun insertWeather(weather: Weather)
    fun getLastPredictionInDate(date: Date): LiveData<Weather?>

    fun deleteDb()

}