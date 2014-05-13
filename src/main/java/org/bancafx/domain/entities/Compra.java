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
    @Column(name = "dia_da_entrega")
    private LocalDate diaDaEntrega;

    @Convert(converter = LocalTimePersistenceConverter.class)
    @Column(name = "hora_da_entrega")
    private LocalTime horaDaEntrega;

    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @Setter(AccessLevel.NONE)
    @Column(name="momento_da_compra")
    private LocalDateTime momentoDaCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<ItemCompra> itens;

    @Setter(AccessLevel.NONE)
    @Column(name = "total_da_compra")
    private BigDecimal totalDaCompra;

    @ManyToOne
    private Fornecedor fornecedor;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @Column(name = "entrege_pelo_fornecedor")
    private Boolean entregePeloFornecedor;

    public Compra(){
        momentoDaCompra.now();
        entregePeloFornecedor = false;
    }

    public BigDecimal getTotalDaCompra(){
        BigDecimal valorTotal = new BigDecimal(0);
        for(ItemCompra ic : itens){
            valorTotal = valorTotal.add(ic.getTotal());
        }
        return valorTotal;
    }

    public void confirmarEntrega(){
        this.entregePeloFornecedor = true;
    }

    public void adicionarItem(ItemCompra item){
        this.itens.add(item);
    }

    public void adicionarItens(List<ItemCompra> itens){
        this.itens.addAll(itens);
    }

    public void removerItem(ItemVenda item){
        this.itens.remove(item);
    }

    public void removerTodosItens(){
        this.itens.clear();
    }
}
