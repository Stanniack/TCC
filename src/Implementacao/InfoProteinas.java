package Implementacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfoProteinas {

    private final int qtdArquivos = 9;

    private List<String[]> extraiGenesTratadosTodos() {

        List<String[]> listaPotenciaisEfetoras = new ArrayList<>();
        listaPotenciaisEfetoras.add(new String[]{"pmid", "term_id"});

        try {

            for (int i = 1; i <= qtdArquivos; i++) {

                /* Alterar  */
                String file = "C:\\Users\\fiodo\\OneDrive\\Documentos\\TextMining\\" + i + ".tsv";
                BufferedReader br = new BufferedReader(new FileReader(file));
                String[] linhas;
                //br.readLine();

                while (br.ready()) {

                    linhas = br.readLine().split("\t");

                    // identifica se é gene
                    if (linhas[1].toLowerCase().equals("gene")) {

                        if (!listaPotenciaisEfetoras.toString().contains(linhas[2])) {
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

    private List<String[]> extraiGenesTratados() {

        List<String[]> listaPotenciaisEfetoras = new ArrayList<>();
        listaPotenciaisEfetoras.add(new String[]{"pmid", "term_id"});

        try {


            /* Alterar  */
            String file = "C:\\Users\\fiodo\\OneDrive\\Documentos\\TextMining\\9.tsv";
            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] linhas;
            //br.readLine();

            while (br.ready()) {

                linhas = br.readLine().split("\t");

                // identifica se é gene
                if (linhas[1].toLowerCase().equals("gene")) {
                    // Verifica se não há repetidos
                    if (!listaPotenciaisEfetoras.toString().contains(linhas[2])) {
                        listaPotenciaisEfetoras.add(new String[]{linhas[0], linhas[2]});
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

    /* Método traz as proteínas codificadas do proteinas.txt */
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
            System.out.println("-- INFORMAÇÕES PROTEINAS.TXT --");
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

        /* Pega as listas para a manipulação dos dados */
        List<String[]> genes = this.extraiGenesTratadosTodos();
        //List<String[]> genes = this.extraiGenesTratados();
        List<String[]> proteinas = this.extraiInfoProtIdentficadas();

        List<String[]> potenciaisEfetoras = new ArrayList<>();
        potenciaisEfetoras.add(new String[]{"pmid", "hgnc", "symbol", "name", "uniprot_id"});
        int potenciaisProteinas = 0;

        // Genes: pmid | hgnc
        // proteinas: hgnc | symbol | name | uniprot_id
        for (String[] gene : genes) {
            for (String[] proteina : proteinas) {
                if (gene[1].equals(proteina[0])) {
                    potenciaisEfetoras.add(new String[]{gene[0], proteina[0], proteina[1], proteina[2], proteina[3]});
                    potenciaisProteinas++;
                }
            }
        }
        
        /* Lista dos uniprots_ids da SecretE */
        List<String> uniprotIds = new UniprotID().getUniprotsFromSecretE();
        int efetorasSecretE = 0;
        
        for (String[] potencialEfetora : potenciaisEfetoras) {
            for (String uniprot_id : uniprotIds) {
                
                /* Se existir uniprot_id nas potenciais encontradas, remove pois já existe na base de dados*/
                if (potencialEfetora[4].equals(uniprot_id)) {
                    potenciaisEfetoras.remove(potencialEfetora);
                    efetorasSecretE++;
                }
            }
        }

        System.out.println("-- INFORMAÇÕES POTENCIAIS EFETORAS --");
        System.out.println("Potenciais proteínas encontradas: " + potenciaisProteinas);
        System.out.println("Efetoras identificas no SecretE: " + efetorasSecretE);
        System.out.println("Total de potenciais proteínas efetoras retornadas: " 
                + potenciaisProteinas + " - " + efetorasSecretE + " = "
                + (potenciaisProteinas - efetorasSecretE));
        System.out.println("____________________________________________________");

        return potenciaisEfetoras;
    }
}
