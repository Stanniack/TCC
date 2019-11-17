package Implementacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EfetorasRepetidas {

    public void eliminaRepetidas() {

        try {

            List<String> listaMatCab;
            List<String> listaMatCab2;
            List<String> listaMatSeq;
            List<String> listaMatSeq2;

            List<String> listaHerCab;
            List<String> listaHerCab2;
            List<String> listaHerSeq;
            List<String> listaHerSeq2;

            List<String[]> listaNovas = new ArrayList<>();

            listaMatCab = this.obtemCabecalho("C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\efetoras\\mateus\\cabecalho_rf.txt");
            listaMatSeq = this.obtemSequencia("C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\efetoras\\mateus\\fasta_rf.txt");

            listaHerCab = this.obtemCabecalho("C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\efetoras\\hercules\\cabecalho.txt");
            listaHerSeq = this.obtemSequencia("C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\efetoras\\hercules\\fasta.txt");

            System.out.println("MatCab: " + listaMatCab.size());
            System.out.println("MatSeq: " + listaMatSeq.size());
            System.out.println("HerCab: " + listaHerCab.size());
            System.out.println("HerSeq: " + listaHerSeq.size());
            System.out.println("----------------------------------");

            /* Elima repetidas proteínas Mat */
            listaMatCab2 = this.eliminaProteinas(listaMatCab);
            listaMatSeq2 = this.eliminaProteinas(listaMatSeq);

            /* Elima repetidas proteínas Her */
            listaHerCab2 = this.eliminaProteinas(listaHerCab);
            listaHerSeq2 = this.eliminaProteinas(listaHerSeq);

            System.out.println("MatCab2: " + listaMatCab2.size());
            System.out.println("MatSeq2: " + listaMatSeq2.size());
            System.out.println("HerCab2: " + listaHerCab2.size());
            System.out.println("HerSeq2: " + listaHerSeq2.size());
            System.out.println("----------------------------------");

            this.gravaNovasEfetoras(listaHerSeq2, listaMatCab2, listaMatSeq2, "efetorasFinaisRF");

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Quebra de array: " + ex);
        }

    }

    private List<String> eliminaProteinas(List<String> lista) {
        List<String> lista2 = new ArrayList<>();

        for (String item : lista) {
            if (!lista2.contains(item)) {
                lista2.add(item);
            }
        }

        return lista2;
    }

    private List<String> obtemCabecalho(String file) {

        List<String> listaCabecalho = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            while (br.ready()) {

                listaCabecalho.add(Arrays.toString(br.readLine()
                        .split("----------------------------------------------------------------------")));

            }

        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado: " + ex);
        } catch (IOException ex) {
            System.out.println("Erro E/S: " + ex);
        }

        return listaCabecalho;

    }

    private List<String> obtemSequencia(String file) {
        List<String> listaSequencia = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            while (br.ready()) {

                listaSequencia.add(Arrays.toString(br.readLine()
                        .replace(" ", "")
                        .replace("\\r\\n\\r\\n", "")
                        .split("----------------------------------------------------------------------")));

            }

        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado: " + ex);
        } catch (IOException ex) {
            System.out.println("Erro E/S: " + ex);
        }

        return listaSequencia;
    }

    private void gravaNovasEfetoras(List<String> listaHerSeq2, List<String> listaCab, List<String> listaSeq, String nomeTxt) {
        List<String> novasEfetoras = new ArrayList<>();

        int debug = 0;
        for (int i = 0; i < listaSeq.size(); i++) {

            if (!listaHerSeq2.contains(listaSeq.get(i))) {
                String prot = listaCab.get(i).replace("[", "").replace("]", "")
                        + " "
                        + listaSeq.get(i).replace("[", "").replace("]", "");

                novasEfetoras.add(prot);
                debug++;
            }

        }

        System.out.println("Novas proteínas '" + nomeTxt + "': " + debug);

        File file = new File("C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\TCC\\" + nomeTxt + ".txt");

        try {

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String novaEfetora : novasEfetoras) {

                bw.write(novaEfetora + "\r\n\r\n");
            }

            bw.flush();
            bw.close();

        } catch (IOException e) {
            System.out.println("Erro de arquivo: " + e);
        }
    }

}
