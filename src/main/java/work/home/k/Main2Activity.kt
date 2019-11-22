package work.home.k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONObject
import work.home.R
import java.io.BufferedReader

class Main2Activity : AppCompatActivity() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: DataAdapter

    private var urlList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView_id.layoutManager = gridLayoutManager

        loadUrls()

        adapter = DataAdapter(urlList)
        recyclerView_id.adapter = adapter
    }

    private fun loadUrls() {
        val bufferedReader: BufferedReader = assets.open("dog_urls.json").bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val urls = JSONObject(inputString).getJSONArray("urls")

        val strBuilder: StringBuilder = StringBuilder()

        for (i in 0 until urls.length()) {
            urlList.add(urls.getString(i))
            strBuilder.append(urls.getString(i)).append(System.getProperty("line.separator"))
        }

    }
}
