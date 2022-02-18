package loja.testes;

import loja.dao.CategoriaDao;
import loja.dao.ClienteDao;
import loja.dao.ProdutoDao;
import loja.modelo.Categoria;
import loja.modelo.Cliente;
import loja.modelo.Cor;
import loja.modelo.Produto;
import loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Cadastros {

    static void cadastroCategoria(){
        EntityManager em = JPAUtil.getEntityManager();

        //Criando categorias para o Banco de Dados
        Categoria celulares = new Categoria("CELULARES");
        Categoria computadores = new Categoria("COMPUTADORES");
        Categoria televisores = new Categoria("TELEVISORES");

        //Instanciando a DAO para executar as ações no Banco de Dados
        CategoriaDao categoriaDao = new CategoriaDao(em);

        //Inicia conexão e informa que será efetuada uma ação
        em.getTransaction().begin();

        //Ações a serem executadas das DAOs no Banco de Dados
        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(computadores);
        categoriaDao.cadastrar(televisores);

        //Commitando as ações no banco de dados
        em.getTransaction().commit();

        //Fechando conexão
        em.close(); //Fechando a conexão

    }

    static void cadastroProduto(){
        EntityManager em = JPAUtil.getEntityManager();

        //Executa o cadastramento das categorias
        cadastroCategoria();

        //Buscando uma categoria cadastrada para ser utilizada na entidade atual
        CategoriaDao categoriaDao = new CategoriaDao(em);
        Categoria categoria = categoriaDao.buscarPorId(1l);
        Categoria categoria2 = categoriaDao.buscarPorId(2l);
        Categoria categoria3 = categoriaDao.buscarPorId(3l);


        //Criando os produtos
        Produto celular1 = new Produto("Xiaomi Redmi", "8gb RAM", new BigDecimal("800"), categoria, Cor.VERMELHO);
        Produto celular2 = new Produto("IPhone 12", "12gb RAM", new BigDecimal("1500"), categoria, Cor.PRETO);
        Produto computador = new Produto("Intel I7", "32gb RAM", new BigDecimal("7500"), categoria2, Cor.AZUL);
        Produto televisor = new Produto("TV Samsung","42 pol", new BigDecimal("1800"), categoria3, Cor.VERMELHO );

        //Instanciando a DAO para executar as ações no Banco de Dados
        ProdutoDao produtoDao = new ProdutoDao(em);

        //Inicia conexão e informa que será efetuada uma ação
        em.getTransaction().begin();

        //Ações a serem executadas das DAOs no Banco de Dados
        produtoDao.cadastrar(celular1);
        produtoDao.cadastrar(celular2);
        produtoDao.cadastrar(computador);
        produtoDao.cadastrar(televisor);


        //Commitando as ações no banco de dados
        em.getTransaction().commit();

        //Fechando conexão
        em.close(); //Fechando a conexão

    }

    static void cadastroCliente() {
        EntityManager em = JPAUtil.getEntityManager();

        //Criando clientes
        Cliente cliente1 = new Cliente("Rodrigo", "31674750555");
        Cliente cliente2 = new Cliente("Janaina", "33645863255");
        Cliente cliente3 = new Cliente("Anderson", "24563230025");

        //Instanciando a DAO para executar as ações no Banco de Dados
        ClienteDao clienteDao = new ClienteDao(em);

        //Inicia conexão e informa que será efetuada uma ação
        em.getTransaction().begin();

        //Ações a serem executadas das DAOs no Banco de Dados
        clienteDao.cadastrar(cliente1);
        clienteDao.cadastrar(cliente2);
        clienteDao.cadastrar(cliente3);

        //Commitando as ações no banco de dados
        em.getTransaction().commit();

        //Fechando conexão
        em.close(); //Fechando a conexão

    }

}
