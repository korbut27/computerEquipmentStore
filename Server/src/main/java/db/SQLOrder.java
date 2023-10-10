package db;

import ComputerEquipmentStore.Order;
import ComputerEquipmentStore.Role;

import java.sql.SQLException;
import java.util.ArrayList;

public class SQLOrder implements ISQLEOrder {
    private static SQLOrder instance;
    private ConnectionDB dbConnection;

    private SQLOrder() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLOrder getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLOrder();
        }
        return instance;
    }


    @Override
    public ArrayList<Order> findOrder(Order obj) {
        String str = "select idorder, iduser, contents, sumprice, date\n" +
                "from orders" + " where `orders`.iduser = \"" + obj.getId_user() + "\";";;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Order> infList = new ArrayList<>();
        for (String[] items: result){
            Order order = new Order();
            order.setId(Integer.parseInt(items[0]));
            order.setId_user(Integer.parseInt(items[1]));
            order.setContents(items[2]);
            order.setSumrice(Double.valueOf(items[3]));
            order.setDate(items[4]);
            infList.add(order);
        }
        return infList;
    }

    @Override
    public ArrayList<Order> findOrderClient(int id, Order obj) {
        String str = "select idorder, contents, sumprice, date\n" +
                "from orders" +  " where `orders`.iduser = \"" + id +"\"" + " and `orders`.idorder = \"" + obj.getId() +"\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Order> infList = new ArrayList<>();
        for (String[] items: result){
            Order order = new Order();
            order.setId_user(Integer.parseInt(items[0]));
            order.setContents(items[1]);
            order.setSumrice(Double.valueOf(items[2]));
            order.setDate(items[3]);
            infList.add(order);
        }
        return infList;
    }

    @Override
    public boolean deleteOrder(Order obj) {
        return false;
    }

    @Override
    public ArrayList<Order> get() {
        String str = "select idorder, iduser, contents, sumprice, date\n" +
                "from orders;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Order> infList = new ArrayList<>();
        for (String[] items: result){
            Order order = new Order();
            order.setId(Integer.parseInt(items[0]));
            order.setId_user(Integer.parseInt(items[1]));
            order.setContents(items[2]);
            order.setSumrice(Double.valueOf(items[3]));
            order.setDate(items[4]);
            infList.add(order);
        }
        return infList;
    }

    @Override
    public ArrayList<Order> getClient(int id) {
        String str = "select idorder, contents, sumprice, date\n" +
                "from orders" +  " where `orders`.iduser = \"" + id + "\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Order> infList = new ArrayList<>();
        for (String[] items: result){
            Order order = new Order();
            order.setId_user(Integer.parseInt(items[0]));
            order.setContents(items[1]);
            order.setSumrice(Double.valueOf(items[2]));
            order.setDate(items[3]);
            infList.add(order);
        }
        return infList;
    }


    @Override
    public Order getOrder(Role r) {
        String str = "select name, category, price\n" +
                "from equipment;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Order order = new Order();
        for (String[] items: result){
            order.setId_user(Integer.parseInt(items[0]));
            order.setContents(items[1]);
            order.setSumrice(Double.valueOf(items[2]));
            order.setDate(items[3]);
        }
        return order;
    }

    @Override
    public boolean insert(Order obj) {
        return true;
    }

}
