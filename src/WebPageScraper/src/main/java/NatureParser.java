import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NatureParser implements Parser {

    @Override
    public List<Article> parse(String url) throws IOException {
        List<Article> articles = new ArrayList<>();


        Document doc = Jsoup.connect(url)
                .header("Accept-Language", "en-US,en;q=0.5")
                .get();


        Elements articleElements = doc.select("article");

        for (Element articleElement : articleElements) {

            Element typeSpan = articleElement.select("span[data-test=article.type]").first();

            if (typeSpan != null && typeSpan.text().equals("News")) {

                Element linkElement = articleElement.select("a[data-track-action=view article]").first();

                if (linkElement != null) {
                    String articleUrl = linkElement.attr("href");


                    if (!articleUrl.startsWith("http")) {
                        articleUrl = "https://www.nature.com" + articleUrl;
                    }


                    try {
                        Article article = parseArticlePage(articleUrl);
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

    private Article parseArticlePage(String url) throws IOException {

        Document articleDoc = Jsoup.connect(url)
                .header("Accept-Language", "en-US,en;q=0.5")
                .get();


        String title = articleDoc.title();


        Element bodyElement = articleDoc.select("div[class*=body], div[class*=content], div[class*=article-body]").first();

        String content;
        if (bodyElement != null) {

            content = bodyElement.text();

            content = content.replaceAll("\\s+", "");
        } else {
            content = "No content found";
        }

        String fileName = generateFileName(title);

        return new Article(title, content) {
            @Override
            public String getFileName() {
                return fileName;
            }
        };
    }

    private String generateFileName(String title) {
        String fileName = title.replaceAll("\\s+", "_");
        fileName = fileName.replaceAll("[^a-zA-Z0-9_]", "");
        return fileName + ".txt";
    }

    @Override
    public boolean canParse(String url) {
        return url.contains("nature.com") && url.contains("/nature/articles");
    }
}