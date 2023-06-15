package in.ghostreborn.wanpisu.model;

public class AllAnime {

    String animeName;
    String animeThumbnail;

    public AllAnime(
            String animeName,
            String animeThumbnail
    ){
        this.animeName = animeName;
        this.animeThumbnail = animeThumbnail;
    }

    public String getAnimeName() {
        return animeName;
    }

    public String getAnimeThumbnail() {
        return animeThumbnail;
    }
}
