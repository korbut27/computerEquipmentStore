package controllers;

import ClientWork.Connect;
import ComputerEquipmentStore.Equipment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.Dialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteEquipmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField name;

    @FXML
    void deleteEquipment(ActionEvent event) {
        if (checkInput())
            Dialog.showAlertWithNullInput();
        else {
            Equipment equipment = new Equipment();
            equipment.setName(name.getText());
            Connect.client.sendMessage("deleteEquipment");
            Connect.client.sendObject(equipment);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connect.client.readMessage();
            } catch (IOException ex) {
                System.out.println("Error in reading");
            }
            System.out.println(mes);
            if (mes.equals("Ошибка при удалении оборудования"))
                Dialog.showAlertWithData();
            else {
                Dialog.correctOperation();
            }
        }
    }

    private boolean checkInput() {
        try {
            return name.getText().equals("");
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
