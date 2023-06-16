package in.ghostreborn.wanpisu.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import in.ghostreborn.wanpisu.EpisodeSelectActivity;
import in.ghostreborn.wanpisu.R;
import in.ghostreborn.wanpisu.constants.WanPisuConstants;

public class AllAnimeAdapter extends RecyclerView.Adapter<AllAnimeAdapter.ViewHolder> {
    @NonNull
    @Override
    public AllAnimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_anime_list, parent, false);
        return new AllAnimeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllAnimeAdapter.ViewHolder holder, int position) {
        holder.animeTextView.setText(WanPisuConstants.allAnimes.get(position).getAnimeName());
        Picasso.get().load(WanPisuConstants.allAnimes.get(position).getAnimeThumbnail())
                        .into(holder.animeImageView);
        holder.itemView.setOnClickListener(view -> {
            WanPisuConstants.wanPisuSharedPreference.edit()
                    .putString(
                            WanPisuConstants.WAN_PISU_PREFERENCE_ANIME_ID,
                            WanPisuConstants.allAnimes.get(position).getAnimeID()
                    ).apply();
            holder.itemView.getContext().startActivity(
                    new Intent(
                            holder.itemView.getContext(),
                            EpisodeSelectActivity.class
                    )
            );
        });
    }

    @Override
    public int getItemCount() {
        return WanPisuConstants.allAnimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView animeTextView;
        public ImageView animeImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            animeTextView = itemView.findViewById(R.id.all_anime_name);
            animeImageView = itemView.findViewById(R.id.all_anime_image);
        }
    }
}
