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
public class CNPJ implements Serializable{

    @org.hibernate.validator.constraints.br.CNPJ
    private String cnpj;

    public CNPJ(){}

    public CNPJ(String cnpj){
        this.cnpj = cnpj;
    }
}
