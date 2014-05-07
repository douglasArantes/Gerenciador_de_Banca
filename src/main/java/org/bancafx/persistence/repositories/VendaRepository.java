package org.bancafx.persistence.repositories;

import org.bancafx.domain.entities.Funcionario;
import org.bancafx.domain.entities.Venda;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Douglas on 07/05/2014.
 */
public interface VendaRepository {

    void salvar(Venda v);
    void excluir(Venda v);
    List<Venda> buscarTodas();
    List<Venda> buscarPorFuncionario(Funcionario f);
    List<Venda> buscarEntrePeriodo(LocalDate inicio, LocalDate fim);
}
