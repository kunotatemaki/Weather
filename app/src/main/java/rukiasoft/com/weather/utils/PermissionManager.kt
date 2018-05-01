package rukiasoft.com.weather.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import rukiasoft.com.weather.R
import java.lang.ref.WeakReference
import javax.inject.Inject

class PermissionManager @Inject constructor(private val viewUtils: CustomViewUtils,
                                            private val resourcesManager: ResourcesManager) {



    private var requestPermission = false
    private var needToShowDialogs = false

    fun checkPermission(activity: Activity, callbackAllPermissionsGranted: ((m: Unit?) -> Unit?),
                        permissions: MutableList<String>, messageRationale: String, code: Int){
        // Here, thisActivity is the current activity
        requestPermission = false
        needToShowDialogs = false

        permissions.forEach {
            if (ContextCompat.checkSelfPermission(activity,
                            it)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermission = true
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                it)) {
                    needToShowDialogs = true
                }
            }
        }
        if(needToShowDialogs) {
            viewUtils.showAlertDialog(WeakReference(activity), false,
                    resourcesManager.getString(R.string.permission_explanation), messageRationale,
                    resourcesManager.getString(R.string.accept_rationale), {askForPermission(activity, permissions, code)},
                    null, null)
        }else {
            if (requestPermission) {
                askForPermission(activity, permissions, code)
            } else {
                callbackAllPermissionsGranted.invoke(null)
            }
        }
    }

    private fun askForPermission(activity: Activity, permissions: MutableList<String>, code: Int){

        ActivityCompat.requestPermissions(activity,
                permissions.toTypedArray(),
                code)

    }

    fun arePermissionsGrantedByTheUser(grantResults: IntArray): Boolean{
        return grantResults.isNotEmpty() && !grantResults.contains(PackageManager.PERMISSION_DENIED)
    }
}