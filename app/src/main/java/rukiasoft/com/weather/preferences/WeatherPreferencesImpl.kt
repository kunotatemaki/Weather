package rukiasoft.com.weather.preferences

import rukiasoft.com.weather.preferences.WeatherPreferencesConstants.DB_VERSION
import javax.inject.Inject

/**
 * Created by Raul on 17/11/2017.
 * Implementation of WeatherPreferences
 */
class WeatherPreferencesImpl @Inject constructor(private var preferencesManager: PreferencesManager) : WeatherPreferences {

    override fun clearAllData() {
        preferencesManager.clearAll()
    }

    override fun getDbVersion() = preferencesManager.getIntFromPreferences(DB_VERSION)

    override fun setDbVersion(value: Int) {
        preferencesManager.setIntIntoPreferences(DB_VERSION, value)
    }

    override fun deleteDbVersion() {
        preferencesManager.deleteVarFromSharedPreferences(DB_VERSION)
    }

}
