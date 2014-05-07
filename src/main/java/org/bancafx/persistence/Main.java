package org.bancafx.persistence;

import org.bancafx.domain.entities.Produto;
import org.bancafx.persistence.repositories.ProdutoRepository;
import org.bancafx.persistence.repositories.ProdutoRepositoryImp;

/**
 * Created by Douglas on 01/05/2014.
 */
public class Main {

    public static void main(String[] args) {

        ProdutoRepository prodRep = new ProdutoRepositoryImp();

        Produto p = prodRep.buscarPorCodigo((long)5);

        assert p.getCodigo() == 5 : "Falha ao buscar produto";
    }
}
