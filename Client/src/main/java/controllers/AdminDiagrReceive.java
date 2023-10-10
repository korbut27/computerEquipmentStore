package controllers;

import ClientWork.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import util.Dialog;
import util.WindowChanger;

import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminDiagrReceive {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button saveButton;

    @FXML
    private BarChart chart;

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "menuAdmin.fxml", "", false);
    }

    @FXML
    void save(ActionEvent event) throws IOException {
        Connect.client.sendMessage("writeReceiveReport");

        String mes = "";
        try {
            mes = Connect.client.readMessage();
        } catch(IOException ex){
            System.out.println("Ничего нет");
        }
        System.out.println(mes);
        if (mes.equals("Ошибка при составлении отчета"))
            Dialog.showAlertWithData();
        else {
            Dialog.correctOperation();
        }
    }

    @FXML
    void initialize() {
        ArrayList<AbstractMap.SimpleEntry<String, Double>> data
                = (ArrayList<AbstractMap.SimpleEntry<String, Double>>) Connect.client.readObject();
        System.out.println(data);
        int flag = 0;
        for (AbstractMap.SimpleEntry<String,Double> point: data) {
            if(flag != 0){
                XYChart.Series series1 = new XYChart.Series();
                series1.getData().add(new XYChart.Data(point.getKey(), point.getValue()));
                chart.getData().add(series1);
            }
            flag += 1;
        }
        chart.setLegendSide(Side.LEFT);
    }
}
