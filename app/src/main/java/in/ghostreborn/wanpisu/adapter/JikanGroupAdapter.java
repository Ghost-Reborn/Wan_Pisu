package in.ghostreborn.wanpisu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.ghostreborn.wanpisu.R;

public class JikanGroupAdapter extends RecyclerView.Adapter<JikanGroupAdapter.ViewHolder> {

    int startEpisode = 1;
    int lastEpisode;
    double total = 0;
    double remainingEpisodes = 0;

    public JikanGroupAdapter(int lastEpisode) {
        this.lastEpisode = lastEpisode;
        total = Math.floor(lastEpisode/100);
        remainingEpisodes = total % 100;
        if (remainingEpisodes != 0){
            total += 1;
        }
    }

    @Override
    public JikanGroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jikan_group_list, parent, false);
        return new JikanGroupAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JikanGroupAdapter.ViewHolder holder, int position) {
        if (position != (total - 1)){
            holder.jikanGroupTextView.setText(
                    startEpisode + "-" + (startEpisode + 99)
            );
        }else {
            holder.jikanGroupTextView.setText(
                    startEpisode + "-" + lastEpisode
            );
        }
        startEpisode += 100;
    }

    @Override
    public int getItemCount() {
        return (int)total;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView jikanGroupTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jikanGroupTextView = itemView.findViewById(R.id.jikan_group_text_view);
        }
    }

}
