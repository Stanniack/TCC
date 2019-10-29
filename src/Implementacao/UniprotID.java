package Implementacao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UniprotID {

    public List<String> getUniprotsFromSecretE() {

        String url = "http://secretepdb.erc.monash.edu/browse.action";
        List<String> uniprotIds = new ArrayList<>();

        try {

            Document arquivoHtml = Jsoup.connect(url).get();
            Elements elementosEscolhidos = arquivoHtml.getElementsByAttribute("target");

            for (Element elemento : elementosEscolhidos) {
                if (!elemento.text().equals("")) {
                    uniprotIds.add(elemento.text());
                }

            }


        } catch (IOException e) {
            System.out.println("Erro de execução: " + e);
        }

        return uniprotIds;
    }
}
