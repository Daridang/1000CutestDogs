package work.home.k

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*
import work.home.R

/**
 * Created by
+-+-+-+-+-+-+-+-+
|D|a|r|i|d|a|n|g|
+-+-+-+-+-+-+-+-+
on 2019-11-22.
 */
class DataAdapter(private val urls: ArrayList<String>) : RecyclerView.Adapter<DataAdapter
.PictureHolder>
() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val inflateView = parent.inflate(R.layout.list_item, false)
        return PictureHolder(inflateView)
    }

    override fun getItemCount() = urls.size

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        val url = urls[position]
        holder.bindPicture(url)
    }

    class PictureHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var url: String = ""

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = v.context
            val detailsIntent = Intent(context, Details2Activity::class.java)
            detailsIntent.putExtra("url", url)
            context.startActivity(detailsIntent)
        }

        fun bindPicture(url: String) {
            this.url = url
            Glide.with(view.context)
                    .load(url)
                    .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                    .into(view.thumbnail)
        }
    }

}