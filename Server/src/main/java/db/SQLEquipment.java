package db;

import ComputerEquipmentStore.BasketItem;
import ComputerEquipmentStore.Category;
import ComputerEquipmentStore.Equipment;
import ComputerEquipmentStore.Role;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;

public class SQLEquipment implements ISQLEquipment {
    private static SQLEquipment instance;
    private ConnectionDB dbConnection;

    private SQLEquipment() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLEquipment getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLEquipment();
        }
        return instance;
    }


    @Override
    public ArrayList<Equipment> findEquipment(Equipment obj) {
        String str = "select name, producer, category, firstParameter, secondParameter, price\n" +
                "from equipment"+  " where `equipment`.name = \"" + obj.getName() + "\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Equipment> equipmentist = new ArrayList<>();
        for (String[] items: result){
            Equipment equipment = new Equipment();
            equipment.setName(items[0]);
            equipment.setProducer(items[1]);
            equipment.setCategory(items[2]);
            equipment.setFirst_Parameter(items[3]);
            equipment.setSecond_Parameter(items[4]);
            equipment.setPrice(items[5]);
            equipmentist.add(equipment);
        }
        return equipmentist;
    }
    @Override
    public ArrayList<BasketItem> findEquipmentForBasket(Equipment obj) {
        String str = "select name, category, price\n" +
                "from equipment" +  " where `equipment`.name = \"" + obj.getName() + "\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<BasketItem> equipmentist = new ArrayList<>();
        for (String[] items: result){
            BasketItem equipment = new BasketItem();
            equipment.setName(items[0]);
            equipment.setCategory(items[1]);
            equipment.setPrice(items[2]);
            equipmentist.add(equipment);
        }
        return equipmentist;
    }

    @Override
    public boolean deleteEquipment(Equipment obj) {
        String proc = "{call delete_equipment(?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getName());
            callableStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteEquipmentFromBasket(Equipment obj) {
        String proc = "{call delete_EquipmnetFromBasket(?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getName());
            callableStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Equipment> get() {
        String str = "select name, producer, category, firstParameter, secondParameter, price\n" +
                "from equipment;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Equipment> infList = new ArrayList<>();
        for (String[] items: result){
            Equipment equipment = new Equipment();
            equipment.setName(items[0]);
            equipment.setProducer(items[1]);
            equipment.setCategory(items[2]);
            equipment.setFirst_Parameter(items[3]);
            equipment.setSecond_Parameter(items[4]);
            equipment.setPrice(items[5]);
            infList.add(equipment);
        }
        return infList;
    }

    @Override
    public ArrayList<Equipment> getBasket() {
        String str = "select name, producer, category, parameter1, parameter2, price\n" +
                "from basket;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Equipment> infList = new ArrayList<>();
        for (String[] items: result){
            Equipment equipment = new Equipment();
            equipment.setName(items[0]);
            equipment.setProducer(items[1]);
            equipment.setCategory(items[2]);
            equipment.setFirst_Parameter(items[3]);
            equipment.setSecond_Parameter(items[4]);
            equipment.setPrice(items[5]);
            infList.add(equipment);
        }
        return infList;
    }

    @Override
    public Equipment getEquipment(Role r) {
        String str = "select name, category, price\n" +
                "from equipment;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Equipment equipment = new Equipment();
        for (String[] items: result){
            equipment.setName(items[0]);
            equipment.setCategory(items[1]);
            equipment.setPrice(items[2]);
        }
        return equipment;
    }

    @Override
    public boolean insert(Equipment obj, Category ct) {
        String proc = "{call insert_equipment(?,?,?, ?, ?, ?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            System.out.println(obj.toString());
            callableStatement.setString(1, obj.getName());
            callableStatement.setString(2, obj.getProducer());
            callableStatement.setString(3, obj.getCategory());
            callableStatement.setString(4, ct.getParameter_1() + ":\n" + obj.getFirst_Parameter());
            callableStatement.setString(5, ct.getParameter_2() + ":\n" + obj.getSecond_Parameter());
            callableStatement.setString(6, obj.getPrice());
            callableStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean insertBasket(Equipment obj) {
        String proc = "{call insert_equipment_to_basket(?,?,?,?,?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getName());
            callableStatement.setString(2, obj.getProducer());
            callableStatement.setString(3, obj.getCategory());
            callableStatement.setString(4, obj.getFirst_Parameter());
            callableStatement.setString(5, obj.getSecond_Parameter());
            callableStatement.setString(6, obj.getPrice());
            callableStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addEquipmentsToOrder(int id_user) {
        String proc = "{call make_order(?, ?, ?, ?)}";
        String str = "select name, category, price\n" +
                "from basket;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        String names = "";
        double sumprice = 0;
        for (String[] items: result){
            double i3 = 0;
            names += items[0] + ", ";
            try {
                i3 = Double.parseDouble(items[2]);
                sumprice += i3;
            } catch (NumberFormatException e) {
                System.err.println("Неправильный формат строки!");
            }
        }
        Date date = new Date();

        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            System.out.println("Order{equipments='" + names + "', sumprice='100'}");
            callableStatement.setInt(1, id_user);
            callableStatement.setString(2, names);
            callableStatement.setDouble(3, sumprice);
            callableStatement.setString(4, date.toString());
            callableStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
