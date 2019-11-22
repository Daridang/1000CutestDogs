package work.home.j;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.home.R;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.full_screen_img_id)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        String url = Objects.requireNonNull(getIntent().getExtras()).getString("url");
        Glide.with(this).load(url).into(imageView);
    }
}
