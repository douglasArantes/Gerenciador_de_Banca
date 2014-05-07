package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Compra;
import org.bancafx.domain.entities.Venda;
import org.bancafx.utils.jpa.JPAUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public class CompraRepositoryImp implements CompraRepository, Serializable {
    @Override
    public void salvar(Compra c) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager().persist(c);

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public void excluir(Compra c) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager()
                .remove(JPAUtil.getEntityManager()
                        .getReference(Compra.class, c.getId()));

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public List<Compra> buscarTodas() {
        List<Compra> compras;

        return compras = JPAUtil.getEntityManager()
                .createNamedQuery(Compra.TODAS_COMPRAS, Compra.class)
                .getResultList();
    }

    @Override
    public List<Compra> buscarEntrePeriodo(LocalDate inicio, LocalDate fim) {
        //TODO implementar
        return null;
    }
}
