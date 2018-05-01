package rukiasoft.com.weather.persistence.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import rukiasoft.com.weather.utils.getDistinct
import rukiasoft.com.weather.persistence.entities.Weather
import java.util.*

@Dao
abstract class WeatherDao : BaseDao<Weather> {


    @Query("SELECT * FROM weather WHERE date >= :date")
    protected abstract fun getLastPredictionInDateInternal(date: Date): LiveData<Weather?>

    fun getLastPredictionInDate(date: Date) = getLastPredictionInDateInternal(date).getDistinct()

    @Query("DELETE FROM weather")
    abstract fun deleteAll()


}