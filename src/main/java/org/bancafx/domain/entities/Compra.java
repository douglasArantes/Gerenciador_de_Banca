package org.bancafx.domain.entities;

import lombok.*;
import org.bancafx.utils.jpa.converters.LocalDatePersistenceConverter;
import org.bancafx.utils.jpa.converters.LocalDateTimePersistenceConverter;
import org.bancafx.utils.jpa.converters.LocalTimePersistenceConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
@NamedQuery(name = Compra.TODAS_COMPRAS, query = "select c from Compra c")
@Data
@EqualsAndHashCode @ToString
public class Compra implements Serializable{

    public static final String TODAS_COMPRAS = "Compra.listarTodas";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate diaDaEntrega;

    @Convert(converter = LocalTimePersistenceConverter.class)
    private LocalTime horaDaEntrega;

    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @Setter(AccessLevel.NONE)
    private LocalDateTime momentoDaCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<ItemCompra> itens;

    @Setter(AccessLevel.NONE)
    private BigDecimal totalDaCompra;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Boolean entregePeloFornecedor;

    public Compra(){
        momentoDaCompra.now();
    }

    public BigDecimal getTotalDaCompra(){
        BigDecimal valorTotal = new BigDecimal(0);
        for(ItemCompra ic : itens){
            valorTotal = valorTotal.add(ic.getTotal());
        }
        return valorTotal;
    }
}
