package loja.testes;

import loja.dao.CategoriaDao;
import loja.dao.ProdutoDao;
import loja.modelo.Categoria;
import loja.modelo.Produto;
import loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarTodos();
        todos.forEach(p2 -> System.out.println(p.getNome()));

        List<Produto> todos2 = produtoDao.buscarPorNome("Xiaomi Redmi");
        todos.forEach(p2 -> System.out.println(p.getNome()));

        List<Produto> todos3 = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        todos.forEach(p2 -> System.out.println(p.getNome()));

        BigDecimal todos4 = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
        todos.forEach(p2 -> System.out.println(p.getPreco()));
    }

    private static void cadastrarProduto() {
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
