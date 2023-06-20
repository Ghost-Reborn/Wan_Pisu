package in.ghostreborn.wanpisu.model;

public class AllAnime {

    String animeID;
    String animeName;
    String englishName;
    String animeAvailableEpisodes;
    String animeType;
    String animeMalID;
    String animeThumbnail;
    String animeLastEpisodeInfo;
    String animeLastEpisodeYear;

    public AllAnime(
            String animeID,
            String animeName,
            String englishName,
            String animeAvailableEpisodes,
            String animeType,
            String animeMalID,
            String animeThumbnail,
            String animeLastEpisodeInfo,
            String animeLastEpisodeYear
    ){
        this.animeID = animeID;
        this.animeName = animeName;
        this.englishName = englishName;
        this.animeAvailableEpisodes = animeAvailableEpisodes;
        this.animeType = animeType;
        this.animeMalID = animeMalID;
        this.animeThumbnail = animeThumbnail;
        this.animeLastEpisodeInfo = animeLastEpisodeInfo;
        this.animeLastEpisodeYear = animeLastEpisodeYear;
    }

    public String getAnimeName() {
        return animeName;
    }

    public String getAnimeThumbnail() {
        return animeThumbnail;
    }

    public String getAnimeAvailableEpisodes() {
        return animeAvailableEpisodes;
    }

    public String getAnimeID() {
        return animeID;
    }

    public String getAnimeLastEpisodeYear() {
        return animeLastEpisodeYear;
    }

    public String getAnimeLastEpisodeInfo() {
        return animeLastEpisodeInfo;
    }

    public String getAnimeMalID() {
        return animeMalID;
    }

    public String getAnimeType() {
        return animeType;
    }

    public String getEnglishName() {
        return englishName;
    }
}
