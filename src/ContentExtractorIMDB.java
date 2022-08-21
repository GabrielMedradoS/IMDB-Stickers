import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorIMDB implements IContentExtractor{
  
  public  List<Content> pullContents(String json) {

    // extrair só os dados que interessam (titulo, poster, classificação)
    JsonParser parser = new JsonParser();
    List<Map<String, String>> attributesList = parser.parse(json);

    List<Content> contentsList = new ArrayList<>();

    // popular a lista de conteúdos
    for (Map<String, String> attributes : attributesList) {
      String title = attributes.get("title").replaceAll("[^a-zA-Z0-9]", " ");
      // retirar oq esta depois do @ para pegar uma img maior
      String urlImage = attributes.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

      var content = new Content(title, urlImage);

      contentsList.add(content);
    }

    return contentsList;
}
}
