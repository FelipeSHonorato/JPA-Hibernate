package loja.testes;

import loja.dao.PedidoDao;
import loja.modelo.Pedido;
import loja.util.JPAUtil;

import javax.persistence.EntityManager;

import java.sql.SQLOutput;

import static loja.testes.CadastroDePedido.cadastroPedido;


public class PerformanceConsultas{

    public static void main(String[] args) {
        // Povoando banco de dados através dos cadastros criados na classe Cadastro
        cadastroPedido();

        EntityManager em = JPAUtil.getEntityManager();

        /* EFETUANDO UMA CONSULTA SIMPLES NO JPA*/
        //Efetuando uma consulta direta de um pedido para acessar a sua data
        Pedido pedido = em.find(Pedido.class, 1l);

        //Efetuando consulta ao banco de dados e solicitando a data do pedido 1 - Já com alteração de performance
        System.out.println(pedido.getData());

        //Efetuando consulta ao banco de dados e solicitando o nome do cliente do pedido 1 - Já com alteração de performance
        //Caso o EntityManager estivesse sido fechado pelo JPA antes da execução do código abaixo, iria disparar uma exception LazyInitializationException
        System.out.println(pedido.getCliente().getNome());

        /* EFETUANDO UMA CONSULTA UTILIZANDO QUERRY COM FECH PARA ABSTRAÇÃO DE MAIS DE UM DADO DE ENTIDADES RELACIONADAS */

        PedidoDao pedidoDao = new PedidoDao(em);

        Pedido pedido1 = pedidoDao.buscarPedidoComCliente(1l); // Carregando o método com a FETCH que já carrega o nome do cliente

        em.close();

        System.out.println(pedido1.getCliente().getNome()); // Agora mesmo fechando o EntityManager antes o nome do cliente já esta carregado para consulta

    }
}
