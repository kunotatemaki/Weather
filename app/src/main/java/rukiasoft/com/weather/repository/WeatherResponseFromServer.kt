package rukiasoft.com.weather.repository

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResponseFromServer constructor() {


    @Expose
    @SerializedName("coord")
    var coord: CoordItem? = null

    @Expose
    @SerializedName("weather")
    var weather: WeatherItem? = null

    @Expose
    @SerializedName("main")
    var main: MainItem? = null

    @Expose
    @SerializedName("wind")
    var wind: WindItem? = null

    @Expose
    @SerializedName("sys")
    var sys: SysItem? = null

    @Expose
    @SerializedName("name")
    var name: String? = null

    class CoordItem {
        @Expose
        @SerializedName("lon")
        var lon: Double? = null

        @Expose
        @SerializedName("lat")
        var lat: Double? = null
    }

    class WeatherItem {
        @Expose
        @SerializedName("main")
        var main: String? = null

        @Expose
        @SerializedName("description")
        var description: String? = null

        @Expose
        @SerializedName("icon")
        var icon: String? = null
    }


    class MainItem {
        @Expose
        @SerializedName("temp")
        var temp: Double? = null
    }


    class WindItem {
        @Expose
        @SerializedName("speed")
        var speed: Double? = null

        @Expose
        @SerializedName("deg")
        var deg: Int? = null
    }


    class SysItem {
        @Expose
        @SerializedName("country")
        var country: String? = null
    }


}