package org.bancafx.view.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bancafx.domain.entities.ItemVenda;
import org.bancafx.domain.entities.Produto;
import org.bancafx.domain.entities.Venda;
import org.bancafx.persistence.repositories.ProdutoRepository;
import org.bancafx.persistence.repositories.ProdutoRepositoryImp;
import org.bancafx.persistence.repositories.VendaRepository;
import org.bancafx.persistence.repositories.VendaRepositoryImp;
import org.bancafx.utils.fx.FXControlUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Douglas on 13/05/2014.
 */
public class VendaController implements Initializable, IVendaController {

    @FXML private TextField fieldCodigo;
    @FXML private TextField fieldTotalVenda;
    @FXML private TextField fieldValorRecebido;
    @FXML private TextField fieldTroco;


    @FXML private TableView<ItemVenda> tableVendas;
    @FXML private TableColumn<ItemVenda,String> columnCodigo;
    @FXML private TableColumn<ItemVenda,String> columnNome;
    @FXML private TableColumn<ItemVenda,BigDecimal> columnPreco;
    @FXML private TableColumn<ItemVenda, Integer> columnQtdVenda;
    @FXML private TableColumn<ItemVenda, BigDecimal> columnTotal;

    private static Venda venda;
    private static ObservableList<ItemVenda> itensDaVenda;
    private VendaRepository vr;
    private ProdutoRepository pr;

    public VendaController(){
        venda = new Venda();
        itensDaVenda = FXCollections.observableArrayList(venda.getItens());
    }

    public void novoItemForm() {
        pr = new ProdutoRepositoryImp();
        String codigo = fieldCodigo.getText();
        Produto produto = pr.buscarPorCodigo(codigo);

        if (produto != null) {
            FXMLLoader loader = getLoader("add_item_venda.fxml");
            AnchorPane pane = loadAnchorPane(loader);

            Stage stage = new Stage();
            stage.setTitle("Adicionar item na venda");
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            AddItemVendaController controller = loader.getController();
            controller.setStage(stage);
            controller.setProduto(produto);
            stage.showAndWait();
            atualizaTabela();
            mostraTotal();
        }
        fieldCodigo.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preparaTabela();
        tableVendas.getItems().setAll(itensDaVenda);
        tableVendas.setPlaceholder(new Label("Nenhum produto adicionado!"));

    }

    private void preparaTabela() {
        columnCodigo.setCellValueFactory(itemCell -> {
            String cod = itemCell.getValue().getProduto().getCodigo();
            SimpleObjectProperty<String> sop = new SimpleObjectProperty<>();
            sop.setValue(cod);
            return sop;
        });
        columnNome.setCellValueFactory(itemCell -> {
            String nome = itemCell.getValue().getProduto().getNome();
            SimpleObjectProperty<String> sop = new SimpleObjectProperty<>();
            sop.setValue(nome);
            return sop;
        });
        columnPreco.setCellValueFactory(itemCell -> {
            BigDecimal total = itemCell.getValue().getProduto().getPrecoDeVenda();
            SimpleObjectProperty<BigDecimal> sop = new SimpleObjectProperty<>();
            sop.setValue(total);
            return sop;
        });
        columnQtdVenda.setCellValueFactory(itemCell -> {
            Integer qtd = itemCell.getValue().getQuantidade();
            SimpleObjectProperty<Integer> sop = new SimpleObjectProperty<>();
            sop.setValue(qtd);
            return sop;
        });
        columnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    @Override
    public void finalizar(){
        salvarItens();
        salvarVenda(venda);

        limparCampos();

        venda = null;
        itensDaVenda = FXCollections.observableArrayList();
        tableVendas.getItems().setAll(itensDaVenda);

    }

    private void limparCampos() {
        FXControlUtil.limparCampos(fieldTotalVenda, fieldValorRecebido, fieldTroco);
    }

    private void salvarItens() {
        List<ItemVenda> itens =  venda.getItens();

        for (ItemVenda item : itens){
            pr = new ProdutoRepositoryImp();
            Produto prod = item.getProduto();
            try {
                prod.baixarEstoque(item.getQuantidade());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            pr.editar(prod);
        }
    }

    private void salvarVenda(Venda v){
        vr = new VendaRepositoryImp();
        vr.salvar(v);

    }

    @Override
    public void removerItem() {
        ItemVenda item = tableVendas.getSelectionModel().getSelectedItem();
        venda.removerItem(item);
        atualizaTabela();
        mostraTotal();
    }

    @Override
    public void removerTodosItens() {
        venda.removerTodosItens();
        atualizaTabela();
        mostraTotal();
    }

    @Override
    public void mostraTotal() {
        fieldTotalVenda.setText(venda.getTotalDaVenda().toString());
    }

    @Override
    public void calcularTroco() {
        String str = fieldValorRecebido.getText();
        if(!str.isEmpty()) {
            final BigDecimal total = new BigDecimal(fieldTotalVenda.getText());
            final BigDecimal recebido = new BigDecimal(str);

            final BigDecimal troco = recebido.subtract(total);

            if(troco.compareTo(BigDecimal.ZERO) >= 0) {
                fieldTroco.setText(troco.toString());
            }
        }else {
            fieldTroco.setText("");
        }
    }

    private void atualizaTabela(){
        itensDaVenda.clear();
        itensDaVenda.addAll(venda.getItens());
        tableVendas.getItems().setAll(itensDaVenda);

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

    public static Venda getVenda(){
        if (venda == null){
            return venda = new Venda();
        }
        return venda;
    }

}