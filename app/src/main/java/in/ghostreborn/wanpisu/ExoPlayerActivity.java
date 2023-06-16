package in.ghostreborn.wanpisu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import in.ghostreborn.wanpisu.constants.WanPisuConstants;
import in.ghostreborn.wanpisu.parser.AllAnimeParser;

public class ExoPlayerActivity extends AppCompatActivity {

    static TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);

        testText = findViewById(R.id.test_text);
        new AllAnimeAsync().execute();

    }

    class AllAnimeAsync extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            return AllAnimeParser.getAllAnimeServer(
                    WanPisuConstants.ALL_ANIME_SERVER_HEAD +
                            WanPisuConstants.wanPisuSharedPreference.getString(WanPisuConstants.WAN_PISU_PREFERENCE_ANIME_ID, "") +
                            WanPisuConstants.ALL_ANIME_SERVER_TAIL
            );
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            testText.setText(s);
        }
    }

}