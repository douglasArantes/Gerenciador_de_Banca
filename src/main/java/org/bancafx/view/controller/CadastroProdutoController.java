package org.bancafx.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    @FXML private TextField fieldCodigo;
    @FXML private TextField fieldNome;
    @FXML private TextField fieldPrecoDeCusto;
    @FXML private TextField fieldPrecoDeVenda;
    @FXML private TextField fieldQuantidade;
    @FXML private ComboBox<GeneroProduto> comboGenero;
    @FXML private TextArea areaObs;
    @FXML private Label labelMsg;


    private Produto getValores(){
        String codigo = fieldCodigo.getText();
        String nome = fieldNome.getText();
        BigDecimal precoDeCusto = null;
        if(fieldPrecoDeCusto.getText() != null && !fieldPrecoDeCusto.getText().isEmpty()) {
            precoDeCusto = new BigDecimal(fieldPrecoDeCusto.getText());
        }
        BigDecimal precoDeVenda = null;
        if (fieldPrecoDeVenda.getText() != null && !fieldPrecoDeVenda.getText().isEmpty()) {
            precoDeVenda = new BigDecimal(fieldPrecoDeVenda.getText());
        }
        Integer quantidade = null;
        if (!fieldQuantidade.getText().isEmpty()) {
            quantidade = new Integer(fieldQuantidade.getText());
        }
        GeneroProduto genero = comboGenero.getSelectionModel().getSelectedItem();
        String obs = areaObs.getText();

        if(!camposPreenchidos(codigo, nome, precoDeCusto, precoDeVenda, quantidade, genero)){
            labelMsg.setText("Preencha todos os campos");
            return null;
        }
        if(!valoresPositivos(precoDeVenda, precoDeCusto, quantidade)){
            labelMsg.setText("Os valores numéricos devem ser positivos");
            return null;
        }

        Produto produto = new Produto(codigo, nome, obs, precoDeCusto, precoDeVenda, quantidade, genero);
        return produto;
    }


    public void cadastrarProduto() {
        Produto produto = getValores();
        if(produto != null) {
            ProdutoRepository pr = new ProdutoRepositoryImp();
            pr.salvar(produto);
            close();
        }
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

    private boolean camposPreenchidos(String cod, String nome, BigDecimal pCusto, BigDecimal pVenda, Integer qtd, GeneroProduto gen) {
        if(cod != null && !cod.isEmpty()
           && nome != null && !nome.isEmpty()
           && pCusto != null
           && pVenda != null
           && qtd != null
           && gen != null){

            return true;
        }
        return false;
    }

    private boolean valoresPositivos(BigDecimal precoDeVenda, BigDecimal precoDeCusto, Integer quantidade) {
        return precoDeVenda.compareTo(BigDecimal.ZERO) >= 0
                && precoDeCusto.compareTo(BigDecimal.ZERO) >=0
                && (quantidade >= 0);
    }

}

