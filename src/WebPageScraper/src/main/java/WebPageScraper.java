import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WebPageScraper {
    private List<Parser> parsers;
    private ArticleSave articleSave;
    private WebPageSave webPageSave;


    public WebPageScraper() {
        this.parsers = new ArrayList<>();
        this.articleSave = new ArticleSave();
        this.webPageSave = new WebPageSave();
        regParser(new ImdbParser());
        regParser(new NatureParser());
    }

    private void regParser(Parser parser) {
        parsers.add(parser);
    }

    public void processUrl(String url) {
        try {
            boolean saved = webPageSave.saveWebPage(url);
            if (!saved) {
                return;
            }


            Parser suitParser = findSuitableParser(url);

            if (suitParser == null) {
                System.out.println("Invalid movie page!");
                return;
            }

            List<Article> articles = suitParser.parse(url);

            if (url.contains("nature.com")) {
                System.out.print("Saved articles: [");
                for (int i = 0; i < articles.size(); i++) {
                    System.out.print("'" + articles.get(i).getFileName() + "'");
                    if (i < articles.size() - 1) {
                        System.out.print(" , ");
                    }
                }
                System.out.println("]");
            } else {

                for (Article article : articles) {
                    System.out.println(article);
                    System.out.println();
                }
            }

            articleSave.saveArticles(articles, "./articles/");

        } catch (IOException e) {
            System.out.println("Error processing URL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
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