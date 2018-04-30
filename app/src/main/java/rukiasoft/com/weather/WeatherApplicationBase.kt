package rukiasoft.com.weather

import android.util.Log
import com.facebook.stetho.Stetho
import rukiasoft.com.weather.di.components.WeatherComponent
import rukiasoft.com.weather.di.components.WeatherFactory
import rukiasoft.com.weather.utils.Constants
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber


class WeatherApplicationBase : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<WeatherApplicationBase> {
        val mComponent: WeatherComponent = WeatherFactory.component(this)
        mComponent.inject(this)
        return mComponent
    }

    override fun onCreate() {
        super.onCreate()

        //initialize Stetho
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())


        // Initialize Logging with Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }


        Timber.d("db path: %s", getDatabasePath(Constants.DATABASE_NAME).absolutePath)

    }



    /** A tree which logs important information for crash reporting. (Tiber) */
    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

        }
    }


}
