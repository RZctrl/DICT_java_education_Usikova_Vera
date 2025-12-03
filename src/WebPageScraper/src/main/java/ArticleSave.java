import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ArticleSave {
    public void saveArticles(List<Article> articles, String basePath) {
        File directory = new File(basePath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.out.println("Failed to create directory" );
                return;
            }
        }

        if (articles.isEmpty()) {
            System.out.println("No articles to save.");
            return;
        }

        for (Article article : articles) {
            try {
                String fileName = article.getFileName();
                String filePath = basePath + File.separator + fileName;

                try (FileOutputStream fos = new FileOutputStream(filePath)) {
                    fos.write(article.getContent().getBytes(StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                System.out.println("Error saving article");
            }
        }
    }
}