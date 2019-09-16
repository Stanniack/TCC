package Implementacao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeitorArquivoUtil {

    private int RESULT = 5;

    public List<String> leitorAll() {

        List<String> lista = new ArrayList<>();

        while (RESULT >= 1) {
            try {
                Scanner in = new Scanner(new FileReader("C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\TCC\\PMIDS\\pubmed_result" + RESULT + ".txt"));

                while (in.hasNextLine()) {
                    String line = in.nextLine();

                    if (!lista.contains(line)) {
                        lista.add(line);
                    }

                }

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado: " + e);
            }

            RESULT--;
        }

        return lista;
    }

    public List<String> leitor() {
        List<String> lista = new ArrayList<>();

        try {
            Scanner in = new Scanner(new FileReader("C:\\Users\\fiodo\\OneDrive\\Área de Trabalho\\TCC\\PMIDS\\pubmed_result5.txt"));

            while (in.hasNextLine()) {
                String line = in.nextLine();

                if (!lista.contains(line)) {
                    lista.add(line);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e);
        }

        return lista;
    }

}
