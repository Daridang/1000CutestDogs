package work.home.k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details2.*
import work.home.R

class Details2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details2)

        Glide.with(this)
                .load(intent.getStringExtra("url"))
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .into(img_id)
    }
}
