package org.bancafx.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bancafx.domain.entities.GeneroProduto;
import org.bancafx.domain.entities.Produto;

import java.io.IOException;
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

    @Override
    public void novoProduto(){
        Parent painel = loadFxml("cadastro_produto.fxml");
        AnchorPane anchorNovoProduto = new AnchorPane();
        anchorNovoProduto.getChildren().add(painel);
        Stage stage = new Stage();
        stage.setTitle("Novo Produto");
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(anchorNovoProduto);
        stage.setScene(scene);
        stage.show();
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
    public void editarProduto(){
        Parent painel = loadFxml("editar_produto.fxml");
        AnchorPane anchorNovoProduto = new AnchorPane();
        anchorNovoProduto.getChildren().add(painel);
        Stage stage = new Stage();
        stage.setTitle("Editar Produto");
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(anchorNovoProduto);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public boolean excluirProduto(){
        System.out.println("Cliquei no Botão Excluir!");
        return false;
    }
    @Override
    public List<Produto> buscarProdutos() {
        System.out.println("Pressionei: " + fieldPesquisar.getText());
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
