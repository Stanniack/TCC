package tcc;

import Implementacao.ComparaInfoProteinas;
import utils.LeitorArquivoUtil;
import Implementacao.ProcuraArtigosPubtator;
import Implementacao.SequenciaFasta;
import Implementacao.UniprotID;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*@author Mateus Vitor Celestino */
public class Main {

    public static void main(String[] args) throws IOException {
        //List<String> lista = new UniprotID().getUniprotsFromSecretE();
       // List<String[]> lista = new ComparaInfoProteinas().identificaProteinas();
        new SequenciaFasta().obtemPotenciaisEfetoras();
//        
//        for (String[] item : lista) {
//            System.out.println(item[4]);
//        }
//      
    }

}
