import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImdbParser implements Parser {

    @Override
    public List<Article> parse(String url, String articleType) throws IOException {
        List<Article> articles = new ArrayList<>();

        Connection connection = Jsoup.connect(url);
        connection.header("Accept-Language", "en-US,en;q=0.5");
        Document doc = connection.get();

        String title = doc.title();
        Element metaDescription = doc.select("meta[name=description]").first();

        String description = metaDescription != null ? metaDescription.attr("content") :
                "No description available";

        Article article = new Article(title, description);
        articles.add(article);

        return articles;
    }

    @Override
    public boolean canParse(String url) {
        return url.contains("imdb.com") && url.contains("/title/");
    }
}