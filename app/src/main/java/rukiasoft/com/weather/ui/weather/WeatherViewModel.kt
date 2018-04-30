package rukiasoft.com.weather.ui.weather

import android.arch.lifecycle.ViewModel
import rukiasoft.com.weather.persistence.PersistenceManager
import rukiasoft.com.weather.repository.WeatherRequests
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRequests: WeatherRequests,
                                           private val persistenceManager: PersistenceManager) : ViewModel(){
}