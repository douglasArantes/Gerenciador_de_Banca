package org.bancafx.domain.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
@Table(name = "produto")
@NamedQuery(name = Produto.TODOS_PRODUTOS, query = "select p from Produto p")
@Data
@EqualsAndHashCode @ToString
public class Produto implements Serializable {

    public static final String TODOS_PRODUTOS = "Produto.listarTodos";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long codigo;

    @NotNull
    private String nome;

    @Basic(fetch = FetchType.LAZY)
    private String descricao;

    @NotNull
    private BigDecimal precoDeCusto;

    @NotNull
    private BigDecimal precoDeVenda;

    private Integer quantidadeEmEstoque;

    @OneToOne
    private GeneroProduto genero;
}