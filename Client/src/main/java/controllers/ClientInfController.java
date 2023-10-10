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
import javafx.scene.control.TextField;
import util.Dialog;
import util.WindowChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientInfController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button searchButton;

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
    private TextField login;

    ObservableList<Client> clientList = FXCollections.observableArrayList();

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "clientWork.fxml", "", false);
    }

    @FXML
    void searchclient(ActionEvent event) {
        if(login.getText() == "")
            Dialog.showAlertWithNullInput();
        else {
            Connect.client.sendMessage("findClient");
            Client st = new Client();
            st.setLogin(login.getText());
            Connect.client.sendObject(st);
            try {
                clientList.clear();
                ArrayList<Client> students = (ArrayList<Client>) Connect.client.readObject();
                System.out.println(students);
                clientList.addAll(students);
                for (int i = 0; i < clientList.size(); i++)
                    clientTable.setItems(clientList);
            } catch (Exception ex) {

            }
        }
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
        ObservableList<Client> clientList = FXCollections.observableArrayList();
        ArrayList<Client> clients = (ArrayList<Client>) Connect.client.readObject();
        System.out.println(clients);
        clientList.addAll(clients);
        clientTable.setItems(clientList);
        return clientList;
    }
}
