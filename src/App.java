import java.io.InputStream;
import java.net.URL;
import java.util.List;

/* import io.github.cdimascio.dotenv.Dotenv; */
public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e acessar IMDB com os top 250 filmes

        /* Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY"); */

        String url = "https://imdb-api.com/en/API/Top250Movies/k_mze333x9";
        ContentExtractorIMDB extractor = new ContentExtractorIMDB();


       // String url = "https://api.nasa.gov/planetary/apod?api_key=VUTErVuo4btkNh0PAXI9v3wW1a7vrtEMZiU3o7Yq&start_date=2022-08-01&end_date=2022-08-07";
       // ContentExtractorNasa extractor = new ContentExtractorNasa();

        var http = new ClientHttp();
        String json = http.searchData(url);

        // exibir e manipular os dados
        List<Content> contentList = extractor.pullContents(json);

        var Sticker = new CreateStickers();
        
        for (int i = 0; i < 3; i++) {
            
            Content content = contentList.get(i);
            
            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String outputFile = "IMDbAlura/out/" + content.getTitle() + ".png";
           
            Sticker.create(inputStream, outputFile);
            
            System.out.println(content.getTitle());
            System.out.println();
        }
    }
}