package rukiasoft.com.weather.di.components

import rukiasoft.com.weather.WeatherApplicationBase
import rukiasoft.com.weather.di.modules.ActivityBuilder
import rukiasoft.com.weather.di.modules.FragmentsProvider
import rukiasoft.com.weather.di.modules.WeatherModule
import rukiasoft.com.weather.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Main component of the student-planner app
 */

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (ActivityBuilder::class), (WeatherModule::class),
    (FragmentsProvider::class), (FragmentsProvider::class),
    (ViewModelModule::class)])
interface WeatherComponent : AndroidInjector<WeatherApplicationBase>  {

    override fun inject(weatherApp: WeatherApplicationBase)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: WeatherApplicationBase): Builder

        fun build(): WeatherComponent
    }

}