package org.bancafx.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Douglas on 09/04/2014.
 */

//TODO Modificar para @Embeddable

@Entity
@Data
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

    public Endereco(String log, Integer num, String bairro, String cep, String cid, String uf){
        this.logradouro = log;
        this.numero = num;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cid;
        this.uf = uf;
    }

}
