import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {

        //Arquivo local com conexao da API
        //String startAPI = "loginAPI\\nasa.txt";
        //BufferedReader br = new BufferedReader(fr);
        //String url = br.readLine();
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoNasa();

        String url = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

        //Conexão API
        var http = new ClientHttp();
        String json = http.buscadDados(url);


        // pegar dados da API e mostrar na tela
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new StickersWpp();
        for (int i=0; i<3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = conteudo.titulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
            System.out.println();
        }
    }
}