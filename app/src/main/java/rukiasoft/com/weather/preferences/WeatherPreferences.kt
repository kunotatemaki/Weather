package rukiasoft.com.weather.preferences

/**
 * Created by Raul on 17/11/2017.
 * Class for accessing the shared preferences in the app
 */
interface WeatherPreferences {

    fun getDbVersion(): Int
    fun setDbVersion(value: Int)
    fun deleteDbVersion()

    fun clearAllData()
}
