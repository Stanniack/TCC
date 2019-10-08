package Implementacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComparaInfoProteinas {

    private int qtdArquivos = 5;

    private List<String[]> extraiGenesTratados() {

        List<String[]> listaPotenciaisEfetoras = new ArrayList<>();
        listaPotenciaisEfetoras.add(new String[]{"pmid", "term_id"});

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

                        // if suspeito, verifique todos os genes pra testar
                        if (!listaPotenciaisEfetoras.contains(linhas[2])) {
                            listaPotenciaisEfetoras.add(new String[]{linhas[0], linhas[2]});
                        }

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

    private List<String[]> extraiInfoProtIdentficadas() {

        List<String[]> lista = new ArrayList<>();

        try {

            String file = "C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\TCC\\proteinas.txt";
            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] linhas;

            lista.add(new String[]{"hgnc", "symbol", "name", "uniprot_id"});
            br.readLine();
            int debug = 0;
            int branco = 0;
            int erro = 0;

            while (br.ready()) {

                linhas = br.readLine().split("\t");

                try {
                    debug++;

                    if (!linhas[25].equals("")) {
                        lista.add(new String[]{linhas[0].replace("HGNC:", ""), linhas[1], linhas[2], linhas[25]});
                        
                    } else {
                        lista.add(new String[]{linhas[0].replace("HGNC:", ""), linhas[1], linhas[2], "NULL"});
                        branco++;
                    }

                } catch (ArrayIndexOutOfBoundsException ex) {
                    //System.out.println("Quebra de array na linha: " + debug);
                    lista.add(new String[]{linhas[0].replace("HGNC:", ""), linhas[1], linhas[2], "ERRO"});
                    erro++;
                }

            }

            System.out.println("____________________________________________________");
            System.out.println("Espaços em branco: " + branco + " QTD erros: " + erro
                    + "\nTotal de linhas perdidas: " + (branco + erro));
            System.out.println("____________________________________________________");

            // System.out.println(lista.size());
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado: " + ex);
        } catch (IOException ex) {
            System.out.println("Falha I/O - E/S: " + ex);
        }

        return lista;
    }

    public List<String[]> identificaProteinas() {
        List<String[]> genes = this.extraiGenesTratados();
        List<String[]> proteinas = this.extraiInfoProtIdentficadas();
        List<String[]> potenciaisEfetoras = new ArrayList<>();
        potenciaisEfetoras.add(new String[]{"pmid","hgnc","symbol","name","uniprot_id"});
        int debug = 0;

        // Genes: pmid | hgnc
        // proteinas: hgnc | symbol | name | uniprot_id
        for (String[] gene : genes) {
            for (String[] proteina : proteinas) {
                if (gene[1].equals(proteina[0])) {
                    potenciaisEfetoras.add(new String[]{gene[0], proteina[0], proteina[1], proteina[2], proteina[3]});
                    debug++;
                }
            }
        }
        
//        for (String[] potenciais : potenciaisEfetoras) {
//            System.out.println(potenciais[1] + " " + potenciais[4]);
//        }

        System.out.println("Potenciais proteínas encontradas: " + debug);
        System.out.println("____________________________________________________");
        
        return potenciaisEfetoras;
    }
}
