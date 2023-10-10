package controllers;

import ClientWork.Connect;
import ComputerEquipmentStore.Category;
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

public class AddCategoryController {

    @FXML
    private Button backButton;

    @FXML
    private TextField name;

    @FXML
    private TextField parameter1;

    @FXML
    private TextField parameter2;


    @FXML
    private Button registrationButton;


    @FXML
    void initialize() {

    }

    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dbWork.fxml"));

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
    void addCategory(ActionEvent event) throws IOException {
        if (checkInput())
            Dialog.showAlertWithNullInput();
        else {
            Category category = new Category();
            category.setName(name.getText());
            category.setParameter_1(parameter1.getText());
            category.setParameter_2(parameter2.getText());
            Connect.client.sendMessage("addCategory");
            Connect.client.sendObject(category);
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
                Dialog.correctOperation();
            }
        }
    }

    private boolean checkInput() {
        try {
            return name.getText().equals("") || parameter1.getText().equals("") ||
                    parameter2.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }
}
