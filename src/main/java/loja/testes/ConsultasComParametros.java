package loja.testes;

import loja.dao.ProdutoDao;
import loja.modelo.Produto;
import loja.util.JPAUtil;

import javax.persistence.EntityManager;

import java.util.List;

import static loja.testes.CadastroDePedido.cadastroPedido;

public class ConsultasComParametros {

    public static void main(String[] args) {

        cadastroPedido();

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);

        List<Produto> produto = produtoDao.buscarPorParametros("Xiaomi Redmi",null, null);
        for (Produto produto1 : produto) {
            System.out.println(produto1);
            System.out.println(produto1.getPreco());
        }

        List<Produto> produtoComCriteria = produtoDao.buscarPorParametrosComCriteria("IPhone 12", null, null);
            for (Produto produto2 : produtoComCriteria) {
            System.out.println(produto2);
            System.out.println(produto2.getCategoria());
        }
    }

}
