package org.bancafx.utils.fx;

import javafx.util.StringConverter;
import org.bancafx.domain.entities.GeneroProduto;

/**
 * Created by Dougla$ on 19/05/2014.
 */
public class FXConverters {

    private StringConverter<GeneroProduto> generoCoverter = new StringConverter<GeneroProduto>() {
        @Override
        public String toString(GeneroProduto generoProduto) {
            return generoProduto.getGenero();
        }
        @Override
        public GeneroProduto fromString(String s) {
            return null;
        }
    } ;

    public StringConverter<GeneroProduto> getGeneroCoverter() {
        return generoCoverter;
    }
}
