package rukiasoft.com.weather.utils

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import java.lang.ref.WeakReference
import javax.inject.Inject

@Suppress("unused")
class CustomViewUtils @Inject constructor(private val context: Context, private val resourcesManager: ResourcesManager){

    fun showAlertDialog(activity: WeakReference<Activity>, allowCancelWhenTouchingOutside: Boolean, title: String, message: String?,
                        positiveButton: String?, callbackPositive: ((m: Unit?) -> Unit?)?,
                        negativeButon: String?, callbackNegative: ((m: Unit?) -> Unit?)?){
        activity.safe {
            val builder = AlertDialog.Builder(activity.get()!!)
            builder.setTitle(title)
            message?.let { builder.setMessage(message) }
            positiveButton?.let {
                builder.setPositiveButton(positiveButton,
                        { _, _ ->
                            callbackPositive?.invoke(null)
                        })
            }
            negativeButon?.let {
                builder.setNegativeButton(negativeButon,
                        { _, _ ->
                            callbackNegative?.invoke(null)
                        })
            }
            builder.setCancelable(allowCancelWhenTouchingOutside)
            builder.create().show()
        }

    }

    fun calculateNoOfColumnsWithItemWidth(itemWidth: Int, margins: Int): Int {
        //take into account margins
        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels - margins
        return (screenWidth / itemWidth)
    }

    fun calculateNoOfColumns(): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 180).toInt()
    }

    fun calculateNoOfColumns(reductionRate: Float): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels * reductionRate / displayMetrics.density
        return (dpWidth / 180).toInt()
    }

    fun getWidthInPixels() = context.resources.displayMetrics.widthPixels

    fun getHeightInPixels() = context.resources.displayMetrics.heightPixels

    fun dpFromPx(px: Float): Float {
        return px / context.resources.displayMetrics.density
    }

    fun pxFromDp(dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

}