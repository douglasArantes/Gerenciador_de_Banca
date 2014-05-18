package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Produto;

import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public interface ProdutoRepository {

    void salvar(Produto p);
    void excluir(Produto p);
    void editar(Produto p);
    Produto buscarPorCodigo(String codigo);
    void baixarEstoque(Produto p, Integer qtd);
    List<Produto> buscarTodos();
}
