package controllers;

import ClientWork.Connect;
import model.Order;
import model.Role;
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
import util.Dialog;
import util.WindowChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientHistoryController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, Integer> numberColumn;

    @FXML
    private TableColumn<Order, String> contentsColumn;

    @FXML
    private TableColumn<Order, String> priceColumn;

    @FXML
    private TableColumn<Order, String> dateColumn;

    @FXML
    private TextField number;

    ObservableList<Order> orderList = FXCollections.observableArrayList();

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "menuClient.fxml", "", false);
    }

    @FXML
    void searcOrder(ActionEvent event) {
        if(number.getText() == "")
            Dialog.showAlertWithNullInput();
        else {
            Connect.client.sendMessage("findOrderClient");
            Role r = new Role();
            r.setId(Connect.id);
            Connect.client.sendObject(r);
            Order rd = new Order();
            rd.setId(Integer.parseInt(number.getText()));
            Connect.client.sendObject(rd);
            try {
                orderList.clear();
                ArrayList<Order> order = (ArrayList<Order>) Connect.client.readObject();
                System.out.println(order);
                orderList.addAll(order);
                for (int i = 0; i < orderList.size(); i++)
                    orderTable.setItems(orderList);
            } catch (Exception ex) {

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getId_user()));
        contentsColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getContents()));
        priceColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getSumprice() + "$"));
        dateColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getDate()));
        orderTable.setItems(getOrder());
    }

    private ObservableList<Order> getOrder() {
        ObservableList<Order> orderList = FXCollections.observableArrayList();
        ArrayList<Order> orders = (ArrayList<Order>) Connect.client.readObject();
        System.out.println(orders);
        orderList.addAll(orders);
        orderTable.setItems(orderList);
        return orderList;
    }
}
