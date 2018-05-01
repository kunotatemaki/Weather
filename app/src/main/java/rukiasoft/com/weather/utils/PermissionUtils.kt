package rukiasoft.com.weather.utils

import android.Manifest
import android.app.Activity
import rukiasoft.com.weather.R
import javax.inject.Inject

class PermissionUtils @Inject constructor(private val resourcesManager: ResourcesManager,
                                          private val permissionManager: PermissionManager) {

    companion object Keys{
        const val READ_LOCATION_PERMISSIONS_REQUEST = 1
    }

    fun getLocation(activity: Activity?, callbackAllPermissionsGrantedGetLocation: ((m: Unit?) -> Unit?)) {
        val permissions = mutableListOf(Manifest.permission.ACCESS_FINE_LOCATION)
        activity?.let {
            permissionManager.checkPermission(it, callbackAllPermissionsGrantedGetLocation, permissions,
                    resourcesManager.getString(R.string.access_permission_location), Keys.READ_LOCATION_PERMISSIONS_REQUEST)
        }
    }
}