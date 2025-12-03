import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WebPageScraper {
    private List<Parser> parsers;
    private ArticleSave articleSave;

    public WebPageScraper() {
        this.parsers = new ArrayList<>();
        this.articleSave = new ArticleSave();
        regParser(new ImdbParser());
    }

    private void regParser(Parser parser) {
        parsers.add(parser);
    }

    public void processUrl(String url) {
        try {
            Parser suitParser = findSuitableParser(url);

            if (suitParser == null) {
                System.out.println("Invalid movie page!");
                return;
            }

            List<Article> articles = suitParser.parse(url);


            for (Article article : articles) {
                System.out.println(article);
                System.out.println();
            }

            articleSave.saveArticles(articles, "./articles/");

        } catch (IOException e) {
            System.out.println("Invalid movie page!");
        } catch (Exception e) {
            System.out.println("Error processing URL: " + e.getMessage());
        }
    }

    private Parser findSuitableParser(String url) {
        for (Parser parser : parsers) {
            if (parser.canParse(url)) {
                return parser;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        WebPageScraper scraper = new WebPageScraper();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input the URL: ");
        String url = scanner.nextLine();

        scraper.processUrl(url);

        scanner.close();
    }
}