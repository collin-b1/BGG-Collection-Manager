import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private final ArrayList<BoardGame> gameList;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        gameList = new ArrayList<>();
        new WindowMain(this);
    }


    public void readFromPath(String path) {
        CSVReader reader = new CSVReader(path, gameList);
        try {
            reader.read();
            System.out.println(gameList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BoardGame> getGameList() {
        return this.gameList;
    }
}