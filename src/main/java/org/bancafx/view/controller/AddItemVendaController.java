package org.bancafx.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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

    private Stage stage;
    private Produto produto;
    private ItemVenda itemVenda;

    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setProduto(Produto produto){
        this.produto = produto;

        fieldNomeProduto.setText(produto.getNome());
        fieldPrecoProduto.setText(produto.getPrecoDeVenda().toString());
        fieldQtdEstProduto.setText(produto.getQuantidadeEmEstoque().toString());
    }

    public void novoItemVenda(){
        Integer qtd = new Integer(fieldQtdVendaProduto.getText());
        BigDecimal valor = new BigDecimal(fieldPrecoProduto.getText());

        itemVenda = new ItemVenda(produto, qtd, valor);

        VendaController.getVenda().adicionarItem(itemVenda);
        close();
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
