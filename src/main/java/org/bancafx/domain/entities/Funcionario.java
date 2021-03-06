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

//TODO Remover Nome, e adicionar String razaoSocial

@Entity
@NamedQuery(name = Funcionario.TODOS_FUNCIONARIOS, query = "select f from Funcionario f")
@Data
@EqualsAndHashCode @ToString
public class Funcionario implements Serializable{

    public static final String TODOS_FUNCIONARIOS = "Funcionario.listarTodos";

    @Id
    @Column(name = "cpf")
    @org.hibernate.validator.constraints.br.CPF
    private String Cpf;

    @Column(name = "primeiro_nome")
    private String primeiroNome;

    @Column(name = "ultimo_nome")
    private String ultimoNome;

    @Column(unique = true)
    @NotNull
    private String rg;

    @Column(unique = true)
    @Email
    private String email;

    @Convert(converter = TelefonePersistenceConverter.class)
    private Telefone telefone;

    @Embedded
    @Basic(fetch = FetchType.LAZY)
    private Endereco endereco;

    @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "data_de_adimissao")
    private LocalDate adimissao;

    @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "data_de_demissao")
    private LocalDate demissao;

    @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "data_de_nascimento")
    private LocalDate nascimento;

    @Column(unique = true)
    @NotNull
    private String login;

    @Min(value = 6)
    @NotNull
    private String senha;
}