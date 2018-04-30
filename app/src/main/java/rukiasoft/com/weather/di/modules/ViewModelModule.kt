package rukiasoft.com.weather.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import rukiasoft.com.weather.viewmodel.MedicationViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import rukiasoft.com.weather.di.interfaces.ViewModelKey
import rukiasoft.com.weather.ui.weather.WeatherViewModel

/**
 * Created by Raul on 16/11/2017.
 * Module for injection of ViewModels (if needed)
 */
@Suppress("unused")
@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    internal abstract fun bindMedicationViewModel(weatherViewModel: WeatherViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: MedicationViewModelFactory): ViewModelProvider.Factory
}