import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.FileOutputStream;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WebPageSave {

    public boolean saveToFile(String url, String filePath) {
        try {
            Connection.Response response = Jsoup.connect(url)
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .execute();

            int statusCode = response.statusCode();

            if (statusCode != 200) {
                System.out.println("The URL returned " + statusCode);
                return false;
            }

            Document doc = response.parse();
            String htmlContent = doc.html();

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(htmlContent.getBytes(StandardCharsets.UTF_8));
            }

            System.out.println("Content saved to: " + filePath);
            return true;

        } catch (IOException e) {
            System.out.println("Error accessing URL:" + e.getMessage());
            return false;
        }
    }
}