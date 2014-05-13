package org.bancafx.persistence;

import org.bancafx.domain.entities.GeneroProduto;
import org.bancafx.domain.entities.Produto;
import org.bancafx.domain.entities.Venda;
import org.bancafx.persistence.repositories.GeneroProdutoRepository;
import org.bancafx.persistence.repositories.GeneroProdutoRepositoryImp;
import org.bancafx.persistence.repositories.ProdutoRepository;
import org.bancafx.persistence.repositories.ProdutoRepositoryImp;
import org.bancafx.utils.jpa.JPAUtil;

import java.math.BigDecimal;

/**
 * Created by Douglas on 01/05/2014.
 */
public class Main {

    public static void main(String[] args) {
/*
        GeneroProduto gp = new GeneroProduto();
        gp.setGenero("Revista de Tecnologia");
        gp.setDescricao("Artigos técnicos, Tutoriais, etc");
        GeneroProdutoRepository gpr = new GeneroProdutoRepositoryImp();
        gpr.salvar(gp);

        Produto p = new Produto();
        p.setCodigo("OJM-3-5/2014");
        p.setNome("Java Magazine, ed. Março/Abril de 2014");
        p.setDescricao("Java 8(Lambdas, Streams, Nashorn, JavaFX 8, etc");
        p.setQuantidadeEmEstoque(20);
        p.setPrecoDeCusto(new BigDecimal("10.90"));
        p.setPrecoDeVenda(new BigDecimal("13.90"));
        p.setGenero(gp);

        ProdutoRepository pr = new ProdutoRepositoryImp();
        pr.salvar(p);
*/



    }
}
