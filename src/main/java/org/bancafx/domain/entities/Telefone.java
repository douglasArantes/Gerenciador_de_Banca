package org.bancafx.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Douglas on 09/04/2014.
 */

@Entity
@EqualsAndHashCode @ToString
public class Telefone implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ddd;
    private String numero;

    public Telefone(){}

    public Telefone(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }
}
