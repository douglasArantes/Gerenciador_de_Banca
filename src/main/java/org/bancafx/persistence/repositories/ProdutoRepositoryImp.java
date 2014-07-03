package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Produto;
import org.bancafx.utils.jpa.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Douglas on 06/05/2014.
 */
public class ProdutoRepositoryImp implements ProdutoRepository, Serializable{

    private EntityManager em;

    public ProdutoRepositoryImp(){
        em = JPAUtil.getEntityManager();
    }

    public void salvar(Produto p){
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    public void excluir(Produto p){
        em.getTransaction().begin();
        try {
            em.remove(em.getReference(Produto.class, p.getCodigo()));
        }catch (RollbackException re){
            throw new RuntimeException("Venda de produto já realizada");
        }
        em.getTransaction().commit();
        em.close();
    }

    public void editar(Produto p){
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Produto buscarPorCodigo(String codigo) {
        String jpql = "FROM Produto p WHERE p.codigo = :codigo";

        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class).setParameter("codigo", codigo);

        Produto p = null;

        try {
          p = query.getSingleResult();
        }catch (NoResultException nre){
            nre.getMessage();
        }
        return p;
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        String jpql = "FROM Produto p WHERE p.nome LIKE :nome";

        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class).setParameter("nome", "%" + nome + "%");

        return query.getResultList();
    }
    @Override
    public List<Produto> buscarTodos() {

        return em.createNamedQuery(Produto.TODOS_PRODUTOS, Produto.class).getResultList();
    }
}
