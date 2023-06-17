package in.ghostreborn.wanpisu.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import in.ghostreborn.wanpisu.constants.WanPisuConstants;
import in.ghostreborn.wanpisu.model.Jikan;

public class JikanParser {
    public static void parseJikan(String jikanURL) {
        try {
            URL url = new URL(jikanURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            parseEpisodes(response.toString());

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void parseEpisodes(String rawJSON) {

        WanPisuConstants.hasNoEpisodeData = false;
        WanPisuConstants.jikans = new ArrayList<>();

        try {
            JSONObject jikanObject = new JSONObject(rawJSON);
            JSONArray dataArray = jikanObject.optJSONArray("data");
            JSONObject paginationObject = jikanObject.optJSONObject("pagination");
            WanPisuConstants.JIKAN_LAST_PAGE = paginationObject.getInt("last_visible_page");

            if (dataArray == null) {
                WanPisuConstants.hasNoEpisodeData = true;
            } else if (dataArray.length() == 0) {
                WanPisuConstants.hasNoEpisodeData = true;
            }

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject dataObject = dataArray.getJSONObject(i);
                String title = dataObject.getString("title");
                String score = dataObject.getString("score");
                String aired = dataObject.getString("aired");
                boolean isFiller = dataObject.getBoolean("filler");
                boolean isRecap = dataObject.getBoolean("recap");

                WanPisuConstants.jikans.add(new Jikan(
                        title,
                        score,
                        aired,
                        isFiller,
                        isRecap
                ));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
