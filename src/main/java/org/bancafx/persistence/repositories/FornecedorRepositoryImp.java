package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.CNPJ;
import org.bancafx.domain.entities.Fornecedor;
import org.bancafx.domain.entities.Funcionario;
import org.bancafx.utils.jpa.JPAUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public class FornecedorRepositoryImp implements FornecedorRepository, Serializable {
    @Override
    public void salvar(Fornecedor f) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager().persist(f);

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public void excluir(Fornecedor f) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager()
                .remove(JPAUtil.getEntityManager()
                        .getReference(Fornecedor.class, f.getId()));

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public void editar(Fornecedor f) {
        JPAUtil.getEntityManager().getTransaction().begin();

        JPAUtil.getEntityManager().merge(f);

        JPAUtil.getEntityManager().getTransaction().commit();
        JPAUtil.closeEntityManager();
    }

    @Override
    public Fornecedor buscarPorCNPJ(CNPJ cnpj) {
        //TODO implementar
        return null;
    }

    @Override
    public List<Fornecedor> buscarTodos() {
        List<Fornecedor> fornecedors;

        return JPAUtil.getEntityManager()
                .createNamedQuery(Fornecedor.TODOS_FORNECEDORES, Fornecedor.class)
                .getResultList();
    }
}