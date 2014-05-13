package org.bancafx.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Douglas on 09/04/2014.
 */

@EqualsAndHashCode @ToString
public class Telefone implements Serializable {

    private String ddd;
    private String numero;

    public Telefone(){}

    public Telefone(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }
}
