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

public class ClientWorkController {
    @FXML
    private Button backButton;
    @FXML
    private Button addClientButton;
    @FXML
    private Button infClientButton;
    @FXML
    private Button deleteClientButton;


    @FXML
    void averagePerf(ActionEvent event) throws IOException {
        Connect.client.sendMessage("getChartProgress");
        WindowChanger.changeWindow(getClass(), infClientButton, "adminDiagrProgress.fxml", "", false);
    }

    @FXML
    void infClient(ActionEvent event) throws IOException {
        Connect.client.sendMessage("clientInfAdmin");
        WindowChanger.changeWindow(getClass(), infClientButton, "clientInf.fxml", "", false);
    }


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
    void deleteClient() throws IOException {
        WindowChanger.changeWindow(getClass(), deleteClientButton, "deleteClient.fxml", "", true);
    }

    @FXML
    void addClient() throws IOException {
        addClientButton.setOnAction(event -> {
            addClientButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/registrationAdmin.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene((root)));
            stage.show();
        });}
}
