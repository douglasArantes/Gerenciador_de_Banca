package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.CNPJ;
import org.bancafx.domain.entities.Fornecedor;

import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public interface FornecedorRepository {

    void salvar(Fornecedor f);
    void excluir(Fornecedor f);
    void editar(Fornecedor f);
    Fornecedor buscarPorCNPJ(CNPJ cnpj);
    List<Fornecedor> buscarTodos();
}
