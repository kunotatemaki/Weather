package rukiasoft.com.weather.di.modules

import rukiasoft.com.weather.di.interfaces.CustomScopes
import dagger.Module
import dagger.android.ContributesAndroidInjector
import rukiasoft.com.weather.ui.weather.WeatherActivity

/**
 * Created by Raul on 16/11/2017.
 * Builder for activities
 */
@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector
    abstract fun bindWeatherScreen(): WeatherActivity


}