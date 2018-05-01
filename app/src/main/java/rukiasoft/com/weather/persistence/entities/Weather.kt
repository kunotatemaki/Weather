package rukiasoft.com.weather.persistence.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import kotlinx.android.synthetic.main.content_weather.view.*
import rukiasoft.com.weather.R
import rukiasoft.com.weather.network.NetworkConstants
import rukiasoft.com.weather.utils.DateUtils
import rukiasoft.com.weather.utils.ResourcesManager
import java.text.DecimalFormat
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

    fun getTemp(): String{
        mainTemp?.let{
            val tempCelsius = (it - 273.15)
            val formatted =  DecimalFormat("#.##").format(tempCelsius)
            return "$formatted ºC"
        }
        return ""
    }

    fun getWind(): String{
        windSpeed?.let{
            return "$windSpeed m/s"
        }
        return ""
    }

    fun getIcon(): String{
        weatherIcon?.let{
            return NetworkConstants.API_BASE_URL_ICON.replace(NetworkConstants.ICON_TEMPLATE, it)
        }
        return ""
    }

    fun lastUpdated(resourcesManager: ResourcesManager):String{
        val label = resourcesManager.getString(R.string.last_updated)
        val date = DateUtils.getDateFormatted(this.date)
        return "$label $date"
    }

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