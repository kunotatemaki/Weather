package rukiasoft.com.weather.persistence.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import rukiasoft.com.weather.persistence.utils.Converters
import rukiasoft.com.weather.persistence.daos.WeatherDao
import rukiasoft.com.weather.persistence.entities.Weather

@Database(entities = [(Weather::class)],
        version = 1)
@TypeConverters(Converters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
