package rukiasoft.com.weather.persistence

import android.arch.lifecycle.LiveData
import rukiasoft.com.weather.persistence.PersistenceManager
import rukiasoft.com.weather.persistence.db.WeatherDatabase
import rukiasoft.com.weather.persistence.entities.Weather
import java.util.*
import javax.inject.Inject

class PersistenceManagerImpl @Inject constructor(private val db: WeatherDatabase) : PersistenceManager {

    override fun insertWeather(weather: Weather) {
        db.weatherDao().insert(weather)
    }

    override fun getLastPredictionInDate(date: Date) = db.weatherDao().getLastPredictionInDate(date)

    override fun deleteDb() {
        db.weatherDao().deleteAll()
    }

}
