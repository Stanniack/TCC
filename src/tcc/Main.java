package tcc;

import utils.LeitorArquivoUtil;
import Implementacao.ProcuraArtigosPubtator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*@author Mateus Vitor Celestino */
public class Main {

    public static void main(String[] args) throws IOException {
        // new ProcuraArtigos().executa();
        // new ProcuraArtigosPorWebservice().executa();

        //List<String> lista = new LeitorArquivoUtil().leitorAll();
        
        //System.out.println(lista.size());
        
        //new ProcuraArtigosPubtator().searchArticles(new LeitorArquivoUtil().leitorAll());
        //System.out.println(new LeitorArquivoUtil().leitor().size());
        
        List<String> lista = new ArrayList<>();
        lista.add("9811111");
        lista.add("4445113");
        
        if(lista.get(0).contains("11"))
            System.out.println("Contains");

    }

}
