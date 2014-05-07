package org.bancafx.domain.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
@Data
@EqualsAndHashCode @ToString
public class GeneroProduto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @NotNull
    private String genero;

    @NotNull @Basic(fetch = FetchType.LAZY)
    private String descricao;
}
