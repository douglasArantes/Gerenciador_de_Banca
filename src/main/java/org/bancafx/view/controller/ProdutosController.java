package org.bancafx.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.bancafx.domain.entities.GeneroProduto;
import org.bancafx.domain.entities.Produto;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Douglas on 13/05/2014.
 */
public class ProdutosController implements Initializable, IProdutosController {

    @FXML
    private TableView<Produto> tableProdutos;
    @FXML
    private TableColumn<Produto, String> columnNome;
    @FXML
    private TableColumn<Produto, GeneroProduto> columnGenero;
    @FXML
    private TableColumn<Produto, BigDecimal> columnPCusto;
    @FXML
    private TableColumn<Produto, BigDecimal> columnPVenda;
    @FXML
    private TableColumn<Produto, Integer> columnQuantidade;

    @FXML
    private TextField fieldPesquisar;

    @Override @FXML
    public Produto novoProduto(){
        System.out.println("Cliquei no Botão Novo!");
        return null;
    }
    @Override @FXML
    public Produto editarProduto(){
        System.out.println("Cliquei no Botão Editar!");
        return null;
    }
    @Override @FXML
    public boolean excluirProduto(){
        System.out.println("Cliquei no Botão Excluir!");
        return false;
    }

    @Override @FXML
    public List<Produto> buscarProdutos() {
        System.out.println("Pressionei: " + fieldPesquisar.getText());
        return null;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
