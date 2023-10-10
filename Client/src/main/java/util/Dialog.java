package util;

import javafx.scene.control.Alert;

public class Dialog {
    static public void showAlertWithNullInput(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Ввод данных");
        alert.setContentText("Заполните пустые поля");
        alert.showAndWait();
    }

    static public void showAlertWithExistLogin(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Регистрация");
        alert.setContentText("Такой пользователь уже существует");
        alert.showAndWait();
    }

    static public void showAlertWithNoLogin(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Введите правильно логин или пароль");
        alert.setContentText("Такой пользователь не найден в системе");
        alert.showAndWait();
    }

    static public void showAlertWithData(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка: Сбой задачи");
        alert.setContentText("Проверьте введнные параметры");
        alert.showAndWait();
    }

    static public void correctOperation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Correct");
        alert.setHeaderText("");
        alert.setContentText("Операция прошла успешно");
        alert.showAndWait();
    }

    static public void showAlertWithDouble(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка 500: Ввод двоичных чисел");
        alert.setContentText("Заполните правильно стоимость");
        alert.showAndWait();
    }
}
