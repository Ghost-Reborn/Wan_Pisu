package in.ghostreborn.wanpisu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import in.ghostreborn.wanpisu.R;
import in.ghostreborn.wanpisu.adapter.AllAnimeAdapter;
import in.ghostreborn.wanpisu.adapter.JikanAdapter;
import in.ghostreborn.wanpisu.constants.WanPisuConstants;
import in.ghostreborn.wanpisu.parser.JikanParser;

public class EpisodeSelectActivity extends AppCompatActivity {

    RecyclerView jikanContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_select);

        jikanContainer = findViewById(R.id.jikan_container);

        JikanAsync async = new JikanAsync();
        async.execute();

    }

    class JikanAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            JikanParser.parseJikan(
                    WanPisuConstants.JIKAN_EPISODES_URL_HEAD +
                    WanPisuConstants.wanPisuSharedPreference.getString(WanPisuConstants.WAN_PISU_PREFERENCE_ANIME_MAL_ID, "21") +
                            WanPisuConstants.JIKAN_EPISODES_URL_TAIL
            );
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
            jikanContainer.setLayoutManager(manager);
            JikanAdapter adapter = new JikanAdapter();
            jikanContainer.setAdapter(adapter);
        }
    }

}