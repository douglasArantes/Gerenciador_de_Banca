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
public class RG implements Serializable{

    private String rg;

    public RG(){}

    public RG(String rg) {
        this.rg = rg;
    }
}
