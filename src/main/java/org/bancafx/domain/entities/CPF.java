package org.bancafx.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Douglas on 09/04/2014.
 */

@Embeddable
@EqualsAndHashCode @ToString
public class CPF implements Serializable {

    @org.hibernate.validator.constraints.br.CPF
    private String cpf;

    public CPF(){}

    public CPF(String cpf) {
        this.cpf = cpf;
    }
}
