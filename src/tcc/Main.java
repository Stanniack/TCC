package tcc;

import Implementacao.EfetorasRepetidas;
import Implementacao.InfoProteinas;
import utils.LeitorArquivoUtil;
import Implementacao.ProcuraArtigosPubtator;
import Implementacao.SequenciaFasta;
import Implementacao.UniprotID;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*@author Mateus Vitor Celestino */
public class Main {

    public static void main(String[] args) throws IOException {
//        new SequenciaFasta().obtemPotenciaisEfetoras();
        new EfetorasRepetidas().eliminaRepetidas();
//        List<String> lista = Arrays.asList("teste", "vitor", "mateus");
//        List<String> lista2 = Arrays.asList("teste2", "celestino", "vitor");
//
//        for (String t : lista) {
//            if (!lista2.contains(t)) {
//                System.out.println(t);
//            }
//
//        }
        
        //System.out.println(lista2.size());

    }

}
