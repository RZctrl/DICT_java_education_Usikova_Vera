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
        return title.replaceAll("[^a-zA-Z0-9]", "_") + ".txt";
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

    @Override
    public String toString() {
        return title + "\n" + content;
    }
}