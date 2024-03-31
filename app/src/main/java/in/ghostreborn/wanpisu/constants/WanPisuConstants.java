package in.ghostreborn.wanpisu.constants;

import android.content.SharedPreferences;

import java.util.ArrayList;

import in.ghostreborn.wanpisu.model.AllAnime;
import in.ghostreborn.wanpisu.model.Jikan;

public class WanPisuConstants {

    public static final String JIKAN_EPISODES_URL_HEAD = "https://api.jikan.moe/v4/anime/";
    public static final String JIKAN_EPISODES_URL_TAIL = "/episodes?page=";

    public static ArrayList<AllAnime> allAnimes;
    public static ArrayList<Jikan> jikans;
    public static ArrayList<String> jikanEpisodes;

    public static boolean hasNoEpisodeData = false;
    public static boolean isHLS = true;
    public static int ALL_ANIME_POSITION = 0;
    public static int ALL_ANIME_EPISODE_NUMBER = 0;
    public static int ALL_ANIME_TOTAL_EPISODES = 0;
    public static int ALL_ANIME_EPISODE_ADD = 0;
    public static int JIKAN_LAST_PAGE = 0;

    public static final String WAN_PISU_PREFERENCE = "WAN_PISU";
    public static final String WAN_PISU_PREFERENCE_ANIME_ID = "ANIME_ID";
    public static final String WAN_PISU_PREFERENCE_ANIME_NAME = "ANIME_NAME";
    public static final String WAN_PISU_PREFERENCE_ANIME_MAL_ID = "MAL_ID";
    public static SharedPreferences wanPisuSharedPreference;

}
