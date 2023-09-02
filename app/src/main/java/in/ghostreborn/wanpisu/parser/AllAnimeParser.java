package in.ghostreborn.wanpisu.parser;

import android.net.Uri;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AllAnimeParser {

    private static String getAllAnimeJSON(String animeName) {
        OkHttpClient client = new OkHttpClient();

        String baseUrl = "https://api.allanime.day/api";
        String queryUrl = baseUrl + "?variables=" +
                Uri.encode("{\"search\":{\"allowAdult\":false,\"allowUnknown\":false,\"query\":\"" +
                        animeName +
                        "\"},\"limit\":40,\"page\":1,\"translationType\":\"sub\",\"countryOrigin\":\"ALL\"}") +
                "&query=" +
                Uri.encode("query($search:SearchInput,$limit:Int,$page:Int,$translationType:VaildTranslationTypeEnumType,$countryOrigin:VaildCountryOriginEnumType){shows(search:$search,limit:$limit,page:$page,translationType:$translationType,countryOrigin:$countryOrigin){edges{_id,name,englishName,availableEpisodes,__typename,malId,thumbnail,lastEpisodeInfo,lastEpisodeDate,season,airedStart,episodeDuration,episodeCount,lastUpdateEnd}}}");

        Request request = new Request.Builder()
                .url(queryUrl)
                .header("Referer", "https://allanime.to")
                .header("Cipher", "AES256-SHA256")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; rv:109.0) Gecko/20100101 Firefox/109.0")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "NULL";

    }

    public static void parseAllAnime(String anime) {

        String JSON = getAllAnimeJSON(anime);

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

    public static String getAllAnimeServer(String showID) {

        String serverURL = "https://embed.ssbcontent.site/apivtwo/clock.json?id=" + decryptAllAnime(showID);
        Log.e("TAG", serverURL);

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

            JSONObject jsonObject = new JSONObject(String.valueOf(response));
            JSONArray links = jsonObject.getJSONArray("links");
            JSONObject linkObject = links.getJSONObject(0);
            String link = linkObject.getString("link");

            if (linkObject.has("mp4")){
                WanPisuConstants.isHLS = !linkObject.getBoolean("mp4");
            }else {
                // TODO check HLS key exists in response
                WanPisuConstants.isHLS = true;
            }

            return link;

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static String decryptAllAnime(String showID) {

        // Connect and get encrypted url

        OkHttpClient client = new OkHttpClient();

        String baseUrl = "https://api.allanime.day/api";
        String queryUrl = baseUrl + "?variables=" +
                Uri.encode("{\"showId\":\"" +
                        showID +
                        "\",\"translationType\":\"sub\",\"episodeString\":\"1\"}") +
                "&query=" +
                Uri.encode("query($showId:String!,$translationType:VaildTranslationTypeEnumType!,$episodeString:String!){episode(showId:$showId,translationType:$translationType,episodeString:$episodeString){episodeString,sourceUrls}}");

        Request request = new Request.Builder()
                .url(queryUrl)
                .header("Referer", "https://allanime.to")
                .header("Cipher", "AES256-SHA256")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; rv:109.0) Gecko/20100101 Firefox/109.0")
                .build();

        try {
            Response response = client.newCall(request).execute();
            String rawJSON = response.body().string();
            JSONObject jsonObject = new JSONObject(rawJSON);
            JSONArray sourceURLs = jsonObject.getJSONObject("data")
                    .getJSONObject("episode")
                    .getJSONArray("sourceUrls");

            for (int i = 0; i < sourceURLs.length(); i++) {
                String decrypted = decryptAllAnimeServer(
                        sourceURLs.getJSONObject(i).getString("sourceUrl").substring(2)
                );
                if (decrypted.contains("apivtwo")){
                    decrypted = decrypted.substring(18);
                    Log.e("TAG", "Decrypted: " + decrypted);
                    return decrypted;
                }
            }

            return "NULL";

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return "NULL";

    }

    private static String decryptAllAnimeServer(String decrypt) {
        StringBuilder decryptedString = new StringBuilder();

        for (int i = 0; i < decrypt.length(); i += 2) {
            String hex = decrypt.substring(i, i + 2);
            int dec = Integer.parseInt(hex, 16);
            int xor = dec ^ 56;
            String oct = String.format("%03o", xor);
            char decryptedChar = (char) Integer.parseInt(oct, 8);
            decryptedString.append(decryptedChar);
        }

        return decryptedString.toString();
    }

}
