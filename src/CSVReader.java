import java.io.*;
import java.util.List;

/* Sources
 * http://www.java2s.com/Code/Java/File-Input-Output/Readeachlineinacommaseparatedfileintoanarray.htm
 *
 *
 */
public class CSVReader {

    private final File file;
    private final List<BoardGame> target;

    public CSVReader(String path, List<BoardGame> target) {
        this.file = new File(path);
        this.target = target;
    }

    // Function to read board game list from .CSV file
    public boolean read() throws IOException {
        // Attempt to create a BufferedReader with the file
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            return false;
        }

        // Get past first line with headers
        br.readLine();

        // Read each line into line
        String line;
        while ((line = br.readLine()) != null) {
            // Split line by commas
            String[] values = line.split("\",\"");
            BoardGame b;
            Options o = new Options()
                    .setRating(Double.parseDouble(values[20]))
                    .setDifficulty(Double.parseDouble(values[22]))
                    .setMinPlayers(Integer.parseInt(values[27]))
                    .setMaxPlayers(Integer.parseInt(values[28]))
                    .setMinPlayingTime(Integer.parseInt(values[31]))
                    .setMaxPlayingTime(Integer.parseInt(values[30]));

            if (!values[42].equals("expansion")) {
                b = new BoardGame(values[0].substring(1), o);
                b.getStats().setIsExpansion(false);
            } else {
                b = new ExpansionGame(values[0].substring(1), o);
                b.getStats().setIsExpansion(true);
            }
            target.add(b);
        }

        br.close();
        return true;
    }
}
