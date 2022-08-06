import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e acessar IMDB com os top 250 filmes
        // String apikey = "k_mze333x9";
        String url = "https://imdb-api.com/en/API/Top250Movies/k_mze333x9";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String json = response.body();
        
        // extrair só os dados que interessam (titulo, poster, classificaçao)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> MovieList = parser.parse(json);

        // exibir e manipular os dados
        for (Map<String, String> movie : MovieList) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
            System.out.println();
        }
    }
}
