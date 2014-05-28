package org.bancafx.utils.fx;

import javafx.scene.control.TextInputControl;

/**
 * Created by Douglas on 27/05/2014.
 */
public class FXControlUtil {

    public static void limparCampos(TextInputControl... inputs){
        for (TextInputControl tic : inputs){
            tic.setText("");
        }
    }
}
