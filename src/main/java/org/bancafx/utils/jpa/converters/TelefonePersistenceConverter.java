package org.bancafx.utils.jpa.converters;

import org.bancafx.domain.entities.Telefone;

import javax.persistence.AttributeConverter;

/**
 * Created by Dougla$ on 13/05/2014.
 */
public class TelefonePersistenceConverter implements AttributeConverter<Telefone, String> {
    @Override
    public String convertToDatabaseColumn(Telefone telefone) {
        return telefone.toString();
    }

    @Override
    public Telefone convertToEntityAttribute(String telefone) {
        String ddd = telefone.substring(0, 2);
        String numero = telefone.substring(2, 11);

        return new Telefone(ddd, numero);
    }
}
