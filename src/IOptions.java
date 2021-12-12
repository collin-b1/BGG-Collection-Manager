public interface IOptions {
    int getMinPlayers();
    int getMaxPlayers();
    int getMinPlayingTime();
    int getMaxPlayingTime();
    double getDifficulty();
    double getRating();
    boolean matches(Options match);
}
