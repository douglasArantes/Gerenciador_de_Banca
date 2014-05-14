package org.bancafx.view.controller;

import org.bancafx.domain.entities.Produto;

import java.util.List;

/**
 * Created by Douglas on 14/05/2014.
 */
public interface IProdutosController {
    Produto novoProduto();
    Produto editarProduto();
    boolean excluirProduto();
    List<Produto> buscarProdutos();
}
