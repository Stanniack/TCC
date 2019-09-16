package Testes;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTeste {

    public static void main(String[] args) {

        try {
            String url = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id=11748933&retmode=xml";
            Document arquivoHtml = Jsoup.connect(url).get();
            Elements elementosTagEscolhida = arquivoHtml.getElementsByClass("expandable-body");

            List<String> listaDeAbstracts = null;

            for (Element elemento : elementosTagEscolhida) {
                List<String> element = elemento.getElementsByClass("expandable-body").eachText();
                System.out.println(element);

            }
            
            

        } catch (UnknownHostException e) {
            System.out.println("Erro ao buscar o link, verifique a conexão com a internet: " + e);

        } catch (HttpStatusException e) {
            System.out.println("Link não encontrado: " + e);

        } catch (IOException e) {
            System.out.println("Erro na execução: " + e);

        }
    }
}
