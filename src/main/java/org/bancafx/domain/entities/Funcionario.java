package org.bancafx.domain.entities;

import lombok.*;
import org.bancafx.utils.jpa.converters.LocalDatePersistenceConverter;
import org.bancafx.utils.jpa.converters.TelefonePersistenceConverter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
@NamedQuery(name = Funcionario.TODOS_FUNCIONARIOS, query = "select f from Funcionario f")
@Data
@EqualsAndHashCode @ToString
public class Funcionario implements Serializable{

    public static final String TODOS_FUNCIONARIOS = "Funcionario.listarTodos";

    @Id  @NotNull
    @Column(unique = true)
    @org.hibernate.validator.constraints.br.CPF
    private String Cpf;

    @Embedded @NotNull
    private Nome nome;

    @Column(unique = true)
    @NotNull
    private String rg;

    @Column(unique = true)
    @Email
    private String email;

    @Convert(converter = TelefonePersistenceConverter.class)
    private Telefone telefone;

    @OneToOne
    @Basic(fetch = FetchType.LAZY)
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
}