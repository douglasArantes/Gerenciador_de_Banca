package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Funcionario;
import org.bancafx.domain.entities.Produto;
import org.bancafx.domain.entities.Venda;
import org.bancafx.utils.jpa.JPAUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public class VendaRepositoryImp implements VendaRepository, Serializable {
    @Override
    public void salvar(Venda v) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager().persist(v);

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public void excluir(Venda v) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager()
                .remove(JPAUtil.getEntityManager()
                        .getReference(Venda.class, v.getId()));

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public List<Venda> buscarTodas() {
        List<Venda> vendas;
        return vendas = JPAUtil.getEntityManager()
                .createNamedQuery(Venda.TODAS_VENDAS, Venda.class)
                .getResultList();
    }

    @Override
    public List<Venda> buscarPorFuncionario(Funcionario f) {
        //TODO implementar
        return null;
    }

    @Override
    public List<Venda> buscarEntrePeriodo(LocalDate inicio, LocalDate fim) {
        //TODO implementar
        return null;
    }
}
