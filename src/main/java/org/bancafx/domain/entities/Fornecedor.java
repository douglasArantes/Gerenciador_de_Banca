package org.bancafx.domain.entities;

import lombok.*;
import org.bancafx.utils.jpa.converters.TelefonePersistenceConverter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Douglas on 09/04/2014.
 */
@Entity
@NamedQuery(name = Fornecedor.TODOS_FORNECEDORES, query = "select f from Fornecedor f")
@Data @AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode @ToString
public class Fornecedor implements Serializable {

    public static final String TODOS_FORNECEDORES = "Fornecedor.listarTodos";

    @Id
    @Column(name = "cnpj", unique = true)
    private String Cnpj;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(unique = true)
    @Email
    private String email;

    @Convert(converter = TelefonePersistenceConverter.class)
    @Column(name = "tel_fixo")
    private Telefone fixo;

    @Convert(converter = TelefonePersistenceConverter.class)
    @Column(name = "tel_celular")
    private Telefone celular;

    @Embedded
    @Basic(fetch = FetchType.LAZY)
    private Endereco endereco;
}
