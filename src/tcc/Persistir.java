package tcc;

import javax.persistence.EntityManager;
import modelo.Artigo;
import utils.JPAUtil;

public class Persistir {

    public static void main(String[] args) {

        Artigo artigo = new Artigo();
        artigo.setLinha1("dsdasdasdas");
        
        EntityManager em = JPAUtil.getInstance();
        
        em.getTransaction().begin();
        em.persist(artigo);
        em.getTransaction().commit();
        em.close();
        
    }
}
