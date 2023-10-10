package ServerWork;

import ComputerEquipmentStore.*;
import DB.SQLFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;

public class Worker implements Runnable {
    protected Socket clientSocket = null;
    ObjectInputStream sois;
    ObjectOutputStream soos;

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            sois = new ObjectInputStream(clientSocket.getInputStream());
            soos = new ObjectOutputStream(clientSocket.getOutputStream());
            while (true) {
                System.out.println("Получение команды от клиента...");
                String choice = sois.readObject().toString();
                System.out.println(choice);
                System.out.println("Команда получена");
                switch (choice) {
                    case "authorization" -> {
                        System.out.println("Выполняется авторизация пользователя....");
                        Authorization auth = (Authorization) sois.readObject();
                        System.out.println(auth.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        Role r = sqlFactory.getRole().getRole(auth);
                        System.out.println(r.toString());

                        if (r.getId() != 0 && r.getRole() != "" && sqlFactory.deleteBasket()) {
                            soos.writeObject("OK");
                            soos.writeObject(r);
                        } else
                            soos.writeObject("There is no data!");
                    }
                    case "adminInf" -> {
                        System.out.println("Запрос к БД на получение информации об администраторе: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Admin> infList = sqlFactory.getAdmin().get();
                        System.out.println(infList.toString());
                        soos.writeObject(infList);
                    }
                    case "registrationStudent" -> {
                        System.out.println("Запрос к БД на проверку пользователя(таблица students), клиент: " + clientSocket.getInetAddress().toString());
                        Client client = (Client) sois.readObject();
                        System.out.println(client.toString());

                        SQLFactory sqlFactory = new SQLFactory();
                        Role r = sqlFactory.getClients().insert(client);
                        System.out.println((r.toString()));

                        if (r.getId() != 0 && r.getRole() != "") {
                            soos.writeObject("OK");
                            soos.writeObject(r);
                        } else {
                            soos.writeObject("This user is already existed");
                        }
                    }
                    case "clientInfAdmin" -> {
                        System.out.println("Запрос к БД на получение информации о студентах: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Client> clientList = sqlFactory.getClients().get();
                        System.out.println(clientList.toString());
                        soos.writeObject(clientList);
                    }
                    case "clientInf" -> {
                        System.out.println("Запрос к БД на проверку студента (таблица students), клиент: " + clientSocket.getInetAddress().toString());
                        Role r = (Role) sois.readObject();
                        System.out.println(r.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        Client client = sqlFactory.getClients().getClient(r);
                        System.out.println(client.toString());
                        soos.writeObject(client);
                    }
                    case "findClient" -> {
                        System.out.println("Запрос к БД на поиск студента: " + clientSocket.getInetAddress().toString());
                        Client st = (Client) sois.readObject();
                        System.out.println(st.toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Client> studList = sqlFactory.getClients().findClient(st);
                        System.out.println(studList.toString());
                        soos.writeObject(studList);
                    }
                    case "deleteClient" -> {
                        System.out.println("Выполняется удаление студента...");
                        Client client = (Client) sois.readObject();
                        System.out.println(client.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getClients().deleteClient(client)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при удалении студента");
                        }
                    }
                    case "addCategory" -> {
                        System.out.println("Запрос к БД на проверку категории(таблица categories), клиент: " + clientSocket.getInetAddress().toString());
                        Category category = (Category) sois.readObject();
                        System.out.println(category.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getCategories().insert(category)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Incorrect Data");
                        }
                    }
                    case "deleteCategory" -> {
                        System.out.println("Выполняется удаление категории...");
                        Category category = (Category) sois.readObject();
                        System.out.println(category.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getCategories().deleteCategory(category)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при удалении студента");
                        }
                    }
                    case "showCategoriesList" -> {
                        System.out.println("Запрос к БД на проверку категорий (таблица categories), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Category> categoryList = sqlFactory.getCategories().get();
                        System.out.println(categoryList.toString());
                        soos.writeObject(categoryList);
                    }
                    case "findCategory" -> {
                        System.out.println("Запрос к БД на поиск категории: " + clientSocket.getInetAddress().toString());
                        Category st = (Category) sois.readObject();
                        System.out.println(st.toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Category> categoryList = sqlFactory.getCategories().findCategories(st);
                        System.out.println(categoryList.toString());
                        soos.writeObject(categoryList);
                    }
                    case "changeCategory" -> {
                        System.out.println("Запрос к БД на изменение категории(таблица categories), клиент: " + clientSocket.getInetAddress().toString());
                        Category category = (Category) sois.readObject();
                        System.out.println(category.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getCategories().changeCategory(category)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Incorrect Data");
                        }
                    }
                    case "addEquipment" -> {
                        System.out.println("Запрос к БД на проверку оборудования(таблица equipment), клиент: " + clientSocket.getInetAddress().toString());
                        Equipment equipment = (Equipment) sois.readObject();
                        System.out.println(equipment.toString());

                        SQLFactory sqlFactory = new SQLFactory();
                        Category ct = sqlFactory.getCategories().findCategory(equipment.getCategory());
                        if (sqlFactory.getEquipments().insert(equipment, ct)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Incorrect Data");
                        }
                    }
                    case "addEquipmentToOrder" -> {
                        System.out.println("Запрос к БД на  оборудования: " + clientSocket.getInetAddress().toString());
                        Equipment st = (Equipment) sois.readObject();
                        System.out.println(st.toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<BasketItem> equipmentList1 = sqlFactory.getEquipments().findEquipmentForBasket(st);
                        if(equipmentList1.toString().equals("[]")){
                            soos.writeObject("Incorrect Data");
                        }else {

                            for (BasketItem obj: equipmentList1) {
                                System.out.println(obj.toString());
                                if (sqlFactory.getBasketItems().insertToBasket(obj)) {
                                    soos.writeObject("OK");
                                } else {
                                    soos.writeObject("Incorrect Data");
                                }
                                soos.writeObject("Ok");
                            }
                        }
                    }
                    case "showEquipmentList" -> {
                        System.out.println("Запрос к БД на проверку оборудования (таблица equipment), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Equipment> equipmentList = sqlFactory.getEquipments().get();
                        System.out.println(equipmentList.toString());
                        soos.writeObject(equipmentList);
                    }
                    case "findEquipment" -> {
                        System.out.println("Запрос к БД на поиск оборудования: " + clientSocket.getInetAddress().toString());
                        Equipment st = (Equipment) sois.readObject();
                        System.out.println(st.toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Equipment> equipmentList = sqlFactory.getEquipments().findEquipment(st);
                        System.out.println(equipmentList.toString());
                        soos.writeObject(equipmentList);
                    }
                    case "deleteEquipment" -> {
                        System.out.println("Выполняется удаление товара из каталога...");
                        Equipment equipment = (Equipment) sois.readObject();
                        System.out.println(equipment.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getEquipments().deleteEquipment(equipment)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при удалении оборудования");
                        }
                    }
                    case "showBasket" -> {
                        System.out.println("Запрос к БД на проверку корзины (таблица basket), клиент: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Equipment> equipmentList = sqlFactory.getEquipments().getBasket();
                        System.out.println(equipmentList.toString());
                        soos.writeObject(equipmentList);
                    }
                    case "addBasketItem" -> {
                        System.out.println("Запрос к БД на проверку оборудования(таблица busket), клиент: " + clientSocket.getInetAddress().toString());
                        Equipment equipment = (Equipment) sois.readObject();
                        System.out.println(equipment.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getEquipments().insertBasket(equipment)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Incorrect Data");
                        }

                    }
                    case "deleteEquipmentFromBasket" -> {
                        System.out.println("Выполняется удаление товара из корзины...");
                        Equipment equipment = (Equipment) sois.readObject();
                        System.out.println(equipment.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getEquipments().deleteEquipmentFromBasket(equipment)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при удалении товара");
                        }
                    }
                    case "makeOrder" -> {
                        System.out.println("Выполняется оформление заказа...");
                        SQLFactory sqlFactory = new SQLFactory();

                        Role role = (Role) sois.readObject();

                        if (sqlFactory.getEquipments().addEquipmentsToOrder(role.getId())) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при оформлении заказа");
                        }
                    }
                    case "showOrders" -> {
                        System.out.println("Запрос к БД на получение информации о заказах: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Order> ordersList = sqlFactory.getOrders().get();
                        System.out.println(ordersList.toString());
                        soos.writeObject(ordersList);
                    }
                    case "findOrder" -> {
                        System.out.println("Запрос к БД на получение информации о заказах: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        Order order = (Order) sois.readObject();
                        ArrayList<Order> ordersList = sqlFactory.getOrders().findOrder(order);
                        System.out.println(ordersList.toString());
                        soos.writeObject(ordersList);
                    }
                    case "findOrderClient" -> {
                        System.out.println("Запрос к БД на получение информации о заказах клиента: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        Role role = (Role) sois.readObject();
                        Order order = (Order) sois.readObject();
                        ArrayList<Order> ordersList = sqlFactory.getOrders().findOrderClient(role.getId(), order);
                        System.out.println(ordersList.toString());
                        soos.writeObject(ordersList);
                    }
                    case "clientHistory" -> {
                        System.out.println("Запрос к БД на получение информации о заказах клиента: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        Role role = (Role) sois.readObject();
                        ArrayList<Order> ordersList = sqlFactory.getOrders().getClient(role.getId());
                        System.out.println(ordersList.toString());
                        soos.writeObject(ordersList);
                    }
                    case "getDiagrReceive" -> {
                    }
                    case "getChartReceive" -> {
                        System.out.println("Запрос в БД на получение прибыли школы");
                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Receive> receives = sqlFactory.getReceive().getChart();

                        ArrayList<AbstractMap.SimpleEntry<String, Double>> data = new ArrayList<>();
                        for (Receive r : receives) {
                            data.add(new AbstractMap.SimpleEntry<String, Double>(
                                    r.getDate(), r.getTotal_spent()));
                        }

                        soos.writeObject(data);
                    }
                    case "getChartProgress" -> {
                    }
                    case "writeReceiveReport" -> {
//                        SQLFactory sqlFactory = new SQLFactory();
//
//                        ArrayList<Receive> receives = sqlFactory.getReceive().get();
//
//                        if (receives.size() == 0)
//                            soos.writeObject("Ничего нет");
//                        else {
//
//                            BufferedWriter outputWriter = null;
//                            outputWriter = new BufferedWriter(new FileWriter("profit"));
//                            outputWriter.write("Прибыль по курсам:\n");
//                            for (Receive r : receives) {
//                                outputWriter.write(r.getBalance() + "   " + r.getLanguage());
//                                outputWriter.newLine();
//                            }
//                            outputWriter.flush();
//                            outputWriter.close();
//
//                            soos.writeObject("OK");
//                        }
                    }
                    case "writeProgressReport" -> {
//                        SQLFactory sqlFactory = new SQLFactory();
//
//                        ArrayList<Client> students = sqlFactory.getClients().getProgress();
//
//                        if (students.size() == 0)
//                            soos.writeObject("Ничего нет");
//                        else {
//
//                            BufferedWriter outputWriter = null;
//                            outputWriter = new BufferedWriter(new FileWriter("progress"));
//                            outputWriter.write("Рейтинг студентов:\n");
//                            for (Client s : students) {
//                                outputWriter.write(s.getLogin() + "   " + s.getAverageMark());
//                                outputWriter.newLine();
//                            }
//                            outputWriter.flush();
//                            outputWriter.close();
//
//                            soos.writeObject("OK");
//                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.\n" + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println("Client disconnected.\n" + e.getMessage());

        }
        catch (SQLException e) {
            System.out.println("Client disconnected.\n" + e.getMessage());
        }
    }
}


