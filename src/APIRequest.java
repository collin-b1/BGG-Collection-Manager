import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// This class/feature is unfinished but will be used in the final version.
// It will download the board game's picture from the internet and display
// it on the best fit window

public class APIRequest {

    // https://api.geekdo.com/xmlapi2/thing?id=174430
    public String getArt(String id) throws IOException {
        URL url = new URL("https://api.geekdo.com/xmlapi2/thing?id=" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");

        String result = "";
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(http.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result += line;
            }
        }
        return result;
    }
}
