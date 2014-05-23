package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Funcionario;
import org.bancafx.domain.entities.Produto;
import org.bancafx.utils.jpa.JPAUtil;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public class FuncionarioRepositoryImp implements FuncionarioRepository, Serializable{

    private EntityManager em;

    public FuncionarioRepositoryImp(){
        em = JPAUtil.getEntityManager();
    }

    @Override
    public void salvar(Funcionario f) {
        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void excluir(Funcionario f) {
        em.getTransaction().begin();
        em.remove(em.getReference(Funcionario.class, f.getCpf()));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void editar(Funcionario f) {
        em.getTransaction().begin();
        em.merge(f);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return em.createNamedQuery(Funcionario.TODOS_FUNCIONARIOS, Funcionario.class).getResultList();
    }

    @Override
    public Funcionario buscarPorCPF(CPF cnpj) {
        //TODO implementar
        return null;
    }
}