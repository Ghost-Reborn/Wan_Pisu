package in.ghostreborn.wanpisu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import in.ghostreborn.wanpisu.constants.WanPisuConstants;

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

    }
}