package tcc;

import Implementacao.LeitorArquivoUtil;
import Implementacao.ProcuraArtigosPubtator;
import java.io.IOException;
import java.util.List;

/*@author Mateus Vitor Celestino */
public class Main {

    public static void main(String[] args) throws IOException {
        // new ProcuraArtigos().executa();
        // new ProcuraArtigosPorWebservice().executa();

        //List<String> lista = new LeitorArquivoUtil().leitorAll();
        
        //System.out.println(lista.size());
        
        new ProcuraArtigosPubtator().searchArticles(new LeitorArquivoUtil().leitorAll());

    }

}
