package rukiasoft.com.weather.repository


import android.arch.lifecycle.LiveData
import rukiasoft.com.weather.vo.AbsentLiveData
import rukiasoft.com.weather.vo.Resource
import rukiasoft.com.weather.utils.Constants
import rukiasoft.com.weather.utils.RateLimiter
import rukiasoft.com.weather.AppExecutors
import rukiasoft.com.weather.BuildConfig
import rukiasoft.com.weather.network.*
import rukiasoft.com.weather.persistence.PersistenceManager
import rukiasoft.com.weather.persistence.entities.Weather
import rukiasoft.com.weather.persistence.utils.PersistenceUtils
import rukiasoft.com.weather.utils.DateUtils
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Roll on 4/10/17.
 * Repository that handles Task instances.
 *
 * Task - value object name
 * Repository - type of this class.
 */

@Singleton
class WeatherRequests @Inject
constructor(private val weatherServiceFactory: WeatherServiceFactory,
            private val appExecutors: AppExecutors,
            private val persistenceManager: PersistenceManager
) {

    //request info from user if the last request is more than 15 minutes old
    private val rateLimit: RateLimiter = RateLimiter(15, TimeUnit.MINUTES)

    fun downloadWeatherInfo(lat: Double, lon: Double): LiveData<Resource<Weather?>> {
        val host: String = NetworkConstants.API_BASE_URL

        return object : NetworkBoundResource<Weather?, WeatherResponseFromServer>(appExecutors) {
            override fun saveCallResult(item: WeatherResponseFromServer) {

                //store the data in the db
                val user = PersistenceUtils.getWeatherFromServerResponse(item)
                user?.let {
                    persistenceManager.insertWeather(user)
                }

            }

            override fun shouldFetch(data: Weather?): Boolean {
                return data == null || rateLimit.shouldFetch(data.date.time)
            }

            override fun loadFromDb(): LiveData<Weather?> {
                return persistenceManager.getLastPredictionInDate(DateUtils.addHour(DateUtils.currentTime, -24))
            }

            override fun createCall(): LiveData<ApiResponse<WeatherResponseFromServer>> {
                //create call
                val networkService: WeatherService? = weatherServiceFactory.getWeatherService(host, Constants.DEFAULT_NUMBER_OF_RETRIES)
                return networkService?.getWeather(lat.toString(), lon.toString(), BuildConfig.API_KEY)
                        ?: AbsentLiveData.create()
            }

            override fun onFetchFailed() {
                //something went wrong
            }
        }.asLiveData()
    }


}