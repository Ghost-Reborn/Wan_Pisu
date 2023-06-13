package in.ghostreborn.wanpisu;

import java.util.ArrayList;

public class WanPisuConstants {

    public static final String ALL_ANIME_QUERY_HEAD = "https://api.allanime.to/allanimeapi?variables={%22search%22:{%22allowAdult%22:false,%22allowUnknown%22:false,%22query%22:%22";
    public static final String ALL_ANIME_QUERY_TAIL = "%22},%22limit%22:40,%22page%22:1,%22translationType%22:%22sub%22,%22countryOrigin%22:%22ALL%22}&query=query($search:SearchInput,$limit:Int,$page:Int,$translationType:VaildTranslationTypeEnumType,$countryOrigin:VaildCountryOriginEnumType){shows(search:$search,limit:$limit,page:$page,translationType:$translationType,countryOrigin:$countryOrigin){edges{_id,name,availableEpisodes,__typename,malId,thumbnail,lastEpisodeInfo,lastEpisodeDate,season,airedStart,episodeDuration,episodeCount,lastUpdateEnd}}}";
    public static ArrayList<AllAnime> allAnimes;

}
