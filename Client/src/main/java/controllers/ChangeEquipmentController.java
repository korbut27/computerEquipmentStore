package controllers;

import ClientWork.Connect;
import ComputerEquipmentStore.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import util.Dialog;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChangeEquipmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button goNextButton;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> categoryList = new ComboBox<>();

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
    void goNext(ActionEvent event) {
        if(categoryList.getValue() == "")
            Dialog.showAlertWithNullInput();
        else {
            Connect.client.sendMessage("findCategory");
            Category category = new Category();
            category.setName(categoryList.getValue());
            Connect.client.sendObject(category);
            goNextButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/addEquipment.fxml"));
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



    private boolean checkInput() {
        try {
            return categoryList.getValue().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }


    @FXML
    void initialize() {
        ArrayList<Category> category = (ArrayList<Category>) Connect.client.readObject();
        String[] categories = new String[category.size()];
        int i = 0;
        for(Category gg : category){
            categories[i] = gg.getName();
            i++;
        }
        for(String st : categories){
            categoryList.getItems().addAll(st);
        }
    }
}
