package controllers;

import ClientWork.Connect;
import model.Category;
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

public class CategoryListController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Category> categoryTable;

    @FXML
    private TableColumn<Category, String> nameColumn;

    @FXML
    private TableColumn<Category, String> parameter1Column;

    @FXML
    private TableColumn<Category, String> parameter2Column;

    @FXML
    private TextField name;

    ObservableList<Category> CategoryList = FXCollections.observableArrayList();

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "dbWork.fxml", "", false);
    }

    @FXML
    void searchCategory(ActionEvent event) {
        if(name.getText() == "")
            Dialog.showAlertWithNullInput();
        else {
            Connect.client.sendMessage("findCategory");
            Category st = new Category();
            st.setName(name.getText());
            Connect.client.sendObject(st);
            try {
                CategoryList.clear();
                ArrayList<Category> Category = (ArrayList<Category>) Connect.client.readObject();
                System.out.println(Category);
                CategoryList.addAll(Category);
                for (int i = 0; i < CategoryList.size(); i++)
                    categoryTable.setItems(CategoryList);
            } catch (Exception ex) {

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getName()));
        parameter1Column.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getParameter_1()));
        parameter2Column.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getParameter_2()));
        categoryTable.setItems(getCategory());
    }

    private ObservableList<Category> getCategory() {
        ObservableList<Category> categoryList = FXCollections.observableArrayList();
        ArrayList<Category> category = (ArrayList<Category>) Connect.client.readObject();
        System.out.println(category);
        categoryList.addAll(category);
        categoryTable.setItems(categoryList);
        return categoryList;
    }
}
