package controllers;

import ClientWork.Connect;
import ComputerEquipmentStore.Client;
import ComputerEquipmentStore.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Dialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private Button registrationButton;


    @FXML
    void initialize() {

    }

    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        Role role = new Role();
        System.out.println( role.getRole());
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
    void registrationStudent(ActionEvent event) throws IOException {
        if (checkInput())
            Dialog.showAlertWithNullInput();
        else {
            Client student = new Client();
            student.setFirstname(firstName.getText());
            student.setLastname(lastName.getText());
            student.setLogin(login.getText());
            student.setPassword(password.getText());
            Connect.client.sendMessage("registrationStudent");
            Connect.client.sendObject(student);
            System.out.println("Запись отправлена");


            String mes = "";
            try {
                mes = Connect.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            if (mes.equals("This user is already existed"))
                Dialog.showAlertWithExistLogin();
            else {
                Role r = (Role) Connect.client.readObject();
                Connect.id = r.getId();
                Connect.role = r.getRole();
                registrationButton.getScene().getWindow().hide();

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
        }
    }

    private boolean checkInput() {
        try {
            return firstName.getText().equals("") || lastName.getText().equals("") ||
                    login.getText().equals("") || password.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }
}
