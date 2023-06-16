package in.ghostreborn.wanpisu.constants;

import android.content.SharedPreferences;

import java.util.ArrayList;

import in.ghostreborn.wanpisu.model.AllAnime;
import in.ghostreborn.wanpisu.model.Jikan;

public class WanPisuConstants {

    public static final String ALL_ANIME_QUERY_HEAD = "https://api.allanime.to/allanimeapi?variables={%22search%22:{%22allowAdult%22:false,%22allowUnknown%22:false,%22query%22:%22";
    public static final String ALL_ANIME_QUERY_TAIL = "%22},%22limit%22:39,%22page%22:1,%22translationType%22:%22sub%22,%22countryOrigin%22:%22JP%22}&query=query($search:SearchInput,$limit:Int,$page:Int,$translationType:VaildTranslationTypeEnumType,$countryOrigin:VaildCountryOriginEnumType){shows(search:$search,limit:$limit,page:$page,translationType:$translationType,countryOrigin:$countryOrigin){edges{_id,name,availableEpisodes,__typename,malId,thumbnail,lastEpisodeInfo,lastEpisodeDate,season,airedStart,episodeDuration,episodeCount,lastUpdateEnd}}}";
    public static final String ALL_ANIME_SERVER_HEAD = "https://api.allanime.to/allanimeapi?variables={%22showId%22:%22";
    public static final String ALL_ANIME_SERVER_MIDDLE = "\",\"translationType\":\"sub\",\"episodeString\":\"";
    public static final String ALL_ANIME_SERVER_TAIL = "\"}&query=query($showId:String!,$translationType:VaildTranslationTypeEnumType!,$episodeString:String!){episode(showId:$showId,translationType:$translationType,episodeString:$episodeString){episodeString,notes,thumbnail,sourceUrls}}";
    public static final String JIKAN_EPISODES_URL_HEAD = "https://api.jikan.moe/v4/anime/";
    public static final String JIKAN_EPISODES_URL_TAIL = "/episodes";

    public static ArrayList<AllAnime> allAnimes;
    public static ArrayList<Jikan> jikans;

    public static boolean hasNoEpisodeData = false;
    public static boolean isHLS = false;
    public static int ALL_ANIME_POSITION = 0;
    public static int ALL_ANIME_EPISODE_NUMBER = 0;

    public static final String WAN_PISU_PREFERENCE = "WAN_PISU";
    public static final String WAN_PISU_PREFERENCE_ANIME_ID = "ANIME_ID";
    public static final String WAN_PISU_PREFERENCE_ANIME_MAL_ID = "MAL_ID";
    public static SharedPreferences wanPisuSharedPreference;

}
