import java.io.IOException;
import java.util.List;

public interface Parser {
    List<Article> parse(String url) throws IOException;
    boolean canParse(String url);
}