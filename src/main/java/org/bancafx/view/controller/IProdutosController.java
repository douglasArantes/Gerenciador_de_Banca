package org.bancafx.view.controller;

import org.bancafx.domain.entities.Produto;

import java.io.IOException;
import java.util.List;

/**
 * Created by Douglas on 14/05/2014.
 */
public interface IProdutosController {
    void novoProduto();
    void editarProduto();
    void excluirProduto();
    void buscarProdutos();
}
