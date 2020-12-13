package iit.controllers;

import iit.service.IitCustService;
import iit.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.*;

public class IitAnalysisController implements Initializable {
    @FXML
    private StackPane pieChartPane, barChartPane;

    private IitCustService iitCustService = ServiceFactory.getIitCustServiceInstance();
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Map<String, Integer> typeList =  (HashMap)iitCustService.countEventsByProductId();
        Iterator<Map.Entry<String, Integer>> it = typeList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Use product statistics event pie chart");
        pieChartPane.getChildren().add(chart);

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis, yAxis);
        bc.setTitle("Use product statistics event histogram");
        xAxis.setLabel("product");
        yAxis.setLabel("event number");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Statistical data");

         Iterator<Map.Entry<String, Integer>> it1 = typeList.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry<String, Integer> entry = it1.next();
             series1.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }
        bc.getData().addAll(series1);
        barChartPane.getChildren().add(bc);
    }
}
