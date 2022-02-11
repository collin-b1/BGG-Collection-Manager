public class BoardGame implements Comparable<BoardGame> {

    private final String name;
    private final Options stats;

    public BoardGame(String name, Options stats) {
        this.name = name;
        this.stats = stats;
    }

    public String getName() {
        return this.name;
    }

    public Options getStats() {
        return this.stats;
    }

    public String toString() {
        return "" + this.getName();
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
