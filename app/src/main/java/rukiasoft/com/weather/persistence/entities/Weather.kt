package rukiasoft.com.weather.persistence.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by Roll on 21/11/17.
 * Entity for User Info
 */

@Entity(tableName = "weather", indices = [(Index(value = arrayOf("id"), unique = true))])
class Weather constructor(@PrimaryKey
                          @ColumnInfo(name = "id")
                          var id: Int,
                          @ColumnInfo(name = "coor_lon")
                          var coordLon: Double?,
                          @ColumnInfo(name = "coor_lat")
                          var coordLat: Double?,
                          @ColumnInfo(name = "weather_main")
                          var weatherMain: String?,
                          @ColumnInfo(name = "weather_description")
                          var weatherDescription: String?,
                          @ColumnInfo(name = "weather_icon")
                          var weatherIcon: String?,
                          @ColumnInfo(name = "main_temp")
                          var mainTemp: Double?,
                          @ColumnInfo(name = "wind_speed")
                          var windSpeed: Double?,
                          @ColumnInfo(name = "wind_degrees")
                          var windDegrees: Int?,
                          @ColumnInfo(name = "name")
                          var name: String?,
                          @ColumnInfo(name = "country")
                          var country: String?,
                          @ColumnInfo(name = "date")
                          var date: Date

) {

    fun compareTo(user: Weather): Boolean {
        return (id == user.id)
                .and(coordLon == user.coordLon)
                .and(coordLat == user.coordLat)
                .and(weatherMain == user.weatherMain)
                .and(weatherDescription == user.weatherDescription)
                .and(weatherIcon == user.weatherIcon)
                .and(mainTemp == user.mainTemp)
                .and(windSpeed == user.windSpeed)
                .and(windDegrees == user.windDegrees)
                .and(name == user.name)
                .and(country == user.country)
                .and(date == user.date)
    }

}