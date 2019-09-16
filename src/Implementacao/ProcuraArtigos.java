package Implementacao;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Mateus
 */
public class ProcuraArtigos {

    private int NUMERO_ARTIGO = 1;
    private final int quantidadeThreads = 2;
    private int DEBUGGER = 100;

    public void executa() {
        this.aplicaMetodoTEXT();

        for (int i = 0; i < quantidadeThreads; i++) {
            new Thread() {
                @Override
                public void run() {
                    aplicaMetodo();

                }

            }.start();
        }

    }

    private void dorme() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProcuraArtigos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aplicaMetodo() {

        Long tempoInicial = System.currentTimeMillis();

        while (true) {

            try {
                String url = "https://www.ncbi.nlm.nih.gov/pubmed/" + NUMERO_ARTIGO;
                Document arquivoHtml = Jsoup.connect(url).get();
                Elements elementosTagEscolhida = arquivoHtml.getElementsByClass("content");

                List<String> listaDeAbstracts = null;
                List<String> listaDeKeyWords = null;
                List<String> listaDeAuths = null;
                List<String> listaDeAuthorInformations = null;
                List<String> listaDeInformations = null;

                for (Element elemento : elementosTagEscolhida) {
                    listaDeAbstracts = elemento.getElementsByClass("abstr").eachText();
                    listaDeKeyWords = elemento.getElementsByClass("keywords").eachText();
                    listaDeInformations = elemento.getElementsByClass("cit").eachText();
                    listaDeAuthorInformations = elemento.getElementsByClass("afflist").eachText();
                    listaDeAuths = elemento.getElementsByClass("auths").eachText();

                }

                // Incrementa para o próximo artigo
                this.NUMERO_ARTIGO++;

                //System.out.println(NUMERO_ARTIGO + ": " + listaDeAuths);
                //System.out.println(listaDeInformations);
                // System.out.println(listaDeAuths);
                //System.out.println(listaDeAuthorInformations);
                //System.out.println(listaDeAbstracts);
                //System.out.println(listaDeKeyWords);
                System.out.println(this.NUMERO_ARTIGO);
            } catch (UnknownHostException e) {
                System.out.println("Erro ao buscar o link, verifique a conexão com a internet: " + e);

            } catch (HttpStatusException e) {
                System.out.println("Link não encontrado: " + e);
                //this.dorme();
                this.NUMERO_ARTIGO++;
                //System.out.println(this.NUMERO_ARTIGO);

            } catch (IOException e) {
                System.out.println("Erro na execução: " + e);

            }

            if (NUMERO_ARTIGO == DEBUGGER) {
                Long tempoFinal = System.currentTimeMillis();
                System.out.println("Demoraram " + ((tempoFinal - tempoInicial) / 1000)
                        + " segundos para baixar " + NUMERO_ARTIGO + " artigos");

                DEBUGGER += 100;
            }

        }

    }

    private void aplicaMetodoTEXT() {

        Long tempoInicial = System.currentTimeMillis();

//        while (true) {
        try {
            String url = "https://www.ncbi.nlm.nih.gov/pubmed/" + "30529900";
            Document arquivoHtml = Jsoup.connect(url).get();
            Elements elementosTagEscolhida = arquivoHtml.getElementsByTag("pre");
            //System.out.println(elementosTagEscolhida);
            

            List<String> listaDeAbstracts = null;

            for (Element elemento : elementosTagEscolhida) {
                System.out.println(elemento.text());

            }

            
            // Incrementa para o próximo artigo
            this.NUMERO_ARTIGO++;

        } catch (UnknownHostException e) {
            System.out.println("Erro ao buscar o link, verifique a conexão com a internet: " + e);

        } catch (HttpStatusException e) {
            System.out.println("Link não encontrado: " + e);
            //this.dorme();
            this.NUMERO_ARTIGO++;
            //System.out.println(this.NUMERO_ARTIGO);

        } catch (IOException e) {
            System.out.println("Erro na execução: " + e);

        }

        if (NUMERO_ARTIGO == DEBUGGER) {
            Long tempoFinal = System.currentTimeMillis();
            System.out.println("Demoraram " + ((tempoFinal - tempoInicial) / 1000)
                    + " segundos para raspar " + " 100 " + " artigos");

            DEBUGGER += 100;
        }

//        }
    }

}
