package in.ghostreborn.wanpisu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.ghostreborn.wanpisu.JikanAsync;
import in.ghostreborn.wanpisu.R;
import in.ghostreborn.wanpisu.constants.WanPisuConstants;

public class JikanGroupAdapter extends RecyclerView.Adapter<JikanGroupAdapter.ViewHolder> {

    @Override
    public JikanGroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jikan_group_list, parent, false);
        return new JikanGroupAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JikanGroupAdapter.ViewHolder holder, int position) {
            holder.jikanGroupTextView.setText(
                    WanPisuConstants.jikanEpisodes.get(position)
            );
            holder.itemView.setOnClickListener(view -> {
                WanPisuConstants.ALL_ANIME_EPISODE_ADD = position * 100;
                new JikanAsync(holder.itemView.getContext(), position + 1).execute();
            });
    }

    @Override
    public int getItemCount() {
        return WanPisuConstants.jikanEpisodes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView jikanGroupTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jikanGroupTextView = itemView.findViewById(R.id.jikan_group_text_view);
        }
    }

}
