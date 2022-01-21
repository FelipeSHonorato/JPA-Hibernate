package loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    // Criando uma variável estática com informação da DB a ser utilizada
    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("JPA-Hibernate");

    // Criando um método para as classes acessarem a conexão
    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }

}
