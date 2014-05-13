package org.bancafx.view.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Douglas on 13/05/2014.
 */
public class Main  extends Application{

    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1200, 600);

        Label titulo = new Label("Sistema Gerenciador de Banca");
        titulo.setMinSize(30, 30);
        root.setTop(titulo);
        root.setLeft(null);


        TabPane tabs = new TabPane();
        root.setCenter(tabs);

        Tab venda = new Tab("VENDAS");

        Parent painelVenda = FXMLLoader.load(getClass().getResource("/org/bancafx/view/fxml/cadastro_produto.fxml"));
        AnchorPane anchorVenda = new AnchorPane();
        anchorVenda.getChildren().add(painelVenda);
        venda.setContent(anchorVenda);


        Tab pedido = new Tab("PEDIDOS");

        Tab estoque = new Tab("ESTOQUE");

        Parent painelEstoque = FXMLLoader.load(getClass().getResource("/org/bancafx/view/produtos.fxml"));
        AnchorPane anchorEstoque = new AnchorPane();
        anchorEstoque.getChildren().add(painelEstoque);
        estoque.setContent(anchorEstoque);


        Tab relatorio = new Tab("RELATÓRIOS");




        tabs.getTabs().addAll(venda, pedido, estoque, relatorio);

        desabilitarFecharTabs(venda, pedido, estoque, relatorio);

        stage.setScene(scene);
        stage.show();

    }

    private static void desabilitarFecharTabs(Tab ... tabs){
        for(Tab t : tabs){
            t.setClosable(false);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
