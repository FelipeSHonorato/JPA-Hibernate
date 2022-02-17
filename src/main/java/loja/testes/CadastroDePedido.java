package loja.testes;

import loja.dao.ClienteDao;
import loja.dao.PedidoDao;
import loja.dao.ProdutoDao;
import loja.modelo.Cliente;
import loja.modelo.ItemPedido;
import loja.modelo.Pedido;
import loja.modelo.Produto;
import loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido extends Cadastros {

    public static void main(String[] args) {

        // Povoando banco de dados através dos cadastros criados na classe Cadastro
        cadastroCliente();
        cadastroProduto();

        //Recuperando dados povoados
        EntityManager em = JPAUtil.getEntityManager();

        //Criando uma nova instância para acesso ao clienteDao e produtoDao
        ClienteDao clienteDao = new ClienteDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        //Buscando na lista povoada de cliente um cliente especifico por ID
        Cliente c = clienteDao.buscarPorId(1l);

        //Buscando na lista povoada de cliente um cliente especifico por nome
        Cliente c1 = clienteDao.buscarPorNome("Janaina");

        //Buscando na lista povoada de produto um produto especifico por ID
        Produto p = produtoDao.buscarPorId(1l);

        //Buscando na lista povoada de produto um produto especifico por nome
        Produto p2 = produtoDao.buscarPorNome("IPhone 12");


        //Inicia conexão e informa que será efetuada uma ação
        em.getTransaction().begin();

        //Criando Pedido 1
        Pedido pedido = new Pedido(c);
        pedido.adicionarItem(new ItemPedido(1, pedido, p));

        //Criando Pedido 2
        Pedido pedido2 = new Pedido(c1);
        pedido.adicionarItem(new ItemPedido(2, pedido, p2));


        //Cadastrando o pedido
        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);


        //Commitando as ações no banco de dados
        em.getTransaction().commit();

        //Somando valor total de todos os pedidos criados
        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("VALOR TOTAL: " + totalVendido);

        //Fechando conexão
        em.close(); //Fechando a conexão
    }
}
