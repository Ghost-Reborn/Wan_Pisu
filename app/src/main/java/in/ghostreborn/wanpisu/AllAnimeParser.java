package in.ghostreborn.wanpisu;

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

public class AllAnimeParser {

    public static String getAllAnimeJSON(String allAnimeURL){
        try{
            URL url = new URL(allAnimeURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return parseAllAnime(response.toString());
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "ERROR";

    }

    public static String parseAllAnime(String JSON){
        StringBuilder parsed = new StringBuilder();
        try{
            JSONObject allAnimeBaseJSON = new JSONObject(JSON);
            JSONArray edgesArray = allAnimeBaseJSON
                    .getJSONObject("data")
                    .getJSONObject("shows")
                    .getJSONArray("edges");
            for (int i=0;i<edgesArray.length();i++){
                JSONObject animeObject = edgesArray.optJSONObject(i);
                String animeName = animeObject.getString("name");
                parsed.append(animeName);
                parsed.append("\n\n");
            }
            return parsed.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

}
