package controllers;

import ClientWork.Connect;
import model.Category;
import model.Equipment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Check;
import util.Dialog;

import java.io.IOException;
import java.util.ArrayList;

public class AddEquipmentController {

    @FXML
    private Button backButton;

    @FXML
    private TextField name;

    @FXML
    private TextField producer;

    @FXML
    private TextField sparameter1;

    @FXML
    private TextField sparameter2;

    @FXML
    private TextField price;

    @FXML
    private Label parameter1;

    @FXML
    private Label parameter2;

    @FXML
    private Label categorylabel;


    @FXML
    private Button registrationButton;


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
    void registrationEquipment(ActionEvent event) throws IOException {
        if (checkInput())
            Dialog.showAlertWithNullInput();
        else if (checkDouble())
            Dialog.showAlertWithDouble();
        else {
            Equipment equipment = new Equipment();
            equipment.setName(name.getText());
            equipment.setProducer(producer.getText());
            equipment.setCategory(categorylabel.getText());
            equipment.setFirst_Parameter(sparameter1.getText());
            equipment.setSecond_Parameter(sparameter2.getText());
            equipment.setPrice(price.getText());
            System.out.println(equipment.toString());
            Connect.client.sendMessage("addEquipment");
            Connect.client.sendObject(equipment);
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
            return name.getText().equals("") || producer.getText().equals("") ||
                    sparameter1.getText().equals("") || sparameter2.getText().equals("") ||
                    price.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

    private boolean checkDouble() {
        try {
            Check check =new Check();
            return !check.checkDouble(price.getText());
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

    @FXML
    void initialize() {
        ArrayList<Category> category = (ArrayList<Category>) Connect.client.readObject();
        for(Category ct : category){
            System.out.println(ct.toString());
            categorylabel.setText(ct.getName());
            parameter1.setText(ct.getParameter_1());
            parameter2.setText(ct.getParameter_2());
        }

    }
}
