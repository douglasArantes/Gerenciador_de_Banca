package org.bancafx.view.controller;

import javafx.stage.Stage;

/**
 * Created by Dougla$ on 16/05/2014.
 */
public class EditarProdutoControlller {

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
