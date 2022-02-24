package loja.dao;

import loja.modelo.Cliente;
import loja.modelo.Produto;
import net.bytebuddy.asm.Advice;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar (Produto produto){
        this.em.persist(produto);
    }

    public Produto buscarPorId(Long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos(){
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNomeLista(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome2";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome2", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome){
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome2";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome2", nome)
                .getSingleResult();
    }

    public List<Produto> buscarPorCor(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.cor.nome = :nome2";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome2", nome)
                .getResultList();
    }

    public Produto buscarPorNome(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    /* ****** Consultas com parâmetros dinâmicos ****** */

    // Utilizando método "gambiarra" porém de mais fácil entendimento do JPQL
    public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro){

        //Filtra quais parâmetros foram inseridos ns query
        String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
               if (nome != null && !nome.trim().isEmpty()) {
                   jpql += " AND p.nome = :nome ";
               }
               if (preco != null){
                   jpql += " AND p.preco = :preco ";
               }
               if (dataCadastro != null){
                   jpql += " AND p.dataCadastro = :dataCadastro ";
               }

        //Seta os parâmetros inseridos
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
                if (nome != null && !nome.trim().isEmpty()){
                    query.setParameter("nome", nome);
                }
                if (preco != null){
                    query.setParameter("preco", preco);
                }
                if (dataCadastro != null){
                    query.setParameter("dataCadastro", dataCadastro);
                }

                return query.getResultList();
    }

    // Utilizando método CRITERIA da JPA para criar um método com parâmetros dinâmicos
    public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro){

        CriteriaBuilder builder = em.getCriteriaBuilder();

        // Cria um objeto do tipo PRODUTO onde ela tem como base a classe PRODUTO
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);

        //Substitui o SELECT e o FROM do JPQL
        Root<Produto> from = query.from(Produto.class);

        //Para inserir os AND no modo CRITERIA
        Predicate filtros = builder.and();
        if (nome != null && !nome.trim().isEmpty()) {
            filtros = builder.and(filtros, builder.equal(from.get("nome"), nome) );
        }
        if (preco != null){
            filtros = builder.and(filtros, builder.equal(from.get("preco"), preco) );
        }

        if (dataCadastro != null){
            filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro) );
        }

        // Substitui o WHERE
        query.where(filtros);

        return em.createQuery(query).getResultList();
    }

}

