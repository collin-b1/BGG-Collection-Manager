import java.util.Arrays;

public class BoardGame implements Comparable<BoardGame> {

    // The Display name of the board game
    private final String name;

    // The stats of the board game, stored inside an Options object
    private final Options stats;

    // ID of the board game from the CSV file
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

    // Specifies how the BoardGame object should
    // display in text, mainly used for debugging
    public String toString() {
        return this.getName() + ":" + this.getId() + ":" + Arrays.toString(this.getStats().getRecPlayers());
    }

    // Method which specifies how the BoardGame objects
    // should compare to each other in the PriorityQueue
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
