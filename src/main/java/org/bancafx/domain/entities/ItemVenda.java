package org.bancafx.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
@Data
@EqualsAndHashCode @ToString
public class ItemVenda implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_codigo", nullable = false)
    private Produto produto;

    private Integer quantidade;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    public ItemVenda(){
        valor = new BigDecimal("0.0");
    }

    public BigDecimal getTotal(){
        BigDecimal qtd = new BigDecimal(quantidade);
        return  produto.getPrecoDeVenda().multiply(qtd);
    }

    public ItemVenda(Produto p, Integer qtd, BigDecimal val, Venda venda){
        this.produto = p;
        this.quantidade = qtd;
        this.valor = val;
        this.venda = venda;
    }

    public BigDecimal getLucroItemVenda(){
        return produto.getLucro().multiply(new BigDecimal(quantidade));
    }
}
