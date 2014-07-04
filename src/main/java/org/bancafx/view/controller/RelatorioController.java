package org.bancafx.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import org.bancafx.domain.entities.Produto;
import org.bancafx.domain.entities.Venda;
import org.bancafx.persistence.repositories.ProdutoRepository;
import org.bancafx.persistence.repositories.ProdutoRepositoryImp;
import org.bancafx.persistence.repositories.VendaRepositoryImp;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Douglas on 03/07/2014.
 */
public class RelatorioController implements Initializable {

    @FXML private TextField fieldPesquisar;
    @FXML private ListView<Produto> produtos;
    @FXML private LineChart<String, Number> lineChartLucroVenda;

    private final CategoryAxis periodoAxisX;
    private final NumberAxis lucroAxisY;
    private XYChart.Series<String, Number> series;

    private ObservableList<Produto> produtosList;

    private List<Venda> vendas;
    private DadoLucro dados;


    public RelatorioController() {
        produtosList = FXCollections.observableArrayList();
        produtos = new ListView<>();
        periodoAxisX = new CategoryAxis();
        lucroAxisY = new NumberAxis();
        series = new XYChart.Series<>();
        lineChartLucroVenda = new LineChart<>(periodoAxisX, lucroAxisY);
        vendas = new ArrayList<>(new VendaRepositoryImp().buscarTodas());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProdutoRepository prodRepo = new ProdutoRepositoryImp();
        produtosList.addAll(prodRepo.buscarTodos());
        produtos.setItems(produtosList);

        preparaListView();
        preparaLineChart();
    }

    private void preparaListView() {
        produtos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void preparaLineChart(){
        gerarSeries(); //com dados estáticos
        lineChartLucroVenda.getData().add(series);
    }


    /*TESTE Gerando Gráfico com Dados Estáticos*/
    private void gerarSeries() {
        series.setName("lucros");
        series.getData().add(new XYChart.Data<>("30/06", 45d));
        series.getData().add(new XYChart.Data<>("01/07", 5d));
        series.getData().add(new XYChart.Data<>("02/07", 7d));
        series.getData().add(new XYChart.Data<>("03/07", 15d));
        series.getData().add(new XYChart.Data<>("04/07", 10d));
        series.getData().add(new XYChart.Data<>("05/07", 20d));
        series.getData().add(new XYChart.Data<>("06/07", 24d));
        series.getData().add(new XYChart.Data<>("07/07", 100d));
        series.getData().add(new XYChart.Data<>("08/07", 89d));
        series.getData().add(new XYChart.Data<>("09/07", 77d));
        series.getData().add(new XYChart.Data<>("10/07", 45d));
        series.getData().add(new XYChart.Data<>("11/07", 99d));
        series.getData().add(new XYChart.Data<>("12/07", 46d));
        series.getData().add(new XYChart.Data<>("13/07", 20d));
        series.getData().add(new XYChart.Data<>("14/07", 29d));
        series.getData().add(new XYChart.Data<>("15/07", 31d));
        series.getData().add(new XYChart.Data<>("16/07", 87d));
        series.getData().add(new XYChart.Data<>("17/07", 20d));
        series.getData().add(new XYChart.Data<>("18/07", 43d));
        series.getData().add(new XYChart.Data<>("19/07", 91d));
        series.getData().add(new XYChart.Data<>("20/07", 54d));
        series.getData().add(new XYChart.Data<>("21/07", 22d));
        series.getData().add(new XYChart.Data<>("22/07", 54d));
        series.getData().add(new XYChart.Data<>("23/07", 32d));
        series.getData().add(new XYChart.Data<>("24/07", 54d));
        series.getData().add(new XYChart.Data<>("25/07", 71d));
        series.getData().add(new XYChart.Data<>("26/07", 23d));
        series.getData().add(new XYChart.Data<>("27/07", 20d));
        series.getData().add(new XYChart.Data<>("28/07", 23d));
        series.getData().add(new XYChart.Data<>("29/07", 59d));
        series.getData().add(new XYChart.Data<>("30/07", 87d));


    }

    /*
    * ******************************************************************
    *   ATENÇÃO  -- Os métodos abaixo "gerarGraficoDeLucrosXXX",
    *   que devem estar presentes nos Documentos (Eu acho!)
    ***********************************************************************
    * */
    public void gerarGrafifoDeLucrosHoje(){
        //TODO grafíco para dia atual
    }
    public void gerarGrafifoDeLucros7Dias(){
        //TODO grafíco para os 7 útimos dias
    }
    public void gerarGrafifoDeLucros15Dias(){
        //TODO grafíco para os últimos 15 dias
    }
    public void gerarGrafifoDeLucrosMes(){
        //TODO grafíco para o último mes
    }

    public static boolean mesmoDia(LocalDateTime ldt1, LocalDateTime ldt2) {
        if (ldt1.getDayOfMonth() == ldt2.getDayOfMonth()
                && ldt1.getMonth().equals(ldt2.getMonth())
                && ldt1.getYear() == ldt2.getYear()) {
            return true;
        } else {
            return false;
        }
    }

    private List<DadoLucro> getDadosLucro(){
        List<DadoLucro> lucroPorDia  = new ArrayList<>();
        List<Venda> vendasDoDia = new ArrayList<>();
        LocalDateTime dataAtual = vendas.get(0).getMomentoDaVenda();

        for(Venda venda : vendas){
            if (!mesmoDia(venda.getMomentoDaVenda(), dataAtual)){
                DadoLucro dadosDoDia = constroiDadoLucroParaData(dataAtual, vendasDoDia);
                lucroPorDia.add(dadosDoDia);
                vendasDoDia = new ArrayList<>();
                dataAtual = venda.getMomentoDaVenda();
            }
            vendasDoDia.add(venda);
        }
        DadoLucro dadosDoDia = constroiDadoLucroParaData(dataAtual, vendasDoDia);
        lucroPorDia.add(dadosDoDia);

        return lucroPorDia;
    }

    public DadoLucro constroiDadoLucroParaData(LocalDateTime data, List<Venda> vendas){
        BigDecimal lucro = vendas.stream()
                .map(i -> i.getLucroDaVenda())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new DadoLucro(lucro, data);
    }
}

class DadoLucro {
    private BigDecimal lucro;
    private LocalDateTime data;

    public DadoLucro(BigDecimal lucro, LocalDateTime data) {
        this.lucro = lucro;
        this.data = data;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public void setLucro(BigDecimal lucro) {
        this.lucro = lucro;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}