package org.bancafx.persistence;

import org.bancafx.domain.entities.GeneroProduto;
import org.bancafx.domain.entities.Produto;
import org.bancafx.utils.jpa.JPAUtil;

import java.math.BigDecimal;

/**
 * Created by Douglas on 01/05/2014.
 */
public class Main {

    public static void main(String[] args) {

        JPAUtil.getEntityManager().getTransaction().begin();


        Produto test = new Produto();
        test.setNome("Teste #");
        test.setDescricao("Testando com novo projeto");
        test.setPrecoDeCusto(new BigDecimal(77.90));
        test.setPrecoDeVenda(new BigDecimal(100.00));
        GeneroProduto gen = JPAUtil.getEntityManager().find(GeneroProduto.class, 1);
        test.setGenero(gen);
        test.setQuantidadeEmEstoque(10);

        JPAUtil.getEntityManager().persist(test);

        JPAUtil.getEntityManager().getTransaction().commit();

        JPAUtil.closeEntityManager();


    }
}
