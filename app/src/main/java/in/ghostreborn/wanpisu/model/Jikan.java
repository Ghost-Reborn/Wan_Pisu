package in.ghostreborn.wanpisu.model;

public class Jikan {

    String animeTitle;
    String animeScore;
    String animeAired;
    boolean animeFiller;
    boolean animeRecap;

    public Jikan(
        String animeTitle,
        String animeScore,
        String animeAired,
        boolean animeFiller,
        boolean animeRecap
    ){
        this.animeTitle = animeTitle;
        this.animeScore = animeScore;
        this.animeAired = animeAired;
        this.animeFiller = animeFiller;
        this.animeRecap = animeRecap;
    }

    public String getAnimeAired() {
        return animeAired;
    }

    public String getAnimeScore() {
        return animeScore;
    }

    public String getAnimeTitle() {
        return animeTitle;
    }

    public boolean isAnimeFiller() {
        return animeFiller;
    }

    public boolean isAnimeRecap() {
        return animeRecap;
    }
}
