package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.GeneroProduto;
import org.bancafx.domain.entities.Produto;

import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public interface GeneroProdutoRepository {

    void salvar(GeneroProduto gp);
    void excluir(GeneroProduto gp );
    void editar(GeneroProduto gp);
    GeneroProduto buscarPorId(Integer id);
    List<GeneroProduto> buscarTodos();
}
