public class Article {
    private String title;
    private String content;
    private String fileName;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.fileName = generateFileName(title);
    }

    private String generateFileName(String title) {
        String name = title.replaceAll("\\s+", "_")
                .replaceAll("[^a-zA-Z0-9_]", "");
        return name + ".txt";
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getFileName() {
        return fileName;
    }
}