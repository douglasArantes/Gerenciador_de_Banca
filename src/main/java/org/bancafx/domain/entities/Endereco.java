package org.bancafx.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode @ToString
public class Endereco implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String logradouro;

    private Integer numero;

    private String bairro;

    private String cep;

    private String cidade;

    private String uf;

}
