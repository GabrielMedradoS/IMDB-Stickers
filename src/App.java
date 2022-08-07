import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

/* import io.github.cdimascio.dotenv.Dotenv; */

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e acessar IMDB com os top 250 filmes

        /* Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY"); */
        
        String url = "https://imdb-api.com/en/API/Top250Movies/k_mze333x9";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String json = response.body();
        
        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> MovieList = parser.parse(json);

        // exibir e manipular os dados
        var Sticker = new CreateStickers();
        for (Map<String, String> movie : MovieList) {
            
            String urlImage = movie.get("image");
            String titleMovie = movie.get("title");
            
            InputStream inputStream = new URL(urlImage).openStream();
            String outputFile = titleMovie + ".png";

            Sticker.create(inputStream, outputFile);

            System.out.println(movie.get("title"));
            System.out.println();
        }
    }
}
