package loja.testes;

import loja.modelo.Produto;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Xiaomi Redmi");
        celular.setDescricao("Preto");
        celular.setPreco(new BigDecimal("800"));

        //Criação da conexão com o banco de dados através de uma criação de EntityFactory
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("JPA-Hibernate");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin(); //Inicia conexão e informa que será efetuada uma ação
        em.persist(celular); //Ação a ser executada
        em.getTransaction().commit(); //Envio para o banco de dados a ação a ser executada
        em.close(); //Fechando a conexão
    }
}
