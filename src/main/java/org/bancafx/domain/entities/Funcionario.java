package org.bancafx.domain.entities;

import lombok.*;
import org.bancafx.utils.jpa.converters.LocalDatePersistenceConverter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
@Data
@EqualsAndHashCode @ToString
public class Funcionario implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Embedded @NotNull
    private Nome nome;

    @Column(unique = true)
    private String email;

    @OneToOne
    private Telefone telefone;

    @OneToOne
    private Endereco endereco;

    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate adimissao;

    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate demissao;

    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate nascimento;

    @Column(unique = true)
    @NotNull
    private String login;

    @Min(value = 6)
    @NotNull
    private String senha;

    @Column(unique = true)
    @NotNull
    private CPF cpf;

    @Column(unique = true)
    @NotNull
    private RG rg;
}
