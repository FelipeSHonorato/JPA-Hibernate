package loja.dao;

import loja.modelo.Pedido;
import loja.modelo.Produto;
import loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar (Pedido pedido){
        this.em.persist(pedido);
    }

    public Pedido buscarPorId(Long id){
        return em.find(Pedido.class, id);
    }

    public List<Pedido> buscarTodos(){
        String jpql = "SELECT p FROM Pedido p";
        return em.createQuery(jpql, Pedido.class).getResultList();
    }

    public List<Pedido> buscarPorNome(String nome){
        String jpql = "SELECT p FROM Pedido p WHERE p.nome = :nome2";
        return em.createQuery(jpql, Pedido.class)
                .setParameter("nome2", nome)
                .getResultList();
    }

    //Método para somar o valor total de todos os pedidos criados
    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    public List<RelatorioDeVendasVo> relatorioDeVendas(){
        String jpql = "SELECT new loja.vo.RelatorioDeVendasVo( "
                + "produto.nome, "
                + "SUM(item.quantidade), "
                + "MAX(pedido.data)) "
                + "FROM Pedido pedido "
                + "JOIN pedido.itens item "
                + "JOIN item.produto produto "
                + "GROUP BY produto.nome "
                + "ORDER BY SUM(item.quantidade) DESC";
        return em.createQuery(jpql, RelatorioDeVendasVo.class)
                .getResultList();
    }
    /* Para realizar a consulta avançada acima foi criada uma classe especifica para separar as consultas (cada linha)
     A classe deve conter o caminho completo ao ser mencionada no SELECT*/
}
