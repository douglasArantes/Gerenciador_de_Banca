package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Funcionario;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public interface FuncionarioRepository {

    void salvar(Funcionario f);
    void excluir(Funcionario f);
    void editar(Funcionario f);
    Funcionario buscarPorCPF(CPF cnpj);
    List<Funcionario> buscarTodos();
}
