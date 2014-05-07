package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.GeneroProduto;
import org.bancafx.domain.entities.Produto;
import org.bancafx.utils.jpa.JPAUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */

public class GeneroProdutoRepositoryImp implements GeneroProdutoRepository, Serializable {


    @Override
    public void salvar(GeneroProduto gp) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager().persist(gp);

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public void excluir(GeneroProduto gp) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager()
                .remove(JPAUtil.getEntityManager()
                        .getReference(GeneroProduto.class, gp.getId()));

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public void editar(GeneroProduto gp) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager().merge(gp);

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public List<GeneroProduto> buscarTodos() {
        List<GeneroProduto> generoProdutos;

        return generoProdutos = JPAUtil.getEntityManager()
                .createNamedQuery(GeneroProduto.TODOS_GENEROS, GeneroProduto.class)
                .getResultList();
    }
}
