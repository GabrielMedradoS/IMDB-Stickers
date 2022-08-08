import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorNasa implements IContentExtractor {

  public  List<Content> pullContents(String json) {

        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Content> contentsList = new ArrayList<>();

        // popular a lista de conteúdos
        for (Map<String, String> attributes : attributesList) {
          String title = attributes.get("title");
          String urlImage = attributes.get("url");

          var content = new Content(title, urlImage);

          contentsList.add(content);
        }

        return contentsList;
  }
}
