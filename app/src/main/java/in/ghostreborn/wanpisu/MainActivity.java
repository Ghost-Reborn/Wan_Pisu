package in.ghostreborn.wanpisu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testText = findViewById(R.id.test_text);
        WanPisuConstants.allAnimes = new ArrayList<>();
        AllAnimeAsync animeAsync = new AllAnimeAsync();
        animeAsync.execute();

    }
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
        MainActivity.testText.setText(WanPisuConstants.allAnimes.get(0).animeName);
    }
}