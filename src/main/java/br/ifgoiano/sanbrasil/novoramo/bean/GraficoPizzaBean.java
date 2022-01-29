package br.ifgoiano.sanbrasil.novoramo.bean;

import br.ifgoiano.sanbrasil.novoramo.service.PesquisaService;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.io.Serializable;

@ManagedBean("GraficoPizzaMB")
@Slf4j
public class GraficoPizzaBean implements Serializable {

    private PieChartModel pieModel1;

    @Autowired
    private PesquisaService pesquisaService;

    @PostConstruct
    public void init() {
        createPieModels();
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    private void createPieModels() {
        createPieModel1();
    }

    private void createPieModel1() {
        log.info("Gerando o grafico de qtd de Casos de Doença por estado");
        pieModel1 = new PieChartModel();
        var listaPesquisaCasosPorEstado = pesquisaService.findQtdCasosDoencaEstado();

        listaPesquisaCasosPorEstado.forEach(pesquisa -> pieModel1.set(pesquisa.getNome(), pesquisa.getQtdTotal()));

        pieModel1.setTitle("Quantidade de Casos de Doença por estado");
        pieModel1.setLegendPosition("w");
    }

}