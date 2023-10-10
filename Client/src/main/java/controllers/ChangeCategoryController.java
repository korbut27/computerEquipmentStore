package controllers;

import ClientWork.Connect;
import model.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.Dialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeCategoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name;

    @FXML
    private TextField parameter1;

    @FXML
    private TextField parameter2;

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;



    @FXML
    void changeCategory(ActionEvent event) {
        if (checkInput())
            Dialog.showAlertWithNullInput();
        else {
            Category category = new Category();
            category.setName(name.getText());
            category.setParameter_1(parameter1.getText());
            category.setParameter_2(parameter2.getText());
            Connect.client.sendMessage("changeCategory");
            Connect.client.sendObject(category);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connect.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            if (mes.equals("Incorrect Data"))
                Dialog.showAlertWithExistLogin();
            else {
                Dialog.correctOperation();
            }
        }
    }

    private boolean checkInput() {
        try {
            return  name.getText().equals("") || parameter1.getText().equals("") || parameter2.getText().equals("");
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
