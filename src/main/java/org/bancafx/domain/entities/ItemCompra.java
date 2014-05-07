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
public class ItemCompra implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_codigo", nullable = false)
    private Produto produto;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    public BigDecimal getTotal(){
            BigDecimal qtd = new BigDecimal(quantidade);
            return produto.getPrecoDeCusto().multiply(qtd);
    }
}
