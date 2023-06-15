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

import in.ghostreborn.wanpisu.model.AllAnime;
import in.ghostreborn.wanpisu.constants.WanPisuConstants;

public class AllAnimeParser {

    private static String getAllAnimeJSON(String allAnimeURL) {
        try {
            URL url = new URL(allAnimeURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "ERROR";
    }

    public static void parseAllAnime(String allAnimeURL) {

        String JSON = getAllAnimeJSON(allAnimeURL);

        try {
            JSONObject allAnimeBaseJSON = new JSONObject(JSON);
            JSONArray edgesArray = allAnimeBaseJSON
                    .getJSONObject("data")
                    .getJSONObject("shows")
                    .getJSONArray("edges");
            for (int i = 0; i < edgesArray.length(); i++) {
                JSONObject animeObject = edgesArray.optJSONObject(i);
                String animeName = animeObject.getString("name");
                String animeThumbnail = animeObject.getString("thumbnail");
                WanPisuConstants.allAnimes.add(
                        new AllAnime(
                                animeName,
                                animeThumbnail
                        )
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
