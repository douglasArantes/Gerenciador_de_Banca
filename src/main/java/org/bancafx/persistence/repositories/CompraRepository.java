package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Compra;
import org.bancafx.domain.entities.Funcionario;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public interface CompraRepository {
    void salvar(Compra c);
    void excluir(Compra c);
    List<Compra> buscarTodas();
    List<Compra> buscarEntrePeriodo(LocalDate inicio, LocalDate fim);

}
