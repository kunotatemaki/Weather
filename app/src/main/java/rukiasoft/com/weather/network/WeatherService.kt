package rukiasoft.com.weather.network

import android.arch.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Query
import rukiasoft.com.weather.repository.WeatherResponseFromServer


/**
 * Created by Roll
 * Requests to firefly service
 *
 */
interface WeatherService {

    @GET("/data/2.5/weather")
    fun getWeather(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") apiKey: String): LiveData<ApiResponse<WeatherResponseFromServer>>

}