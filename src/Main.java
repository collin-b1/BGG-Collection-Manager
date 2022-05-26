import java.io.IOException;
import java.util.ArrayList;

public class Main {

    /**
     * The loaded list of games
     */
    private final ArrayList<BoardGame> gameList;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        gameList = new ArrayList<>();
        new WindowMain(this);
    }

    // Create a new CSVReader object with the target being the gameList ArrayList
    public void readFromPath(String path) {
        CSVReader reader = new CSVReader(path, gameList);
        try {
            reader.read();
            System.out.printf("Successfully loaded %d game(s) from file!%n", gameList.size());
        } catch (IOException e) {
            System.out.println("Failed to load games from file!");
            e.printStackTrace();
        }
    }

    public ArrayList<BoardGame> getGameList() {
        return this.gameList;
    }
}