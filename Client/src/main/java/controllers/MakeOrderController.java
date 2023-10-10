package controllers;

import ClientWork.Connect;
import ComputerEquipmentStore.Client;
import ComputerEquipmentStore.Equipment;
import ComputerEquipmentStore.Role;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import util.Dialog;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class MakeOrderController {

    @FXML
    private Button backButton;
    @FXML
    private TableView<Equipment> itemTable;

    @FXML
    private TableColumn<Equipment, String> nameColumn;
    @FXML
    private TableColumn<Equipment, String> categoryColumn;
    @FXML
    private TableColumn<Equipment, String> priceColumn;

    @FXML
    private TextField telnumber;

    @FXML
    private TextField mail;

    @FXML
    private TextField index;

    @FXML
    private TextField adres;

    @FXML
    private Label sumpricelabel;

    @FXML
    private Label fiolabel;

    @FXML
    private Button registrationButton;


    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/menuClient.fxml"));

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
    void registrationOrder(ActionEvent event) throws IOException {
        Connect.client.sendMessage("makeOrder");
        Role r = new Role();
        r.setId(Connect.id);
        Connect.client.sendObject(r);
        String mes = "";
        try {
            mes = Connect.client.readMessage();
        } catch (IOException ex) {
            System.out.println("Error in reading");
        }
        System.out.println(mes);
        if (mes.equals("Ошибка при оформлении заказа"))
            Dialog.showAlertWithData();
        else {
            Dialog.correctOperation();
        }
        if (checkInput())
            Dialog.showAlertWithNullInput();
        else {
            File file = new File("E:\\5 сем лабы\\курсач\\PSP-CP-samples\\PSP-CP-1\\project\\reports\\report.txt");
            PrintWriter pw = new PrintWriter(file);
            pw.println("EQUIPMENT STORE");
            pw.println("-----------------------\n");
            pw.println("ФИО покупателя: " + fiolabel.getText() + "\n");
            pw.println("Номер телефона: " + telnumber.getText() + "\n");
            pw.println("Почтовый индекс: " + index.getText() + "\n");
            pw.println("Адрес покупателя: " + adres.getText() + "\n");
            pw.println("-----------------------\n");
            pw.println("Список товаров:\n");
            pw.println("+--------------+---------------+---------------+\n");
            pw.println("|   Название   |   Категория   |   Стоимость   |\n");
            pw.println("+--------------+---------------+---------------+\n");
            ObservableList<Equipment> equipmentList = itemTable.getItems();
            for(Equipment eq: equipmentList){
                Equipment equipment = eq;
                pw.println("|" + equipment.getName() + "|" + equipment.getCategory() + "|" + equipment.getPrice() + "|\n");
                pw.println("+--------------+---------------+---------------+\n");
            }
            pw.println("Итоговая стоимоть:" + sumpricelabel.getText() + "\n");
            Date date = new Date();
            pw.println("Время оформления:" + date.toString() + "\n");
            pw.close();
        }
    }

    private boolean checkInput() {
        try {
            return telnumber.getText().equals("") || mail.getText().equals("") ||
                    index.getText().equals("") || adres.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

    @FXML
    void initialize() {
        nameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getName()));
        categoryColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getCategory()));
        priceColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getPrice() + "$"));
        itemTable.setItems(getBasketItem());

    }
    private ObservableList<Equipment> getBasketItem() {
        ObservableList<Equipment> equipmentList = FXCollections.observableArrayList();
        ArrayList<Equipment> equipment = (ArrayList<Equipment>) Connect.client.readObject();
        System.out.println(equipment);
        equipmentList.addAll(equipment);
        itemTable.setItems(equipmentList);
        double sum = 0;
        for(Equipment equipment1 : equipment){
            Equipment eq = equipment1;
            sum += Double.parseDouble(eq.getPrice());
        }
        sumpricelabel.setText(Double.toString(sum) + "$");
        Connect.client.sendMessage("clientInf");
        Role r = new Role();
        r.setId(Connect.id);
        Connect.client.sendObject(r);
        Client client = (Client) Connect.client.readObject();
        System.out.println(client);
        fiolabel.setText(client.getFirstname() + " " + client.getLastname());
        return equipmentList;
    }
}
