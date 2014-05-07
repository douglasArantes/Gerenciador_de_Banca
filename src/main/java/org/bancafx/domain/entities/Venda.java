package org.bancafx.domain.entities;

import lombok.*;
import org.bancafx.utils.jpa.converters.LocalDateTimePersistenceConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
@Data
@EqualsAndHashCode @ToString
public class Venda implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @Setter(AccessLevel.NONE)
    private LocalDateTime momentoDaVenda;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<ItemVenda> itens;

    @Setter(AccessLevel.NONE)
    private BigDecimal totalDaVenda;

    @ManyToOne
    private Funcionario funcionario;

    public Venda(){
        momentoDaVenda.now();
    }

    public BigDecimal getTotalDaVenda(){
        BigDecimal valorTotal = new BigDecimal(0);
        for (ItemVenda iv : itens){
            valorTotal = valorTotal.add(iv.getTotal());
        }
        return valorTotal;
    }
}
