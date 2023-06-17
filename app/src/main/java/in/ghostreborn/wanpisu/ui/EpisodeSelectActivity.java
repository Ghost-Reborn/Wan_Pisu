package in.ghostreborn.wanpisu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import in.ghostreborn.wanpisu.JikanAsync;
import in.ghostreborn.wanpisu.R;
import in.ghostreborn.wanpisu.adapter.AllAnimeAdapter;
import in.ghostreborn.wanpisu.adapter.JikanAdapter;
import in.ghostreborn.wanpisu.adapter.JikanGroupAdapter;
import in.ghostreborn.wanpisu.constants.WanPisuConstants;
import in.ghostreborn.wanpisu.model.Jikan;
import in.ghostreborn.wanpisu.parser.JikanParser;

public class EpisodeSelectActivity extends AppCompatActivity {

    public static RecyclerView jikanContainer;
    public static RecyclerView jikanGroupContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_select);

        jikanContainer = findViewById(R.id.jikan_container);
        jikanGroupContainer = findViewById(R.id.jikan_group_container);

        JikanAsync async = new JikanAsync(this, 1);
        async.execute();

    }

}