package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Funcionario;
import org.bancafx.domain.entities.Produto;
import org.bancafx.utils.jpa.JPAUtil;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public class FuncionarioRepositoryImp implements FuncionarioRepository, Serializable{
    @Override
    public void salvar(Funcionario f) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager().persist(f);

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public void excluir(Funcionario f) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager()
                .remove(JPAUtil.getEntityManager()
                        .getReference(Funcionario.class, f.getId()));

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public void editar(Funcionario f) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager().merge(f);

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public Funcionario buscarPorCPF(CPF cnpj) {
        //TODO implementar
        return null;
    }

    @Override
    public List<Funcionario> buscarTodos() {
        List<Funcionario> funcionarios;

        return JPAUtil.getEntityManager()
                .createNamedQuery(Funcionario.TODOS_FUNCIONARIOS, Funcionario.class)
                .getResultList();
    }
}