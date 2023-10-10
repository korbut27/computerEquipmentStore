package controllers;

import ClientWork.Connect;
import model.Equipment;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.WindowChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BasketController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;
    @FXML
    private Button orderButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button refreshButton;

    @FXML
    private TableView<Equipment> itemTable;

    @FXML
    private TableColumn<Equipment, String> nameColumn;

    @FXML
    private TableColumn<Equipment, String> producerColumn;

    @FXML
    private TableColumn<Equipment, String> categoryColumn;

    @FXML
    private TableColumn<Equipment, String> parameter1Column;

    @FXML
    private TableColumn<Equipment, String> parameter2Column;

    @FXML
    private TableColumn<Equipment, String> priceColumn;

    @FXML
    private TextField login;

    ObservableList<Equipment> equipmentList = FXCollections.observableArrayList();

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "menuClient.fxml", "", false);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getName()));
        producerColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getProducer()));
        categoryColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getCategory()));
        parameter1Column.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getFirst_Parameter()));
        parameter2Column.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getSecond_Parameter()));
        priceColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getPrice() + "$"));
        itemTable.setItems(getBasketItem());
    }

    private ObservableList<Equipment> getBasketItem() {
        ObservableList<Equipment> equipmentList = FXCollections.observableArrayList();
        ArrayList<Equipment> equipment = (ArrayList<Equipment>) Connect.client.readObject();
        System.out.println(equipment);
        equipmentList.addAll(equipment);
        itemTable.setItems(equipmentList);
        return equipmentList;
    }

    public void deleteEquipment(ActionEvent actionEvent) throws IOException {
        WindowChanger.changeWindow(getClass(), deleteButton, "deleteBasketItem.fxml", "", true);
    }

    public void makeOrder(ActionEvent actionEvent) throws IOException {
        Connect.client.sendMessage("showBasket");
        WindowChanger.changeWindow(getClass(), orderButton, "makeOrder.fxml", "", false);
    }
    public void refreshBasket(ActionEvent actionEvent) throws IOException {
        Connect.client.sendMessage("showBasket");
        WindowChanger.changeWindow(getClass(), refreshButton, "basket.fxml", "", false);
    }
}
