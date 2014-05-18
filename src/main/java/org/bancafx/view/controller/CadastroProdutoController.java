package org.bancafx.view.controller;

import javafx.stage.Stage;

/**
 * Created by Douglas on 15/05/2014.
 */
public class CadastroProdutoController {

    private Stage dialogStage;

    public void setStage(Stage stage){
        this.dialogStage = stage;
    }

    public void cadastrarProduto() {

    }


    public void cancelar(){
        dialogStage.close();
    }
}
