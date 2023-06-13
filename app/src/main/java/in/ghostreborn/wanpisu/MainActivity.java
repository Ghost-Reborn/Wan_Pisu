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

public class MainActivity extends AppCompatActivity {

    static TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testText = findViewById(R.id.test_text);
        AllAnimeAsync animeAsync = new AllAnimeAsync();
        animeAsync.execute();

    }
}

class AllAnimeAsync extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        return getJSONData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MainActivity.testText.setText(s);
    }

    private String getJSONData(){
        try{
            URL url = new URL(
                    WanPisuConstants.ALL_ANIME_QUERY_HEAD +
                            WanPisuConstants.ALL_ANIME_QUERY_TAIL
            );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ERROR";

    }
}