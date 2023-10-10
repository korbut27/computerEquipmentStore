package controllers;

import ClientWork.Connect;
import model.Client;
import model.Equipment;
import model.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.WindowChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuClientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button personalInfButton;

    @FXML
    private Button perstimetable;

    @FXML
    private Button basketButton;
    @FXML
    private Button persTeacherButton;

    @FXML
    private Button equipmentListButton;

    @FXML
    private Button coursesButton;

    @FXML
    private Label login;

    @FXML
    private Label basket;

    @FXML
    void persInf(ActionEvent event) throws IOException {
        Connect.client.sendMessage("clientInf");
        Role r = new Role();
        r.setId(Connect.id);
        Connect.client.sendObject(r);
        WindowChanger.changeWindow(getClass(), personalInfButton, "clientInformation.fxml", "", false);
    }

    @FXML
    void persTeacherTimetable(ActionEvent event) throws IOException {

    }

    @FXML
    void changeInf(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showCourses");
        WindowChanger.changeWindow(getClass(), coursesButton, "showCourses.fxml", "", false);
    }

    @FXML
    void showEquipmentList(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showEquipmentList");
        WindowChanger.changeWindow(getClass(), equipmentListButton, "equipmentListClient.fxml", "", false);
    }

    @FXML
    void showBasket(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showBasket");
        WindowChanger.changeWindow(getClass(), equipmentListButton, "basket.fxml", "", false);
    }

    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main.fxml"));

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
    void showHistory(ActionEvent event) throws IOException {
        Connect.client.sendMessage("clientHistory");
        Role r = new Role();
        r.setId(Connect.id);
        Connect.client.sendObject(r);
        WindowChanger.changeWindow(getClass(), historyButton, "clientHistory.fxml", "", false);
    }

    @FXML
    void initialize() {
        Client client = getClient();
        login.setText(client.getLogin());
        basket.setText(String.valueOf(getBasket()));

    }

    private Client getClient() {
        Connect.client.sendMessage("clientInf");
        Role r = new Role();
        r.setId(Connect.id);
        Connect.client.sendObject(r);
        Client client = (Client) Connect.client.readObject();
        System.out.println(client);
        return client;
    }
    private int getBasket() {
        Connect.client.sendMessage("showBasket");
        ArrayList<Equipment> equipment = (ArrayList<Equipment>) Connect.client.readObject();
        System.out.println(equipment);
//        if(equipment.equals("[]")){
//            return 0;
//        } else{
//            return equipment.size();
//        }
        return equipment.size();
    }
}
