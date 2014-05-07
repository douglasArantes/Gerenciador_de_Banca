package org.bancafx.persistence;

import org.bancafx.domain.entities.GeneroProduto;
import org.bancafx.domain.entities.Produto;
import org.bancafx.persistence.repositories.ProdutoRepositoryImp;
import org.bancafx.persistence.repositories.ProdutoRespository;
import org.bancafx.utils.jpa.JPAUtil;

import java.math.BigDecimal;

/**
 * Created by Douglas on 01/05/2014.
 */
public class Main {

    public static void main(String[] args) {

        ProdutoRespository prodRep = new ProdutoRepositoryImp();

        Produto p = prodRep.buscarPorCodigo((long)5);

        assert p.getCodigo() == 5 : "Falha ao buscar produto";
    }
}
