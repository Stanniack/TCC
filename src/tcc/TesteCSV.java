/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import Implementacao.InfoProteinas;
import Implementacao.SequenciaFasta;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class TesteCSV {

    public static void main(String[] args) throws IOException {
//        String[] cabecalho = {"nome", "idade", "telefone"};
//
//        List<String[]> linhas = new ArrayList<>();
//        linhas.add(new String[]{"Joao", "35", "joao@dicasdejava.com.br"});
//        linhas.add(new String[]{"Maria", "23", "maria@dicasdeprogramacao.com.br"});
//        linhas.add(new String[]{"Ana", "25", "ana@dicasdejava.com.br"});
//
//        Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\pessoas.tsv"));
//        CSVWriter csvWriter = new CSVWriter(writer);
//
//        csvWriter.writeNext(cabecalho);
//        csvWriter.writeAll(linhas);
//
//        csvWriter.flush();
//        writer.close();

      //  new InfoProteinas().identificaProteinas();
          //new SequenciaFasta().obtemPotenciaisEfetoras();
          new SequenciaFasta().obtemPotenciaisEfetoras();
        

    }

}
