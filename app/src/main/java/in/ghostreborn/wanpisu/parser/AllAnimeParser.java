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

import in.ghostreborn.wanpisu.constants.WanPisuConstants;
import in.ghostreborn.wanpisu.model.AllAnime;

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
                String animeID = animeObject.getString("_id");
                String animeName = animeObject.getString("name");
                String englishName = animeObject.getString("englishName");
                String availableEpisodes = animeObject.optJSONObject("availableEpisodes")
                        .getString("sub");
                String type = animeObject.getString("__typename");
                String malID = animeObject.getString("malId");
                String animeThumbnail = animeObject.getString("thumbnail");
                String lastEpisodeInfo = "null";
                if (animeObject.optJSONObject("lastEpisodeInfo").has("sub")) {
                    lastEpisodeInfo = animeObject.optJSONObject("lastEpisodeInfo")
                            .optJSONObject("sub")
                            .getString("episodeString");
                }
                String lastEpisodeYear = animeObject.optJSONObject("lastEpisodeDate")
                        .optJSONObject("sub")
                        .getString("year");
                Log.e("MAL_ID", "ANIME_NAME: " + animeName + "\tMAL_ID: " + malID);
                WanPisuConstants.allAnimes.add(
                        new AllAnime(
                                animeID,
                                animeName,
                                englishName,
                                availableEpisodes,
                                type,
                                malID,
                                animeThumbnail,
                                lastEpisodeInfo,
                                lastEpisodeYear
                        )
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getAllAnimeServer(String serverURL) {
        try {
            URL url = new URL(serverURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return getBackupServer(response.toString());

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "ERROR";
    }

    private static String getBackupServer(String serverJSON) {
        try {
            JSONObject baseJSON = new JSONObject(serverJSON);
            JSONArray sourceURLS = baseJSON
                    .optJSONObject("data")
                    .optJSONObject("episode")
                    .getJSONArray("sourceUrls");
            for (int i = 0; i < sourceURLS.length(); i++) {
                if (sourceURLS.get(i).toString().contains("workfields.backup-server")) {
                    JSONObject urlObject = sourceURLS.getJSONObject(i);
                    return urlObject.getString("sourceUrl");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "ERROR";

    }

}
