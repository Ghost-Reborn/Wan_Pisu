package in.ghostreborn.wanpisu.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.ghostreborn.wanpisu.R;
import in.ghostreborn.wanpisu.constants.WanPisuConstants;
import in.ghostreborn.wanpisu.ui.EpisodeSelectActivity;

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

            WanPisuConstants.ALL_ANIME_TOTAL_EPISODES = Integer.parseInt(WanPisuConstants.allAnimes.get(position).getAnimeAvailableEpisodes());
            WanPisuConstants.wanPisuSharedPreference.edit()
                    .putString(
                            WanPisuConstants.WAN_PISU_PREFERENCE_ANIME_ID,
                            WanPisuConstants.allAnimes.get(position).getAnimeID()
                    )
                    .putString(
                            WanPisuConstants.WAN_PISU_PREFERENCE_ANIME_MAL_ID,
                            WanPisuConstants.allAnimes.get(position).getAnimeMalID()
                    )
                    .apply();
            WanPisuConstants.ALL_ANIME_POSITION = position;

            setupJikanEpisodes(Integer.parseInt(
                    WanPisuConstants
                            .allAnimes
                            .get(position)
                            .getAnimeAvailableEpisodes()
                    )
            );

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

    private void setupJikanEpisodes(int lastEpisode) {
        WanPisuConstants.jikanEpisodes = new ArrayList<>();

        int startEpisode = 1;
        double total = Math.floor(lastEpisode / 100);
        double remainingEpisodes = total % 100;
        if (remainingEpisodes != 0) {
            total += 1;
        }

        for (int i = 1; i <= total; i++) {
            if (i != total) {
                WanPisuConstants.jikanEpisodes.add(startEpisode + "-" + (startEpisode + 99));
            } else {
                WanPisuConstants.jikanEpisodes.add(startEpisode + "-" + lastEpisode);
            }
            startEpisode += 100;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView animeTextView;
        public ImageView animeImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            animeTextView = itemView.findViewById(R.id.jikan_anime_name);
            animeImageView = itemView.findViewById(R.id.jikan_anime_image);
        }
    }

}
