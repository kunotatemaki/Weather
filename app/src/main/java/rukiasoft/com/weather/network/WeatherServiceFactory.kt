package rukiasoft.com.weather.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Roll on 5/10/17.
 * factory method to return the {@link WeatherService} class
 */
@Singleton
class WeatherServiceFactory @Inject constructor() {

    fun getWeatherService(host: String, retries: Int): WeatherService? {
        // the base url has to end with "/" or retrofit will crash
        var baseUrl: String = host

        if (!baseUrl.endsWith("/")) {
            baseUrl += "/"
        }

        var weatherService: WeatherService? = null

        val client = OkHttpClient().newBuilder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

        try {
            weatherService = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory(retries))
                    .build()
                    .create<WeatherService>(WeatherService::class.java)

        } catch (e: IllegalArgumentException) {

        }

        return weatherService

    }


}
