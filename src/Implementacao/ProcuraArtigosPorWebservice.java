package Implementacao;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;

public class ProcuraArtigosPorWebservice {

    private int NUMERO_ARTIGO = 0;
    private final int quantidadeThreads = 10;
    private int DEBUGGER = 2500;
    private int DEBUGGER_TIMER = 0;
    private final int QTD_IDS = 250;

    public void executa() {

        for (int i = 0; i < quantidadeThreads; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        aplicaMetodo();
                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProcuraArtigosPorWebservice.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }.start();

            //System.out.println("Don't back here, please!");
        }

    }

    private void aplicaMetodo() throws InterruptedException {
        Long tempoInicial = System.currentTimeMillis();
        
        while (true) {

            this.trataXML(this.requestPubmed());

            if (NUMERO_ARTIGO == DEBUGGER) {
                Long tempoFinal = System.currentTimeMillis();
                System.out.println("Demoraram " + ((tempoFinal - tempoInicial) / 1000)
                        + " segundos para baixar " + this.NUMERO_ARTIGO + " artigos");

                DEBUGGER += 2500;
            }

            System.out.println(this.NUMERO_ARTIGO);

        }

    }

    private String ids() {
        String ids = "";

        for (int i = this.NUMERO_ARTIGO; i < (this.NUMERO_ARTIGO + this.QTD_IDS); i++) {
            ids += i + ",";
        }

        ids += this.NUMERO_ARTIGO + this.QTD_IDS;
        //System.out.println(ids);

        return ids;
    }

    private URL requestPubmed() throws InterruptedException {
        URL url = null;
        Long tempoConexaoInicial = System.currentTimeMillis();

        try {
            /* Processo de conexão com o webservice */

            url = new URL("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id="
                    + this.ids() + "&retmode=xml&rettype=abstract&api_key=616289344dac0e7d035ac631e1675f268a08");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Content-Type", "text/xml");
            conexao.setDoInput(true);
            // tempo para requisição
            conexao.setConnectTimeout(5000);
            conexao.connect();

            Long tempoConexaoFinal = System.currentTimeMillis();
            //System.out.println("Tempo de conexão: " + (tempoConexaoFinal - tempoConexaoInicial)/1000);

            // incrementa os ids
            this.NUMERO_ARTIGO += this.QTD_IDS;

        } catch (UnknownHostException e) {
            System.out.println("Erro de conexão: " + e);
            //Thread.sleep(10000);
        } catch (IOException e) {
            System.out.println("Erro de entrada/saída de arquivo: método 'requestPubmed'");
        }

        return url;
    }

    private void trataXML(URL url) {

        try {
            /* Pega o dado requisitado e joga na string */
            Scanner scan = new Scanner(url.openStream());

            /* Adiciona o XML na var xmlContent para 'parseamento' */
            StringBuilder xmlContent = new StringBuilder();

            while (scan.hasNext()) {
                xmlContent.append(scan.next()).append(" ");

            }

            //System.out.println(xmlContent);
            /* Trata conteúdo xml */
            String res = xmlContent.toString();
            StringReader sr = new StringReader(res);
            InputSource source = new InputSource(sr);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            //Parser do XML
            Document doc = dBuilder.parse(source);

            doc.getDocumentElement().normalize();

            /* Pega os dados */
            for (int i = 0; i < this.QTD_IDS; i++) {
                try {

                    if (xmlContent.toString().contains("ArticleTitle")) {
                        //System.out.println(doc.getElementsByTagName("ArticleTitle").item(i).getTextContent());
                    } else {
                        System.out.println("Sem abstract");
                    }

                } catch (DOMException e) {
                    System.out.println("DOM exception: " + e);
                } catch (NullPointerException e) {
                    System.out.println("Quebra de index: " + e);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro de entrada/saída de arquivo: método 'trataXML': " + e);
        } catch (ParserConfigurationException e) {
            System.out.println("Erro na configuração do parser: " + e);
        } catch (SAXException e) {
            System.out.println("Erro SAX: " + e);
        }

    }

}
