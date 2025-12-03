import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WebPageScraper {
    private ArticleSave articleSave;
    private WebPageSave webPageSaver;
    private NatureParser natureParser;

    public WebPageScraper() {
        this.articleSave = new ArticleSave();
        this.webPageSaver = new WebPageSave();
        this.natureParser = new NatureParser();
    }

    public void processMultiplePages(int numberOfPages, String articleType) {
        int totalArticles = 0;
        List<String> savedFiles = new ArrayList<>();

        for (int page = 1; page <= numberOfPages; page++) {
            String url = "https://www.nature.com/nature/articles?sort=PubDate&year=2023&page=" + page;

            String dirPath = "./Page_" + page + "/";

            try {
                File directory = new File(dirPath);
                if (!directory.exists()) {
                    boolean created = directory.mkdirs();
                    if (!created) {
                        System.out.println("Failed to create directory: " + dirPath);
                        continue;
                    }
                }
                boolean pageSaved = webPageSaver.saveToFile(url, dirPath + "source.html");
                if (!pageSaved) {
                    System.out.println("Failed to save page " + page);
                    continue;
                }

                List<Article> articles = natureParser.parse(url, articleType);
                articleSave.saveArticles(articles, dirPath);
                totalArticles += articles.size();

                for (Article article : articles) {
                    savedFiles.add(article.getFileName());
                }
            } catch (IOException e) {
                System.out.println("Error processing page " + page + ": " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        WebPageScraper scraper = new WebPageScraper();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of pages: ");
        try {
            int numberOfPages = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter article type: ");
            String articleType = scanner.nextLine().trim();

            if (numberOfPages <= 0) {
                System.out.println("Number of pages must be positive.");
            } else {
                scraper.processMultiplePages(numberOfPages, articleType);
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } finally {
            scanner.close();
        }
    }
}