package org.bancafx.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bancafx.domain.entities.ItemVenda;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Douglas on 13/05/2014.
 */
public class VendaController implements Initializable, IVendaController {

     @FXML
     private TextField fieldCodigo;


    // criar TextField Valor Recebido

    @Override
    public void finalizar() {

    }
    @Override
    public void removerItem(List<ItemVenda> itens) { // sem parametros

    }
    @Override
    public void removerTodosItens() {

    }
    @Override
    public void buscarItem() {
        String codigo = fieldCodigo.getText();
        boolean achou = true; // Produto p = prodRepo.buscarPorCodigo(codigo)

        if (achou){ // if (produto != null)
            Parent painel = loadFxml("add_item_venda.fxml");
            AnchorPane anchorNovoProduto = new AnchorPane();
            anchorNovoProduto.getChildren().add(painel);
            Stage stage = new Stage();
            stage.setTitle("Adicionar item na venda");
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(anchorNovoProduto);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void adiconarItem(ItemVenda item) { // (produto, quantidade)

    }

    private Parent loadFxml(String file){
        try {
            return (Parent) FXMLLoader.load(getClass().getResource("/fxml/" + file));
        } catch (IOException e) {
            System.err.println("");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
