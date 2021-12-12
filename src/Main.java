import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private CSVReader reader;
    private ArrayList<BoardGame> gameList;
    private WindowMain mainWindow;

    public static void main(String[] args) {
        Main main = new Main();
    }

    public Main() {
        gameList = new ArrayList<>();
        mainWindow = new WindowMain(this);
    }

    public void readFromPath(String path) {
        reader = new CSVReader(path, gameList);
        try {
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getGameList() {
        return this.gameList;
    }
}