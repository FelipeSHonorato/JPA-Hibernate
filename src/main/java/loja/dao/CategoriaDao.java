package loja.dao;

import loja.modelo.Categoria;
import loja.modelo.Produto;


import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar (Categoria categoria){
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria){
        this.em.merge(categoria);
    }

    public void remover (Categoria categoria){
        categoria = em.merge(categoria);
        this.em.remove(categoria);
    }

    public List<Categoria> buscarPorNome(String nome){
        String jpql = "SELECT p FROM Categoria p WHERE p.nome = :nome";
        return em.createQuery(jpql, Categoria.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public Categoria buscarPorId(Long id){
        return em.find(Categoria.class, id);
    }
}
