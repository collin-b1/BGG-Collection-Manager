public class ExpansionGame extends BoardGame {

    private BoardGame baseGame;

    public ExpansionGame(String name, Options options, int id) {
        super(name, options, id);
    }

    public void setBaseGame(BoardGame bg) {
        this.baseGame = bg;
    }

    public BoardGame getBaseGame() {
        return this.baseGame;
    }
}
