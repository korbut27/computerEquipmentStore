package controllers;

import ClientWork.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.WindowChanger;

import java.io.IOException;

public class StatisticWorkController {
    @FXML
    private Button backButton;

    @FXML
    private Button showOrdersButton;

    @FXML
    private Button pieChartButton;

    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/menuAdmin.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene((root)));
        stage.show();
    }

    @FXML
    void showOrders(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showOrders");
        WindowChanger.changeWindow(getClass(), showOrdersButton, "ordersList.fxml", "", false);
    }

    @FXML
    void showPieChart(ActionEvent event) throws IOException {
        Connect.client.sendMessage("getChartReceive");
        WindowChanger.changeWindow(getClass(), showOrdersButton, "adminDiagrReceive.fxml", "", false);
    }

}
