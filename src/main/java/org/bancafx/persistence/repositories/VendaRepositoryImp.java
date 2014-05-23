package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Funcionario;
import org.bancafx.domain.entities.Produto;
import org.bancafx.domain.entities.Venda;
import org.bancafx.utils.jpa.JPAUtil;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public class VendaRepositoryImp implements VendaRepository, Serializable {

    private EntityManager em;

    public VendaRepositoryImp(){
        em = JPAUtil.getEntityManager();
    }

    @Override
    public void salvar(Venda v) {
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void excluir(Venda v) {
        em.getTransaction().begin();
        em.remove(em.getReference(Venda.class, v.getId()));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Venda> buscarTodas() {
        return em.createNamedQuery(Venda.TODAS_VENDAS, Venda.class).getResultList();
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