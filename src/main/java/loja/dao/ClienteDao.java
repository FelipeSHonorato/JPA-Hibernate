package loja.dao;

import loja.modelo.Cliente;
import loja.modelo.Pedido;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDao {

    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar (Cliente cliente){
        this.em.persist(cliente);
    }

    public Cliente buscarPorId(Long id){
        return em.find(Cliente.class, id);
    }

    public List<Cliente> buscarTodos(){
        String jpql = "SELECT p FROM Cliente p";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }

    public Cliente buscarPorNome(String nome){
        String jpql = "SELECT p FROM Cliente p WHERE p.dadosPessoais.nome = :nome";
        return em.createQuery(jpql, Cliente.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

}
