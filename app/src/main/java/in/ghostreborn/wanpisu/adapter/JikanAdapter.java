package in.ghostreborn.wanpisu.adapter;

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
        holder.jikanAnimeScore.setText(jikans.get(position).getAnimeScore());
        holder.jikanAnimeAired.setText(jikans.get(position).getAnimeAired());
        holder.jikanAnimeFiller.setText(jikans.get(position).isAnimeFiller() + "");
        holder.jikanAnimeRecap.setText(jikans.get(position).isAnimeRecap() + "");
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
