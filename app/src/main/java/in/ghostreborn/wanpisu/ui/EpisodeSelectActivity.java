package in.ghostreborn.wanpisu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import in.ghostreborn.wanpisu.R;
import in.ghostreborn.wanpisu.constants.WanPisuConstants;
import in.ghostreborn.wanpisu.parser.JikanParser;

public class EpisodeSelectActivity extends AppCompatActivity {

    TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_select);

        testText = findViewById(R.id.test_text);
        testText.setText(
                WanPisuConstants.wanPisuSharedPreference
                        .getString(
                                WanPisuConstants.WAN_PISU_PREFERENCE_ANIME_ID,
                                "NOT FOUND"
                        )
        );
        JikanAsync async = new JikanAsync();
        async.execute();

    }

    class JikanAsync extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            return JikanParser.parseJikan(
                    WanPisuConstants.JIKAN_EPISODES_URL_HEAD +
                    WanPisuConstants.wanPisuSharedPreference.getString(WanPisuConstants.WAN_PISU_PREFERENCE_ANIME_MAL_ID, "21") +
                            WanPisuConstants.JIKAN_EPISODES_URL_TAIL
            );
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            testText.setText(s);
        }
    }

}