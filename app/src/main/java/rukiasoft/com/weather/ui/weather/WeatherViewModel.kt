package rukiasoft.com.weather.ui.weather

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import rukiasoft.com.weather.persistence.entities.Weather
import rukiasoft.com.weather.repository.WeatherRequests
import rukiasoft.com.weather.utils.switchMap
import rukiasoft.com.weather.vo.Resource
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRequests: WeatherRequests) : ViewModel() {

    private val query: MutableLiveData<Long> = MutableLiveData()

    private var weather: LiveData<Resource<Weather?>> = MutableLiveData()

    var forceDownload: Boolean = false

    private var lat: Double? = null
    private var lon: Double? = null

    init {
        query.value = System.currentTimeMillis()
        weather = query.switchMap { _ ->
            weatherRequests.downloadWeatherInfo(lat, lon, forceDownload)
        }
    }

    fun setLatAndLon(lat: Double, lon: Double) {
        this.lat = lat
        this.lon = lon
        query.value = System.currentTimeMillis()
    }

    fun getWeather() = weather


}