package in.ghostreborn.wanpisu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ghostreborn.wanpisu.adapter.AllAnimeAdapter;
import in.ghostreborn.wanpisu.constants.WanPisuConstants;
import in.ghostreborn.wanpisu.parser.AllAnimeParser;

public class MainActivity extends AppCompatActivity {

    static RecyclerView allAnimeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allAnimeContainer = findViewById(R.id.all_anime_container);
        WanPisuConstants.allAnimes = new ArrayList<>();
        AllAnimeAsync animeAsync = new AllAnimeAsync();
        animeAsync.execute();

    }

    class AllAnimeAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            AllAnimeParser.parseAllAnime(WanPisuConstants.ALL_ANIME_QUERY_HEAD + WanPisuConstants.ALL_ANIME_QUERY_TAIL);
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
            allAnimeContainer.setLayoutManager(gridLayoutManager);
            AllAnimeAdapter adapter = new AllAnimeAdapter();
            allAnimeContainer.setAdapter(adapter);
        }
    }

}