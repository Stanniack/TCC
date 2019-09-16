package Implementacao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class ProcuraArtigosPubtator {

    public void searchArticles(List<String> lista) {
        URL url = null;

        for (int i = 0; i < lista.size(); i++) {
            
            try {
                /* Processo de conexão com o webservice */

                url = new URL("https://www.ncbi.nlm.nih.gov/research/pubtator-api/publications/export/pubtator?pmids=" + lista.get(i));
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                conexao.setRequestMethod("GET");
                conexao.setRequestProperty("Content-Type", "text/xml");
                conexao.setDoInput(true);
                // tempo para requisição
                conexao.setConnectTimeout(5000);
                conexao.connect();

                // Parser
                Scanner scan = new Scanner(url.openStream());
                int debug = 0;

                while (scan.hasNext()) {
                    // Aqui eu devo levar para o banco
                   System.out.println(scan.nextLine());
                    debug++;

                }

                System.out.println("Artigo " + (i+1) + ": " + debug + " linhas");
                
            } catch (UnknownHostException e) {
                System.out.println("Erro de conexão: " + e);
                //Thread.sleep(10000);
            } catch (IOException e) {
                System.out.println("Erro de entrada/saída de arquivo: método 'searchArticles'");
            }
        }

    }

}
