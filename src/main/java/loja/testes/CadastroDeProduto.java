package loja.testes;

import loja.dao.CategoriaDao;
import loja.dao.ProdutoDao;
import loja.modelo.Categoria;
import loja.modelo.Produto;
import loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi","Preto",new BigDecimal("800"), celulares);

        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin(); //Inicia conexão e informa que será efetuada uma ação

        categoriaDao.cadastrar(celulares);//Ação a ser executada
        produtoDao.cadastrar(celular); //Ação a ser executada

        em.getTransaction().commit(); //Envio para o banco de dados a ação a ser executada
        em.close(); //Fechando a conexão
    }
}
