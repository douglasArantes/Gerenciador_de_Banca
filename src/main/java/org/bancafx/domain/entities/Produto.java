package org.bancafx.domain.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
@NamedQuery(name = Produto.TODOS_PRODUTOS, query = "select p from Produto p")
@Data
@EqualsAndHashCode @ToString
public class Produto implements Serializable {

    public static final String TODOS_PRODUTOS = "Produto.listarTodos";

    @Id @NotNull
    private String codigo;

    @NotNull
    private String nome;

    @Basic(fetch = FetchType.LAZY)
    private String descricao;

    @NotNull
    private BigDecimal precoDeCusto;

    @NotNull
    private BigDecimal precoDeVenda;

    @NotNull @Min(value = 0)
    private Integer quantidadeEmEstoque;

    @OneToOne
    private GeneroProduto genero;

    public void baixarEstoque(Integer quantidade) throws Exception {
        if(quantidadeEmEstoque <=quantidade){
            quantidadeEmEstoque -= quantidade;
        }
        else {
            throw new Exception("Quantidade pedida, não está disponível em estoque");
        }
    }
}