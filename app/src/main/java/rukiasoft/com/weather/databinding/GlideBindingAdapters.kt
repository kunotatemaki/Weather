package rukiasoft.com.weather.databinding


import android.databinding.BindingAdapter
import android.graphics.Typeface
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File


/**
 * Created by Roll on 31/8/17.
 * binding adapters to use glide on xml with data binding
 */
@Suppress("unused")
class GlideBindingAdapters {

    @BindingAdapter("imageUrl")
    fun setImageUrl(view: ImageView, url: String?) {
        //circle images
        url?.let {
            Glide.with(view.context)
                    .load(url)
                    .into(view)
        }
    }

    @BindingAdapter("imageRounded")
    fun setImageUrlRounded(view: ImageView, url: String?) {
        //circle images
        url?.let {
            Glide.with(view.context)
                    .load(url)
                    .apply(RequestOptions()
                            .circleCrop())
                    .into(view)
        }
    }

    @BindingAdapter("imageCenterCropped")
    fun setImageUrlCenterAndCropped(view: ImageView, url: String?) {
        //cropped images
        url?.let {
            Glide.with(view.context)
                    .load(url)
                    .apply(RequestOptions()
                            .centerCrop())
                    .into(view)
        }
    }

    @BindingAdapter("imageCenterCropped")
    fun setImageFileCenterAndCropped(view: ImageView, file: File?) {
        //cropped images
        file?.let {
            Glide.with(view.context)
                    .load(file)
                    .apply(RequestOptions()
                            .centerCrop())
                    .into(view)
        }
    }

    @BindingAdapter("imageCenterCropped")
    fun setImageUriCenterAndCropped(view: ImageView, uri: Uri?) {
        //cropped images
        uri?.let {
            Glide.with(view.context)
                    .load(uri)
                    .apply(RequestOptions()
                            .centerCrop())
                    .into(view)
        }
    }

    @BindingAdapter("isVisibleOrGone")
    fun <T> setIsVisibleOrGone(view: View, isVisible: T?) {
        isVisible?.let {
            if (isVisible is Boolean && isVisible == true) {
                view.visibility = View.VISIBLE
                return
            } else if (isVisible is String? && isVisible.isNotBlank()) {
                view.visibility = View.VISIBLE
                return
            }
        }
        view.visibility = View.GONE
    }

    @BindingAdapter("isVisibleOrInvisible")
    fun <T> setIsVisibleOrInvisible(view: View, isVisible: T?) {
        isVisible?.let {
            if (isVisible is Boolean && isVisible == true) {
                view.visibility = View.VISIBLE
                return
            } else if (isVisible is String? && isVisible.isNotBlank()) {
                view.visibility = View.VISIBLE
                return
            }
        }
        view.visibility = View.INVISIBLE
    }

    @BindingAdapter("isBoldOrNot")
    fun <T> setIsBoldOrNot(textView: TextView, isBold: T?) {
        isBold?.let {
            if (isBold is Boolean && isBold == true) {
                textView.setTypeface(textView.typeface, Typeface.BOLD)
                return
            }
        }
        textView.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
    }

    @BindingAdapter("setBackground")
    fun <T> setBackground(view: View, background: T?) {
        background?.let {
            if (it is Int) {
                view.setBackgroundResource(it)
            }
        }
    }

    @BindingAdapter("setBackground")
    fun <T> setBackgroundOnTextView(textView: View, background: T?) {
        background?.let {
            if (it is Int) {
                textView.setBackgroundResource(it)
            }
        }
    }
}