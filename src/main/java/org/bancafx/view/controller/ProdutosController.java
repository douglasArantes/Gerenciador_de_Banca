package org.bancafx.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bancafx.domain.entities.Produto;
import org.bancafx.persistence.repositories.ProdutoRepository;
import org.bancafx.persistence.repositories.ProdutoRepositoryImp;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Douglas on 13/05/2014.
 */
public class ProdutosController implements Initializable, IProdutosController{

    @FXML private TableView<Produto> tableProdutos;
    @FXML private TableColumn<Produto, String> columnCodigo;
    @FXML private TableColumn<Produto, String> columnNome;
    @FXML private TableColumn<Produto, BigDecimal> columnPCusto;
    @FXML private TableColumn<Produto, BigDecimal> columnPVenda;
    @FXML private TableColumn<Produto, Integer> columnQuantidade;

    @FXML private TextField fieldCodigo;
    @FXML private TextField fieldNome;
    @FXML private TextField fieldPCusto;
    @FXML private TextField fieldPVenda;
    @FXML private TextField fieldQuantidade;
    @FXML private TextField fieldGenero;
    @FXML private TextArea tAreaObs;

    @FXML private TextField fieldPesquisar;

    private ObservableList<Produto> produtos;

    public ProdutosController(){
        produtos = FXCollections.observableArrayList();
    }

    @Override
    public void novoProduto(){
        FXMLLoader loader = getLoader("cadastro_produto.fxml");
        AnchorPane pane = loadAnchorPane(loader);

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Novo Produto");
        stage.initModality(Modality.APPLICATION_MODAL);

        CadastroProdutoController controller = loader.getController();
        controller.setStage(stage);
        stage.showAndWait();
        atualizaTabela();
    }

    @Override
    public void editarProduto(){
        FXMLLoader loader = getLoader("editar_produto.fxml");
        AnchorPane pane = loadAnchorPane(loader);

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edição de Produto");
        stage.initModality(Modality.WINDOW_MODAL);
        Produto produto = tableProdutos.getSelectionModel().getSelectedItem();

        EditarProdutoControlller controlller = loader.getController();
        controlller.setStage(stage);

        controlller.setProduto(produto);
        stage.showAndWait();
        atualizaTabela();
    }

    @Override
    public void excluirProduto(){
        ProdutoRepository pr = new ProdutoRepositoryImp();
        ObservableList<Produto> produtosSelecionados=  tableProdutos.getSelectionModel().getSelectedItems();
        produtosSelecionados.forEach( p -> pr.excluir(p));
        mostraDetalhesProduto(null);
        atualizaTabela();
    }

    private void mostraDetalhesProduto(Produto produto){
        if(produto != null){
            fieldCodigo.setText(produto.getCodigo());
            fieldNome.setText(produto.getNome());
            fieldPCusto.setText(produto.getPrecoDeCusto().toString());
            fieldPVenda.setText(produto.getPrecoDeVenda().toString());
            fieldQuantidade.setText(produto.getQuantidadeEmEstoque().toString());
            fieldGenero.setText(produto.getGenero().getGenero());
            tAreaObs.setText(produto.getDescricao());
        } else{
            limpaCampos(fieldCodigo, fieldNome, fieldPCusto, fieldPVenda, fieldQuantidade, fieldGenero, tAreaObs);
        }

    }

    @Override
    public void buscarProdutos() {
        ProdutoRepository pr = new ProdutoRepositoryImp();
        String str = fieldPesquisar.getText();
        if(str != null && !str.equals("")) {
            List<Produto> filtrados = pr.buscarPorNome(str);
            System.out.println(filtrados);
            produtos.clear();
            produtos.addAll(filtrados);
            tableProdutos.getItems().setAll(produtos);
        }else {
            atualizaTabela();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preparaTabela();
        atualizaTabela();

        tableProdutos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableProdutos.getSelectionModel().selectedItemProperty()
                .addListener((observableValue, produto, selectedProd) -> {
                    mostraDetalhesProduto(selectedProd);
                });
        tableProdutos.getSelectionModel().selectFirst();

        Produto p = tableProdutos.getSelectionModel().getSelectedItem();
        mostraDetalhesProduto(p);
    }

    private void preparaTabela() {
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnPCusto.setCellValueFactory(new PropertyValueFactory<>("precoDeCusto"));
        columnPVenda.setCellValueFactory(new PropertyValueFactory<>("precoDeVenda"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeEmEstoque"));
    }

    public void atualizaTabela(){
        produtos.clear();
        produtos.addAll(getProdutos());
        tableProdutos.getItems().setAll(produtos);
    }

    private void limpaCampos(TextInputControl ... inputs){
        for (TextInputControl tic : inputs){
            tic.setText("");
        }
    }

    private ObservableList getProdutos(){
        ProdutoRepository pr = new ProdutoRepositoryImp();
        return FXCollections.observableArrayList(pr.buscarTodos());
    }
    private FXMLLoader getLoader(String file) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + file));
        return loader;
    }
    private AnchorPane loadAnchorPane(FXMLLoader loader) {
        try {
            return (AnchorPane) loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
