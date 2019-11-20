package work.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.home.MainActivity;
import work.home.R;
import work.home.interfaces.ItemClickListener;


/**
 * Created by
 * +-+-+-+-+-+-+-+-+
 * |D|a|r|i|d|a|n|g|
 * +-+-+-+-+-+-+-+-+
 * on 2019-11-19.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.PictureViewHolder> {

    private ItemClickListener listener;
    private List<String> list;

    public DataAdapter(ItemClickListener listener, List<String> list) {
        this.listener = listener;
        this.list = list;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PictureViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        String url = list.get(position);
        Glide.with((MainActivity) listener)
                .load(url)
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .into(holder.img);
        holder.textView.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.thumbnail)
        ImageView img;

        @BindView(R.id.card_nr_txt_id)
        TextView textView;

        PictureViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) listener.onClick(v, list.get(getAdapterPosition()));
        }
    }
}
