package loja.testes;

import loja.dao.ClienteDao;
import loja.dao.PedidoDao;
import loja.dao.ProdutoDao;
import loja.modelo.Cliente;
import loja.modelo.ItemPedido;
import loja.modelo.Pedido;
import loja.modelo.Produto;
import loja.util.JPAUtil;
import loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido extends Cadastros {

    static void cadastroPedido() {

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
            Cliente c2 = clienteDao.buscarPorNome("Rodrigo");
            Cliente c3 = clienteDao.buscarPorNome("Anderson");

            //Buscando na lista povoada de produto um produto especifico por ID
            Produto p = produtoDao.buscarPorId(1l);

            //Buscando na lista povoada de produto um produto especifico por nome
            Produto p2 = produtoDao.buscarPorNome("IPhone 12");
            Produto p3 = produtoDao.buscarPorNome("Intel I7");
            Produto p4 = produtoDao.buscarPorNome("TV Samsung");


            //Inicia conexão e informa que será efetuada uma ação
            em.getTransaction().begin();

            //Criando Pedido 1
            Pedido pedido = new Pedido(c);
            pedido.adicionarItem(new ItemPedido(1, pedido, p));
            pedido.adicionarItem(new ItemPedido(1, pedido, p4));

            //Criando Pedido 2
            Pedido pedido2 = new Pedido(c1);
            pedido.adicionarItem(new ItemPedido(2, pedido2, p2));
            pedido.adicionarItem(new ItemPedido(1, pedido2, p3));

            //Criando Pedido 3
            Pedido pedido3 = new Pedido(c2);
            pedido.adicionarItem(new ItemPedido(1, pedido3, p3));

            //Criando Pedido 4
            Pedido pedido4 = new Pedido(c3);
            pedido.adicionarItem(new ItemPedido(3, pedido4, p4));
            pedido.adicionarItem(new ItemPedido(1, pedido4, p));


            //Cadastrando o pedido
            PedidoDao pedidoDao = new PedidoDao(em);
            pedidoDao.cadastrar(pedido);
            pedidoDao.cadastrar(pedido2);
            pedidoDao.cadastrar(pedido3);
            pedidoDao.cadastrar(pedido4);


            //Commitando as ações no banco de dados
            em.getTransaction().commit();

            //Somando valor total de todos os pedidos criados
            BigDecimal totalVendido = pedidoDao.valorTotalVendido();
            System.out.println("VALOR TOTAL: " + totalVendido);

            List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
            relatorio.forEach(System.out::println);


            //Fechando conexão
            em.close(); //Fechando a conexão
        }
    }
