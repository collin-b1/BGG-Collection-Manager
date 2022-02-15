import java.util.Arrays;

public class BoardGame implements Comparable<BoardGame> {

    private final String name;
    private final Options stats;
    private final int id;

    public BoardGame(String name, Options stats, int id) {
        this.name = name;
        this.stats = stats;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Options getStats() {
        return this.stats;
    }

    public int getId() { return this.id; }

    public String toString() {
        return this.getName() + ":" + this.getId() + ":" + Arrays.toString(this.getStats().getRecPlayers());
    }

    @Override
    public int compareTo(BoardGame o) {
        switch (WindowMain.sortType) {
            default: // Name
                return getName().compareTo(o.getName());
            case RATING:
                Double rating = getStats().getRating();
                Double otherRating = o.getStats().getRating();
                return otherRating.compareTo(rating);
            case PLAYERS:
                Integer players = getStats().getMaxPlayers();
                Integer otherPlayers = o.getStats().getMaxPlayers();
                return players.compareTo(otherPlayers);
            case MINUTES:
                Integer minutes = getStats().getMinPlayingTime();
                Integer otherMinutes = o.getStats().getMinPlayingTime();
                return minutes.compareTo(otherMinutes);
            case COMPLEXITY:
                Double complexity = getStats().getDifficulty();
                Double otherComplexity = o.getStats().getDifficulty();
                return complexity.compareTo(otherComplexity);
        }
    }
}
