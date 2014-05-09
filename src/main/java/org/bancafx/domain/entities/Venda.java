package org.bancafx.domain.entities;

import lombok.*;
import org.bancafx.utils.jpa.converters.LocalDateTimePersistenceConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
@NamedQuery(name = Venda.TODAS_VENDAS, query = "select v from Venda v")
@Data
@EqualsAndHashCode @ToString
public class Venda implements Serializable{

    public static final String TODAS_VENDAS = "Venda.listarTodas";

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
        itens = new ArrayList<>();
        momentoDaVenda.now();
    }

    public BigDecimal getTotalDaVenda(){
        BigDecimal valorTotal = new BigDecimal(0);
        for (ItemVenda iv : itens){
            valorTotal = valorTotal.add(iv.getTotal());
        }
        return valorTotal;
    }

    public void adicionarItem(ItemVenda item){
        itens.add(item);
    }

    public void adicionarItens(List<ItemVenda> itens){
        itens.addAll(itens);
    }

    public void removerItem(ItemVenda item){
        this.itens.remove(item);
    }

    public void removerTodosItens(){
        itens.clear();
    }
}