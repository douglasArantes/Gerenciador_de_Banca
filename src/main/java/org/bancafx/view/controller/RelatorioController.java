package org.bancafx.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import org.bancafx.domain.entities.Produto;
import org.bancafx.domain.entities.Venda;
import org.bancafx.persistence.repositories.ProdutoRepository;
import org.bancafx.persistence.repositories.ProdutoRepositoryImp;
import org.bancafx.persistence.repositories.VendaRepositoryImp;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.stream.Collectors.*;

/**
 * Created by Douglas on 03/07/2014.
 */

public class RelatorioController implements Initializable {

    @FXML private TextField fieldPesquisar;
    @FXML private ListView<Produto> produtos;
    @FXML private LineChart<String, Number> lineChartLucroVenda;

    private CategoryAxis periodoAxisX;
    private NumberAxis lucroAxisY;

    private XYChart.Series<String, Number> series;
    private ObservableList<Produto> produtosList;
    private List<Venda> vendas;

    public RelatorioController() {
        produtosList = FXCollections.observableArrayList();
        produtos = new ListView<>();
        lucroAxisY = new NumberAxis();
        periodoAxisX  = new CategoryAxis();

        lucroAxisY.setLabel("Lucro");
        series = new XYChart.Series<>();
        lineChartLucroVenda = new LineChart<>(periodoAxisX, lucroAxisY);
        buscarVendas();
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

    private void preparaLineChart() {
        gerarNSeries(getDadosLucro().size());
        series.setName("lucros");
        lineChartLucroVenda.getData().add(series);
    }

    public void gerarGrafifoDeLucros7Dias() {
        limpaGrafico();
        buscarVendas();
        gerarNSeries(7);
    }

    public void gerarGrafifoDeLucros15Dias() {
        limpaGrafico();
        buscarVendas();
        gerarNSeries(15);
    }

    public void gerarGrafifoDeLucrosMes() {
        limpaGrafico();
        buscarVendas();
        gerarNSeries(30);
    }

    private void gerarNSeries(int numeroDeSeries) {
        final int size = getDadosLucro().size();
        if (size > numeroDeSeries) {
            List<DadoLucro> dados = getDadosLucro().stream().skip(size - numeroDeSeries).collect(toList());
            for (DadoLucro dado : dados){
                series.getData().add(new XYChart.Data<>(dado.getDataFormatada(), dado.getLucro()));
            }
        }else{
            for (DadoLucro dado : getDadosLucro()){
                series.getData().add(new XYChart.Data<>(dado.getDataFormatada(), dado.getLucro()));
            }
        }
    }

    private void limpaGrafico() {
        series.getData().clear();
        lineChartLucroVenda = new LineChart<>(periodoAxisX, lucroAxisY);
    }

    private static boolean mesmoDia(LocalDateTime ldt1, LocalDateTime ldt2) {
        return ldt1.getDayOfMonth() == ldt2.getDayOfMonth()
                && ldt1.getMonth().equals(ldt2.getMonth())
                && ldt1.getYear() == ldt2.getYear();
    }

    private List<DadoLucro> getDadosLucro() {
        List<DadoLucro> lucroPorDia = new ArrayList<>();
        List<Venda> vendasDoDia = new ArrayList<>();
        LocalDateTime dataAtual = vendas.get(0).getMomentoDaVenda();

        for (Venda venda : vendas) {
            if (!mesmoDia(venda.getMomentoDaVenda(), dataAtual)) {
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

    private DadoLucro constroiDadoLucroParaData(LocalDateTime data, List<Venda> vendas) {
        BigDecimal lucro = vendas.stream()
                .map(Venda::getLucroDaVenda)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new DadoLucro(lucro, data);
    }

    private void buscarVendas(){
        vendas = new ArrayList<>(new VendaRepositoryImp().buscarTodas());
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

    public LocalDateTime getData() {
        return data;
    }

    public String getDataFormatada(){
        return data.format(DateTimeFormatter.ofPattern("dd/MM"));
    }

    public String getHorarioFormatado(){
        return data.format(DateTimeFormatter.ofPattern("hh:mm"));
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.format(DateTimeFormatter.ofPattern("dd/MM")) + " - " + lucro;
    }
}