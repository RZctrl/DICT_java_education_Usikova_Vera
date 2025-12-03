import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ArticleSave {

    public void saveArticles(List<Article> articles, String basePath) {
        File directory = new File(basePath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.out.println("Failed to create directory: " + basePath);
                return;
            }
        }

        for (Article article : articles) {
            try {
                String filePath = basePath + article.getFileName();
                FileWriter writer = new FileWriter(filePath);
                writer.write(article.getContent());
                writer.close();
                System.out.println("Saved: " + filePath);
            } catch (IOException e) {
                System.out.println("Error saving file: " + article.getFileName());
                e.printStackTrace();
            }
        }
    }
}