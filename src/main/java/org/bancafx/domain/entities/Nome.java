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
public class Nome implements Serializable{

    private String primeiroNome;

    private String ultimoNome;

    public Nome(){}

    public Nome(String primeiroNome, String ultimoNome){
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
    }


}
