package org.bancafx.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.bancafx.domain.entities.GeneroProduto;
import org.bancafx.domain.entities.Produto;
import org.bancafx.persistence.repositories.GeneroProdutoRepository;
import org.bancafx.persistence.repositories.GeneroProdutoRepositoryImp;
import org.bancafx.persistence.repositories.ProdutoRepository;
import org.bancafx.persistence.repositories.ProdutoRepositoryImp;
import org.bancafx.utils.fx.FXConverters;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Douglas on 15/05/2014.
 */
public class CadastroProdutoController implements Initializable {

    private Stage dialogStage;

    @FXML private Label labelCodigo;
    @FXML private TextField fieldCodigo;
    @FXML private Label labelNome;
    @FXML private TextField fieldNome;
    @FXML private Label labelPrecoDeCusto;
    @FXML private TextField fieldPrecoDeCusto;
    @FXML private Label labelPrecoDeVenda;
    @FXML private TextField fieldPrecoDeVenda;
    @FXML private Label labelQuantidade;
    @FXML private TextField fieldQuantidade;
    @FXML private Label labelGenero;
    @FXML private ComboBox<GeneroProduto> comboGenero;
    @FXML private Label labelObs;
    @FXML private TextArea areaObs;


    private Produto getValores(){
        String codigo = fieldCodigo.getText();
        String nome = fieldNome.getText();
        BigDecimal precoDeCusto = new BigDecimal(fieldPrecoDeCusto.getText());
        BigDecimal precoDeVenda = new BigDecimal(fieldPrecoDeVenda.getText());
        Integer quantidade = new Integer(fieldQuantidade.getText());
        GeneroProduto genero = comboGenero.getSelectionModel().getSelectedItem();
        String obs = areaObs.getText();

        Produto produto = new Produto(codigo,nome, obs, precoDeCusto, precoDeVenda, quantidade, genero);

        return  produto;
    }


    public void cadastrarProduto() {
        System.err.println("cadastro");
        Produto produto = getValores();
        System.out.println(produto);
        ProdutoRepository pr = new ProdutoRepositoryImp();
        pr.salvar(produto);
        close();
    }

    public void setStage(Stage stage){
        this.dialogStage = stage;
    }

    public void cancelar(){
        close();
    }

    private void close(){
        dialogStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FXConverters fxc = new FXConverters();

        GeneroProdutoRepository gpr = new GeneroProdutoRepositoryImp();
        List<GeneroProduto> generos =  gpr.buscarTodos();
        comboGenero.getItems().addAll(generos);

        comboGenero.converterProperty().setValue(fxc.getGeneroCoverter());
    }

}
