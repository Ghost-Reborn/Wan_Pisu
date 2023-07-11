package in.ghostreborn.wanpisu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        SearchView animeSearchView = findViewById(R.id.anime_search_view);

        // Default selection is Home
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.menu_home);
        menuItem.setChecked(true);

        initialSetup();

        AllAnimeAsync animeAsync = new AllAnimeAsync("");
        animeAsync.execute();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        animeSearchView.setVisibility(View.GONE);
                        AllAnimeAsync animeAsync = new AllAnimeAsync("");
                        animeAsync.execute();
                        return true;
                    case R.id.menu_search:
                        animeSearchView.setVisibility(View.VISIBLE);
                        return true;
                    case R.id.menu_settings:
                        return true;
                }
                return false;
            }
        });

        animeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                AllAnimeAsync animeAsync = new AllAnimeAsync(animeSearchView.getQuery().toString());
                animeAsync.execute();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    private void initialSetup() {
        WanPisuConstants.allAnimes = new ArrayList<>();
        WanPisuConstants.wanPisuSharedPreference = getSharedPreferences(
                WanPisuConstants.WAN_PISU_PREFERENCE,
                MODE_PRIVATE
        );
    }

    class AllAnimeAsync extends AsyncTask<Void, Void, Void> {

        String animeName;
        public AllAnimeAsync(String animeName){
            this.animeName = animeName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            WanPisuConstants.allAnimes.clear();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            AllAnimeParser.parseAllAnime(
                    animeName
            );
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