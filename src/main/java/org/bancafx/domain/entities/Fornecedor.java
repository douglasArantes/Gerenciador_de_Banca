package org.bancafx.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Douglas on 09/04/2014.
 */
@Entity
@Data
@EqualsAndHashCode @ToString
public class Fornecedor implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Embedded
    private Nome nome;

    @Column(unique = true)
    private String email;

    @OneToOne
    private Telefone fixo;

   @OneToOne
   private Telefone celular;

    @OneToOne
    private Endereco endereco;

    @Embedded @Column(unique = true)
    private CNPJ cnpj;



}
