package in.ghostreborn.wanpisu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

class AllAnimeAsync extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        return AllAnimeParser.getAllAnimeJSON(
                WanPisuConstants.ALL_ANIME_QUERY_HEAD +
                        WanPisuConstants.ALL_ANIME_QUERY_TAIL
        );
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MainActivity.testText.setText(s);
    }
}