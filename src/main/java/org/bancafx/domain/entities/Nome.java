package org.bancafx.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Douglas on 09/04/2014.
 */

//TODO Eliminar

@Embeddable
@EqualsAndHashCode @ToString
public class Nome implements Serializable{

    @Column(name = "primeiro_nome")
    private String primeiroNome;

    @Column(name = "ultimo_nome")
    private String ultimoNome;

    public Nome(){}

    public Nome(String primeiroNome, String ultimoNome){
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
    }


}
