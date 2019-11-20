package work.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.home.adapter.DataAdapter;
import work.home.interfaces.ItemClickListener;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private List<String> list = new ArrayList<>();

    @BindView(R.id.recyclerView_id)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }

        loadUrls();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        if (!isNetworkConnected()) {
            Snackbar.make(findViewById(android.R.id.content), "No internet connection",
                    Snackbar.LENGTH_LONG).show();
        }

        DataAdapter adapter = new DataAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    public void loadUrls() {
        String line;
        try {
            InputStreamReader reader = new InputStreamReader(getAssets().open("dog_urls.json"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.getProperty("line.separator"));
            }

            line = stringBuilder.toString();
            bufferedReader.close();
            reader.close();

            JSONObject object = new JSONObject(line);
            JSONArray array = object.getJSONArray("urls");
            int size = array.length();
            for (int i = 0; i < size; i++) {
                list.add(array.getString(i));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return Objects.requireNonNull(cm).getActiveNetworkInfo() != null
                && Objects.requireNonNull(cm.getActiveNetworkInfo()).isConnected();
    }

    @Override
    public void onClick(View v, String url) {
        Intent i = new Intent(this, DetailsActivity.class);
        i.putExtra("url", url);
        startActivity(i);
    }
}
