package work.home.k

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by
+-+-+-+-+-+-+-+-+
|D|a|r|i|d|a|n|g|
+-+-+-+-+-+-+-+-+
on 2019-11-22.
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRood: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRood)
}