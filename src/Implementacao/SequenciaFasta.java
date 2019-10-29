package Implementacao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SequenciaFasta {
    private int TIMER = 25;

    private List<String> extraiSequenciaFasta() {
        
        List<String[]> potenciais = new ComparaInfoProteinas().identificaProteinas();
        List<String> listaDeElementos = new ArrayList<>();

        Long inicio = System.currentTimeMillis();
        int debug = 0;

        for (String[] proteina : potenciais) {
            String url = "https://www.uniprot.org/uniprot/" + proteina[4] + ".fasta";

            try {

                Document arquivoHtml = Jsoup.connect(url).get();
                listaDeElementos.add(arquivoHtml.text());
                debug++;
             
                if (debug == TIMER) {
                    System.out.println(TIMER + " sequências mineradas");
                    TIMER += 25;
                }
             
            } catch (HttpStatusException e) {
                System.out.println("Erro ao buscar o uniprot_id: " + proteina[4]);

            } catch (IOException e) {
                System.out.println("Erro na execução." + e);
            }
        }
        
        System.out.println("Total de " + debug + " sequências mineradas.");

        Long fim = System.currentTimeMillis();

        System.out.println("Tempo para mineração das sequências fasta: " + ((fim - inicio) / 1000) + " segundos.");
        System.out.println("____________________________________________________");

        return listaDeElementos;

    }

    public void obtemPotenciaisEfetoras() {

        File f = new File("C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\TCC\\sequencia_fasta_tipo2.txt");

        try {

            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String sequenciaFasta : this.extraiSequenciaFasta()) {
                /* Troca SV=N por ] */
                sequenciaFasta = sequenciaFasta.replaceFirst("SV=[0-9]", "]");
                
                bw.write(sequenciaFasta + "\r\n\r\n");
            }

            bw.flush();
            bw.close();

        } catch (IOException e) {
            System.out.println("Erro de arquivo: " + e);
        }

    }

}
