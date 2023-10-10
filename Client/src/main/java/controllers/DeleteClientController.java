package controllers;

import ClientWork.Connect;
import ComputerEquipmentStore.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.Dialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteClientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField idstud;

    @FXML
    void deleteStud(ActionEvent event) {
        if (checkInput())
            Dialog.showAlertWithNullInput();
        else {
            Client client = new Client();
            client.setLogin(idstud.getText());
            Connect.client.sendMessage("deleteClient");
            Connect.client.sendObject(client);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connect.client.readMessage();
            } catch (IOException ex) {
                System.out.println("Error in reading");
            }
            System.out.println(mes);
            if (mes.equals("Ошибка при удалении клиента"))
                Dialog.showAlertWithData();
            else {
                Dialog.correctOperation();
            }
        }
    }

    private boolean checkInput() {
        try {
            return idstud.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

    @FXML
    void initialize() {

    }
}
