package org.bancafx.view.controller;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bancafx.domain.entities.ItemVenda;
import org.bancafx.domain.entities.Produto;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Douglas on 25/05/2014.
 */
public class AddItemVendaController implements Initializable{

    @FXML private TextField fieldNomeProduto;
    @FXML private TextField fieldPrecoProduto;
    @FXML private TextField fieldQtdEstProduto;
    @FXML private TextField fieldQtdVendaProduto;
    @FXML private Label labelMsg;

    private Stage stage;
    private Produto produto;
    private ItemVenda itemVenda;

    public void setStage(Stage stage){
        this.stage = stage;
        this.stage.initStyle(StageStyle.UTILITY);
    }
    public void setProduto(Produto produto){
        this.produto = produto;

        fieldNomeProduto.setText(produto.getNome());
        fieldPrecoProduto.setText(produto.getPrecoDeVenda().toString());
        fieldQtdEstProduto.setText(produto.getQuantidadeEmEstoque().toString());
    }

    public void novoItemVenda(){
        Integer qtdVenda = new Integer(fieldQtdVendaProduto.getText());
        BigDecimal valor = new BigDecimal(fieldPrecoProduto.getText());

        if(qtdVenda <= produto.getQuantidadeEmEstoque()) {
            itemVenda = new ItemVenda(produto, qtdVenda, valor, VendaController.getVenda() );
            VendaController.getVenda().adicionarItem(itemVenda);
            close();
        }else {
            fieldQtdVendaProduto.setText("");
            labelMsg.setText("Quantidade não disponível no estoque");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void cancelar(){
        close();
    }

    private void close(){
        stage.close();
    }
}
