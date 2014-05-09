package org.bancafx.domain.entities;

import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
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
        this.valor = produto.getPrecoDeVenda();
    }

    public BigDecimal getTotal(){
        BigDecimal qtd = new BigDecimal(quantidade);
        return  produto.getPrecoDeVenda().multiply(qtd);
    }
}
