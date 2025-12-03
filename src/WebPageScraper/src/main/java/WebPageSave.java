import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.FileOutputStream;
import java.io.IOException;

public class WebPageSave {

    public boolean saveWebPage(String url) {
        try {

            Connection.Response response = Jsoup.connect(url)
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .execute();

            int statusCode = response.statusCode();

            if (statusCode != 200) {
                System.out.println("The URL returned " + statusCode + "!");
                return false;
            }

            String htmlContent = response.body();


            try (FileOutputStream fos = new FileOutputStream("source.html")) {
                fos.write(htmlContent.getBytes("UTF-8"));
            }

            System.out.println("Content saved.");
            return true;

        } catch (IOException e) {
            System.out.println("Error accessing URL: " + e.getMessage());
            return false;
        }
    }
}