package rukiasoft.com.weather.di.modules

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import rukiasoft.com.weather.WeatherApplicationBase
import rukiasoft.com.weather.persistence.PersistenceManager
import rukiasoft.com.weather.persistence.PersistenceManagerImpl
import rukiasoft.com.weather.persistence.db.WeatherDatabase
import rukiasoft.com.weather.preferences.WeatherPreferences
import rukiasoft.com.weather.preferences.WeatherPreferencesImpl
import rukiasoft.com.weather.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Raul on 16/11/2017.
 * Module for the app component
 */
@Module(includes = [(ViewModelModule::class)])
@Singleton
class WeatherModule {


    @Provides
    fun providesContext(application: WeatherApplicationBase): Context {
        return application.applicationContext
    }

    @Provides
    fun providesPersistenceManager(persistenceManager: PersistenceManagerImpl): PersistenceManager {
        return persistenceManager
    }

    @Provides
    fun providesPreferencesManager(medicationPreferences: WeatherPreferencesImpl): WeatherPreferences {
        return medicationPreferences
    }

    @Singleton
    @Provides
    fun provideDb(app: WeatherApplicationBase, preferences: WeatherPreferences): WeatherDatabase {


        return Room.databaseBuilder(app,
                WeatherDatabase::class.java, Constants.DATABASE_NAME)
                //.addMigrations()    //no migrations, version 1
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        //if the db is updated, remove info from preferences (as we are recreating all the tables)
                        val oldVersion = preferences.getDbVersion()
                        if (oldVersion != db.version) {
                            //store the new version
                            preferences.setDbVersion(db.version)
                        }
                    }
                })
                .build()

    }


}