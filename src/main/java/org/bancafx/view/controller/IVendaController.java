package org.bancafx.view.controller;

import org.bancafx.domain.entities.ItemVenda;

import java.util.List;

/**
 * Created by Douglas on 15/05/2014.
 */
public interface IVendaController {

    void finalizar();
    void cancelar();
    void removerItem();
    void removerTodosItens();
    void mostraTotal();
    void calcularTroco();
}
