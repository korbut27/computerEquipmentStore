package controllers;

import ClientWork.Connect;
import ComputerEquipmentStore.Client;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import util.WindowChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientInformationController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> loginColumn;

    @FXML
    private TableColumn<Client, String> firstnameColumn;

    @FXML
    private TableColumn<Client, String> lastnameColumn;

    @FXML
    private TableColumn<Client, Integer> orderAmountColumn;
    @FXML
    private TableColumn<Client, String> totalPaymentColumn;


    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "menuClient.fxml", "", false);
    }

    @FXML
    void changestud(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLogin()));
        firstnameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getFirstname()));
        lastnameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLastname()));
        orderAmountColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getOrders_amount()));
        totalPaymentColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getTotal_spent() + "$"));
        clientTable.setItems(getClient());
    }

    private ObservableList<Client> getClient() {
        ObservableList<Client> studList = FXCollections.observableArrayList();
        Client client = (Client) Connect.client.readObject();
        System.out.println(client);
        studList.add(client);
        clientTable.setItems(studList);
        return studList;
    }
}
