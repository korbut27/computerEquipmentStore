package controllers;

import ClientWork.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.WindowChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DbWorkController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button addEquipmentButton;

    @FXML
    private Button changeEquipmentButton;

    @FXML
    private Button timetableButton;

    @FXML
    private Button equipmentListButton;

    @FXML
    private Button categoryListButton;

    @FXML
    private Button chTimetableButton;

    @FXML
    private Button changeCategoryButton;

    @FXML
    private Button deleteEquipmentButton;

    @FXML
    private Button addCategoryButton;



    @FXML
    void addCourse(ActionEvent event) throws IOException {
//        WindowChanger.changeWindow(getClass(), courseButton, "addCourse.fxml", "AddCourse", true);
    }

    @FXML
    void addEquipment(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showCategoriesList");
        WindowChanger.changeWindow(getClass(), addEquipmentButton, "selectCategory.fxml", "", false);
    }

    @FXML
    void showEquipmentList(ActionEvent event) throws IOException{
        Connect.client.sendMessage("showEquipmentList");
        WindowChanger.changeWindow(getClass(), equipmentListButton, "equipmentList.fxml", "", false);
    }

    @FXML
    void addTimetable(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), timetableButton, "addTimetable.fxml", "", false);
    }

    @FXML
    void changeCourse(ActionEvent event) {

    }

    @FXML
    void changeTeacher(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), timetableButton, "changeTeacher.fxml", "", true);
    }

    @FXML
    void changeTimetable(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), timetableButton, "changeTimetable.fxml", "", false);
    }

    @FXML
    void backToMenuAdmin(ActionEvent event) {
        backButton.getScene().getWindow().hide();

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

    @FXML
    void deleteEquipment(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), deleteEquipmentButton, "deleteEquipment.fxml", "", true);
    }
    @FXML
    void changeEquipment(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showCategoriesList");
        WindowChanger.changeWindow(getClass(), changeEquipmentButton, "changeEquipment.fxml", "", false);
    }

    @FXML
    void addCategory(ActionEvent event) {
        addCategoryButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/addCategory.fxml"));

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
    void showCategoryList(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showCategoriesList");
        WindowChanger.changeWindow(getClass(), categoryListButton, "categoriesList.fxml", "", false);
    }

    @FXML
    void changeCategory(ActionEvent event) throws IOException {
//        Connect.client.sendMessage("changeCategory");
//        WindowChanger.changeWindow(getClass(), changeCategoryButton, "changeCategory.fxml", "", false);
    }



    @FXML
    void deleteCategory(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), deleteEquipmentButton, "deleteCategory.fxml", "", true);
    }



    @FXML
    void initialize() {

    }
}
