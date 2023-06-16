package in.ghostreborn.wanpisu.parser;

import android.util.Log;

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

public class JikanParser {
    public static String parseJikan(String jikanURL) {
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

            return parseEpisodes(response.toString());

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "ERROR";
    }

    public static String parseEpisodes(String rawJSON){
        try{
            JSONObject jikanObject = new JSONObject(rawJSON);
            JSONArray dataArray = jikanObject.optJSONArray("data");

            if (dataArray == null){
                return "NO EPISODE DATA";
            }else if (dataArray.length() == 0){
                return "NO EPISODE DATA";
            }

            return dataArray.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";

    }

}
