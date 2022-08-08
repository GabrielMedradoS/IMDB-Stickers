import java.util.List;

public interface IContentExtractor {
  List<Content> pullContents(String json);
}
