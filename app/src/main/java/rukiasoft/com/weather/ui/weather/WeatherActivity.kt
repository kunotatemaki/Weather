package rukiasoft.com.weather.ui.weather

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.rukiasoft.medication.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_weather.*
import rukiasoft.com.weather.R
import rukiasoft.com.weather.databinding.ActivityWeatherBinding
import rukiasoft.com.weather.databinding.GlideBindingComponent
import rukiasoft.com.weather.network.NetworkUtils
import rukiasoft.com.weather.persistence.entities.Weather
import rukiasoft.com.weather.utils.PermissionManager
import rukiasoft.com.weather.utils.PermissionUtils
import rukiasoft.com.weather.vo.Status
import javax.inject.Inject


class WeatherActivity : BaseActivity() {

    @Inject
    lateinit var permissionUtils: PermissionUtils

    @Inject
    lateinit var networkUtils: NetworkUtils

    @Inject
    lateinit var permissionManager: PermissionManager

    private lateinit var viewModel: WeatherViewModel
    private lateinit var mBinding: ActivityWeatherBinding
    private var menuItem: MenuItem? = null
    private var isRefreshing = false

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather, GlideBindingComponent())

        setSupportActionBar(toolbar)

        //get Weather
        viewModel.getWeather().observe(this, Observer {
            it?.let {
                when (it.status) {

                    Status.SUCCESS -> {
                        hideLoading()
                        setData(it.data)
                    }
                    Status.ERROR -> {
                        //if we are here and there is no data on the screen, means:
                        //->there is no internet connection and the db is empty or has old info
                        //->the server is unreachable and the db is empty or has old info
                        //so check if there is internet connection, because the requirements say that a message has to be shown
                        //only for the firs case
                        if(mBinding.content.weather == null && !networkUtils.isNetworkAvailable()){
                            Snackbar.make(mBinding.root, resourcesManager.getString(R.string.no_internet), Snackbar.LENGTH_LONG).show()
                        }
                        hideLoading()
                    }
                    Status.LOADING -> {
                        showLoading()
                    }
                }
            }
        })

        viewModel.forceDownload = false
        permissionUtils.getLocation(this@WeatherActivity, { getWeather() })

    }

    /**
     * set data on screen (data binding)
     */
    private fun setData(weather: Weather?) {
        mBinding.content.resources = resourcesManager
        mBinding.content.weather = weather
        mBinding.executePendingBindings()
    }

    /***
     * request info to weather api
     */
    @SuppressLint("MissingPermission")
    private fun getWeather() {
        //get lat and lon
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val longitude = location.longitude
        val latitude = location.latitude

        viewModel.setLatAndLon(latitude, longitude)

        //if there is no internet connection, set the value to true so that if there is data in th db
        //and is less than 24h old, the last update time will be shown
        mBinding.content.outdated = networkUtils.isNetworkAvailable().not()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_weather, menu)
        menuItem = menu.findItem(R.id.action_refresh)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_refresh) {
            if (isRefreshing.not()) {
                //force refresh
                viewModel.forceDownload = true
                permissionUtils.getLocation(this@WeatherActivity, { getWeather() })
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /***
     * show indeterminate progress bar
     */
    private fun showLoading() {

        if (isRefreshing.not()) {
            isRefreshing = true
            //set ProgressBar in the MenuItem
            menuItem?.setActionView(R.layout.menu_item_refresh)

        }
    }

    /***
     * gide indeterminate progress bar
     */
    private fun hideLoading() {
        isRefreshing = false
        menuItem?.actionView = null

    }

    /***
     * result of requesting the permission for location
     */
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PermissionUtils.Keys.READ_LOCATION_PERMISSIONS_REQUEST -> {
                // If request is cancelled, the result arrays are empty.
                if (permissionManager.arePermissionsGrantedByTheUser(grantResults)) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    getWeather()

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Snackbar.make(mBinding.root, resourcesManager.getString(R.string.need_permission), Snackbar.LENGTH_LONG).show()
                }
                return
            }
        }
    }
}
