package Implementacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComparaInfoProteinas {

    private int qtdArquivos = 5;

    public List<String[]> extraiInfoDadosTratados() {

        List<String[]> listaPotenciaisEfetoras = new ArrayList<>();
        listaPotenciaisEfetoras.add(new String[]{"pmid", "type", "term_id"});

        try {

            for (int i = 1; i <= qtdArquivos; i++) {

                String file = "C:\\Users\\fiodo\\OneDrive\\Documentos\\TextMining\\resultados" + i + "_tratado.tsv";
                BufferedReader br = new BufferedReader(new FileReader(file));
                String[] linhas;
                //br.readLine();

                while (br.ready()) {

                    linhas = br.readLine().split("\t");

                    // identifica se é gene
                    if (linhas[1].toLowerCase().equals("gene")) {
                        //System.out.println("pmid: " + linhas[0] + " type: " + linhas[1] + " term_id: " + linhas[2]);
                        listaPotenciaisEfetoras.add(new String[]{linhas[0], linhas[1], linhas[2]});
                    }

                }
            }

            //System.out.println("Genes:" + listaPotenciaisEfetoras.size());
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado: " + ex);
        } catch (IOException ex) {
            System.out.println("Falha I/O - E/S: " + ex);
        }

        return listaPotenciaisEfetoras;

    }

    // apenas o GNHC
    public List<String> extraiInfoDadosTratadosDEBUG() {

        List<String> listaPotenciaisEfetoras = new ArrayList<>();

        try {

            for (int i = 1; i <= qtdArquivos; i++) {

                String file = "C:\\Users\\fiodo\\OneDrive\\Documentos\\TextMining\\resultados" + i + "_tratado.tsv";
                BufferedReader br = new BufferedReader(new FileReader(file));
                String[] linhas;
                //br.readLine();

                while (br.ready()) {

                    linhas = br.readLine().split("\t");

                    // identifica se é gene
                    if (linhas[1].toLowerCase().equals("gene")) {
//                        if (!listaPotenciaisEfetoras.contains(linhas[2])) {
                        listaPotenciaisEfetoras.add(linhas[2]);
//                        }

                    }

                }
            }

            //System.out.println("Genes:" + listaPotenciaisEfetoras.size());
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado: " + ex);
        } catch (IOException ex) {
            System.out.println("Falha I/O - E/S: " + ex);
        }

        return listaPotenciaisEfetoras;

    }

    public void extraiInfoProtIdentficadas() {

        List<String> listaProtIdentificadas = new ArrayList<>();
        try {

            String file = "C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\TCC\\proteinas.txt";
            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] linhas;
            //List<String> lista = new ArrayList<>();
            //br.readLine();
            int debug1 = 0;

            while (br.ready()) {

                try {
                    linhas = br.readLine().split("\t");
                    System.out.println(linhas[25]);
                } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
                    debug1++;
                }

                //lista.add(linhas[3]);
                //listaProtIdentificadas.add(linhas[0].replace("HGNC:", ""));
            }
            
            System.out.println(debug1);

            // System.out.println(lista.size());
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado: " + ex);
        } catch (IOException ex) {
            System.out.println("Falha I/O - E/S: " + ex);
        }

        List<String> lista = this.extraiInfoDadosTratadosDEBUG();
        int debug = 0;

//        System.out.println(lista.size());
//        
//        for (int i = 0; i < lista.size(); i++) {
//            for (int j = 0; j < listaProtIdentificadas.size(); j++) {
//
//                if (lista.get(i).equals(listaProtIdentificadas.get(j))) {
//                    System.out.println(lista.get(i) + " = " + listaProtIdentificadas.get(j));
//                    debug++;
//                }
//            }
//        }
//
//        System.out.println("debug: " + debug);
    }
}
