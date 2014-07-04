package org.bancafx.view.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Douglas on 13/05/2014.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        root.setTop(getTop());
        root.setCenter(getCenter());

        Scene scene = new Scene(root, 1200, 900);
        stage.setScene(scene);
        stage.show();
    }

    private Node getCenter() throws IOException {
        return getTabPane();
    }

    private Node getTop() {
        Label titulo = new Label("Sistema Gerenciador de Banca");
        titulo.setMinSize(30, 30);
        return titulo;
    }

    private TabPane getTabPane() {
        TabPane tabs = new TabPane();
        Tab venda = getTabVenda();
        Tab pedido = getTabPedido();
        Tab estoque = getTabEstoque();
        Tab relatorio = getTabRelatorio();

        tabs.getTabs().addAll(venda, estoque, relatorio, pedido);
        desabilitarFecharTabs(tabs);

        return tabs;
    }

    private Tab getTabPedido() {
        return new Tab("PEDIDOS");
    }

    private Tab getTabRelatorio() {
        Tab relatorio = new Tab("RELATÓRIOS");
        AnchorPane anchorRelatorio = (AnchorPane) loadFxml("relatorios.fxml");
        relatorio.setContent(anchorRelatorio);
        return relatorio;
    }


    private Tab getTabEstoque() {
        Tab estoque = new Tab("ESTOQUE");
        Parent painelEstoque = loadFxml("produtos.fxml");
        AnchorPane anchorEstoque = new AnchorPane();
        anchorEstoque.getChildren().add(painelEstoque);
        estoque.setContent(anchorEstoque);
        return estoque;
    }

    private Tab getTabVenda() {
        Tab venda = new Tab("VENDAS");
        AnchorPane anchorVenda = (AnchorPane) loadFxml("venda.fxml");
        venda.setContent(anchorVenda);
        return venda;
    }

    private void desabilitarFecharTabs(TabPane tabPane) {
        tabPane.getTabs().forEach((t) -> t.setClosable(false));
    }

    private Parent loadFxml(String file) {
        try {
            return (Parent) FXMLLoader.load(getClass().getResource("/fxml/" + file));
        } catch (IOException e) {
            System.err.println("");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}