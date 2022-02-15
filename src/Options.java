public class Options {

    public enum SortType {
        NAME(),
        PLAYERS(),
        MINUTES(),
        COMPLEXITY(),
        RATING();

        SortType() {}
    }

    // Instance variables
    private int minPlayers;
    private int maxPlayers;
    private int[] recPlayers;
    private int minPlayingTime;
    private int maxPlayingTime;
    private double rating;
    private double difficulty;
    private boolean isExpansion;

    public Options() {}

    public Options setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
        return this;
    }

    public int getMinPlayers() {
        return this.minPlayers;
    }

    public Options setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public Options setRecPlayers(int[] recPlayers) {
        this.recPlayers = recPlayers;
        return this;
    }

    public int[] getRecPlayers() {
        return this.recPlayers;
    }

    public Options setMinPlayingTime(int minPlayingTime) {
        this.minPlayingTime = minPlayingTime;
        return this;
    }

    public int getMinPlayingTime() {
        return this.minPlayingTime;
    }

    public Options setMaxPlayingTime(int maxPlayingTime) {
        this.maxPlayingTime = maxPlayingTime;
        return this;
    }

    public int getMaxPlayingTime() {
        return maxPlayingTime;
    }

    public Options setDifficulty(double difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public double getDifficulty() {
        return this.difficulty;
    }

    public Options setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public double getRating() {
        return this.rating;
    }

    public Options setIsExpansion(boolean isExpansion) {
        this.isExpansion = isExpansion;
        return this;
    }

    public boolean isExpansion() {
        return this.isExpansion;
    }

    public boolean matches(Options match) {
        if (getMinPlayers() > match.getMinPlayers()) return false;
        if (getMaxPlayers() < match.getMinPlayers()) return false;
        if (getMinPlayingTime() < match.getMinPlayingTime()) return false;
        if (getMaxPlayingTime() > match.getMaxPlayingTime()) return false;
        if (getDifficulty() > match.getDifficulty()) return false;
        if (isExpansion() && !match.isExpansion()) return false;
        return true;
    }
}
