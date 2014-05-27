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
 * Created by Douglas on 16/05/2014.
 */
public class EditarProdutoControlller implements Initializable{

    @FXML private TextField fieldCodigo;
    @FXML private TextField fieldNome;
    @FXML private TextField fieldPrecoDeCusto;
    @FXML private TextField fieldPrecoDeVenda;
    @FXML private TextField fieldQuantidade;
    @FXML private ComboBox<GeneroProduto> comboGenero;
    @FXML private TextArea areaObs;

    private Produto produto;

    private Stage dialogStage;

    public void setStage(Stage stage){
        this.dialogStage = stage;
    }

    public void setProduto(Produto produto){
        this.produto = produto;

        fieldCodigo.setText(this.produto.getCodigo());
        fieldNome.setText(this.produto.getNome());
        fieldPrecoDeCusto.setText(this.produto.getPrecoDeCusto().toString());
        fieldPrecoDeVenda.setText(this.produto.getPrecoDeVenda().toString());
        fieldQuantidade.setText(this.produto.getQuantidadeEmEstoque().toString());
        comboGenero.setValue(produto.getGenero());
        areaObs.setText(this.produto.getDescricao());

    }

    public void atualizarProduto() {
        ProdutoRepository pr = new ProdutoRepositoryImp();
        pr.editar(getValores());
        close();
    }

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

    public void cancelar(){
        close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXConverters fxc = new FXConverters();

        GeneroProdutoRepository gpr = new GeneroProdutoRepositoryImp();
        List<GeneroProduto> generos =  gpr.buscarTodos();
        comboGenero.getItems().addAll(generos);

        comboGenero.converterProperty().setValue(fxc.getGeneroCoverter());
    }

    private void close(){
        dialogStage.close();
    }
}
