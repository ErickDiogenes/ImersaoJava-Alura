import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Arquivo local com conexao da API
        String startAPI = "loginAPI\\in.txt";
        FileReader fr = new FileReader(startAPI);
        BufferedReader br = new BufferedReader(fr);



        //Conexão https
        String url = br.readLine();
        URI endereco = URI.create(url);
        var client =HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();



        //Pegando os dados da API
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);


        //Exibindo oque desejamos de forma editada
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.print("\u001b[48;2;42;122;228m");
            System.out.print("\u001b[38;2;255;255;255m");
            System.out.println(filme.get("title"));
            System.out.print("\u001b[m");

            String imdbRating = filme.get("imDbRating");
            Double imdbRatingDouble = Double.parseDouble(imdbRating);
            long roundedRating = Math.round(imdbRatingDouble);
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            for (int i=0; i<roundedRating; i++){
                System.out.print("\u2b50");
            }
            System.out.println();
        }
        
        
    }
}
