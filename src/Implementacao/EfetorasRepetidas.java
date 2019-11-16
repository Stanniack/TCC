package Implementacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EfetorasRepetidas {

    public void eliminaRepetidas() {
        
        try {

            /* Cabeçaho das proteínas Mat */
            String fileCabecalho = "C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\efetoras\\mateus\\cabecalho.txt";
            BufferedReader brCabecalho = new BufferedReader(new FileReader(fileCabecalho));
            
            /* Sequência das proteínas Mat */
            String fileSequencia = "C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\efetoras\\mateus\\fasta.txt";
            BufferedReader brSequencia = new BufferedReader(new FileReader(fileSequencia));

            /* Listas que conterão as proteínas */
            List<String> listaMat = new ArrayList<>();
            List<String> listaMat2 = new ArrayList<>();

            /* Cabeçaho das proteínas Her */
            String fileCabecalho2 = "C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\efetoras\\hercules\\cabecalho.txt";
            BufferedReader brCabecalho2 = new BufferedReader(new FileReader(fileCabecalho2));

            /* Sequência das proteínas Her */
            String fileSequencia2 = "C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\efetoras\\hercules\\fasta.txt";
            BufferedReader brSequencia2 = new BufferedReader(new FileReader(fileSequencia2));

            List<String> listaHer = new ArrayList<>();
            List<String> listaHer2 = new ArrayList<>();

            List<String> listaNovas = new ArrayList<>();
            
            while (brCabecalho.ready()) {

                listaMat.add(Arrays.toString(brCabecalho.readLine()
                        .split("----------------------------------------------------------------------")));

            }

            while (brSequencia.ready()) {

                listaMat.add(Arrays.toString(brSequencia.readLine()
                        .replace(" ", "")
                        .replace("\\r\\n\\r\\n", "")
                        .split("----------------------------------------------------------------------")));

            }

            while (brSequencia2.ready()) {

                listaHer.add(Arrays.toString(brSequencia2.readLine()
                        .replace(" ", "")
                        .replace("\\r\\n\\r\\n", "")
                        .split("----------------------------------------------------------------------")));

            }
            

            System.out.println("Mat: " + listaMat.size());
            System.out.println("Her: " + listaHer.size());

            /* Elima repetidas proteínas Mat */
            for (String prot : listaMat) {
                if (!listaMat2.contains(prot)) {
                    listaMat2.add(prot);
                }
            }

            /* Elima repetidas proteínas Her */
            for (String prot : listaHer) {
                if (!listaHer2.contains(prot)) {
                    listaHer2.add(prot);
                }
            }

            System.out.println("Mat2: " + listaMat2.size());
            System.out.println("Her2: " + listaHer2.size());

            int debug = 0;
            for (String protMat : listaMat2) {
                if (!listaHer2.contains(protMat)) {
                    System.out.println(protMat);
                    debug++;
                }

            }

            System.out.println(debug);

        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado: " + ex);
        } catch (IOException ex) {
            System.out.println("Falha I/O - E/S: " + ex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Quebra de array: " + ex);
        }

    }

}
