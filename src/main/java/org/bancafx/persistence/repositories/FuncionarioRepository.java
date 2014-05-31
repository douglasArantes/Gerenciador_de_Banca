package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Funcionario;

import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public interface FuncionarioRepository {

    void salvar(Funcionario f);
    void excluir(Funcionario f);
    void editar(Funcionario f);
    Funcionario buscarPorCPF(String cpf);
    List<Funcionario> buscarTodos();
}
