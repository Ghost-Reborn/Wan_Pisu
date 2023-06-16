package in.ghostreborn.wanpisu.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.ghostreborn.wanpisu.ui.ExoPlayerActivity;
import in.ghostreborn.wanpisu.R;
import in.ghostreborn.wanpisu.constants.WanPisuConstants;
import in.ghostreborn.wanpisu.model.Jikan;

public class JikanAdapter extends RecyclerView.Adapter<JikanAdapter.ViewHolder> {
    @NonNull
    @Override
    public JikanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jikan_anime_list, parent, false);
        return new JikanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JikanAdapter.ViewHolder holder, int position) {
        Picasso.get().load(WanPisuConstants.allAnimes.get(WanPisuConstants.ALL_ANIME_POSITION).getAnimeThumbnail())
                .into(holder.jikanAnimeImage);
        ArrayList<Jikan> jikans = WanPisuConstants.jikans;
        holder.jikanAnimeName.setText(jikans.get(position).getAnimeTitle());
        holder.jikanAnimeScore.setText("Rating: " + jikans.get(position).getAnimeScore());
        holder.jikanAnimeAired.setText("Aired: " + jikans.get(position).getAnimeAired());
        holder.jikanAnimeFiller.setText("Filler: " + jikans.get(position).isAnimeFiller() + "");
        holder.jikanAnimeRecap.setText("Recap: " + jikans.get(position).isAnimeRecap() + "");

        holder.itemView.setOnClickListener(view -> {
            WanPisuConstants.ALL_ANIME_EPISODE_NUMBER = position + 1;
            Log.e("EPISODES", "TOTAL EPISODES: " + WanPisuConstants.ALL_ANIME_TOTAL_EPISODES);
            holder.itemView.getContext()
                    .startActivity(
                            new Intent(
                                    holder.itemView.getContext(),
                                    ExoPlayerActivity.class
                            )
                    );

        });

    }

    @Override
    public int getItemCount() {
        return WanPisuConstants.jikans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView jikanAnimeName;
        public TextView jikanAnimeScore;
        public TextView jikanAnimeAired;
        public TextView jikanAnimeFiller;
        public TextView jikanAnimeRecap;
        public ImageView jikanAnimeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jikanAnimeName = itemView.findViewById(R.id.jikan_anime_name);
            jikanAnimeScore = itemView.findViewById(R.id.jikan_anime_score);
            jikanAnimeAired = itemView.findViewById(R.id.jikan_anime_aired);
            jikanAnimeFiller = itemView.findViewById(R.id.jikan_anime_filler);
            jikanAnimeRecap = itemView.findViewById(R.id.jikan_anime_recap);
            jikanAnimeImage = itemView.findViewById(R.id.jikan_anime_image);
        }
    }
}
