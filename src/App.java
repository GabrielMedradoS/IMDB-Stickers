import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* import io.github.cdimascio.dotenv.Dotenv; */
public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e acessar IMDB com os top 250 filmes

        /* Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY"); */

       /*  String url = "https://imdb-api.com/en/API/Top250Movies/k_mze333x9"; */
        String url = "https://api.nasa.gov/planetary/apod?api_key=VUTErVuo4btkNh0PAXI9v3wW1a7vrtEMZiU3o7Yq&start_date=2022-08-01&end_date=2022-08-07";

        var http = new ClientHttp();
        String json = http.searchData(url);

        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> contentList = parser.parse(json);
        // exibir e manipular os dados
        var Sticker = new CreateStickers();
        
        for (int i = 0; i < 4; i++) {
            
            Map<String, String> content = contentList.get(i);
            
            // retirar oq esta depois do @ para pegar uma img maior
            String urlImage = content.get("url").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            
            String titleMovie = content.get("title");
            
            InputStream inputStream = new URL(urlImage).openStream();
            String outputFile = "IMDbAlura/out/" + titleMovie + ".png";
            Sticker.create(inputStream, outputFile);
            System.out.println(titleMovie);
            System.out.println();
        }
    }
}