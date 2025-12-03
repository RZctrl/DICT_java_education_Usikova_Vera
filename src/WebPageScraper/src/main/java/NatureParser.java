import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NatureParser {
    public List<Article> parse(String url, String articleType) throws IOException {
        List<Article> articles = new ArrayList<>();

        Document doc = Jsoup.connect(url)
                .header("Accept-Language", "en-US,en;q=0.5")
                .get();

        Elements articleElements = doc.select("article");

        for (Element articleElement : articleElements) {
            Element typeSpan = articleElement.select("span[data-test=article.type]").first();

            if (typeSpan != null && typeSpan.text().equals(articleType)) {
                Element linkElement = articleElement.select("a[data-track-action=view article]").first();

                if (linkElement != null) {
                    String articleUrl = linkElement.attr("href");

                    if (!articleUrl.startsWith("http")) {
                        articleUrl = "https://www.nature.com" + articleUrl;
                    }

                    try {
                        Article article = parseArticle(articleUrl);
                        if (article != null) {
                            articles.add(article);
                        }
                    } catch (IOException e) {
                        System.out.println("Error parsing article: " + articleUrl);
                    }
                }
            }
        }
        return articles;
    }

    private Article parseArticle(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .header("Accept-Language", "en-US,en;q=0.5")
                .get();

        String title = doc.title();
        if (title.endsWith(" | Nature")) {
            title = title.substring(0, title.length() - 9);
        }

        String content = "";

        Element body = doc.select("div[class*=body], div[class*=article-body], div[class*=article__body]").first();
        if (body != null) {
            content = body.text();

            content = content.replaceAll("\\s+", " ").trim();
        } else {
            body = doc.select("div.c-article-body, article").first();
            if (body != null) {
                content = body.text().replaceAll("\\s+", " ").trim();
            }
        }

        if (!content.isEmpty()) {
            return new Article(title, content);
        }
        return null;
    }
}