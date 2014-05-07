package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Produto;
import org.bancafx.utils.jpa.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Douglas on 06/05/2014.
 */
public class ProdutoRepositoryImp implements ProdutoRespository, Serializable{

    public void salvar(Produto p){
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager().persist(p);

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    public void excluir(Produto p){
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager()
                .remove(JPAUtil.getEntityManager()
                        .getReference(Produto.class, p.getCodigo()));

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    public void editar(Produto p){
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager().merge(p);

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public Produto buscarPorCodigo(Long codigo) {
        String jpql = "FROM Produto p WHERE p.codigo = :codigo";

        TypedQuery<Produto> query = JPAUtil.getEntityManager()
                .createQuery(jpql, Produto.class)
                .setParameter("codigo", codigo);

        return query.getSingleResult();
    }

    @Override
    public void baixarEstoque(Produto p, Integer qtd) {
        //TODO implementar
    }

    @Override
    public List<Produto> buscarTodos() {
        List<Produto> produtos;
        return produtos = JPAUtil.getEntityManager()
                .createNamedQuery(Produto.TODOS_PRODUTOS, Produto.class)
                .getResultList();
    }

}
