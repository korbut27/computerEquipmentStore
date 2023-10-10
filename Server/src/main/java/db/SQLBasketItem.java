package db;

import ComputerEquipmentStore.BasketItem;
import ComputerEquipmentStore.Role;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class SQLBasketItem implements ISQLBasketItem {
    private static SQLBasketItem instance;
    private ConnectionDB dbConnection;

    private SQLBasketItem() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLBasketItem getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLBasketItem();
        }
        return instance;
    }


    @Override
    public ArrayList<BasketItem> findBasketItem(BasketItem obj) {
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
    public boolean deleteBasketItem(BasketItem obj) {
        return false;
    }

    @Override
    public ArrayList<BasketItem> get() {

        String str = "select name, category, price\n" +
                "from basket;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<BasketItem> infList = new ArrayList<>();
        for (String[] items: result){
            BasketItem equipment = new BasketItem();
            equipment.setName(items[0]);
            equipment.setCategory(items[1]);
            equipment.setPrice(items[2]);
            infList.add(equipment);
        }
        return infList;
    }



    @Override
    public BasketItem getEBasketItem(Role r) {
        String str = "select name, category, price\n" +
                "from equipment;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        BasketItem equipment = new BasketItem();
        for (String[] items: result){
            equipment.setName(items[0]);
            equipment.setCategory(items[1]);
            equipment.setPrice(items[2]);
        }
        return equipment;
    }

    @Override
    public boolean insertToBasket(BasketItem obj) {
        String proc = "{call insert_equipment_to_basket(?,?,?,?,?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getName());
            callableStatement.setString(2, obj.getProducer());
            callableStatement.setString(3, obj.getCategory());
            callableStatement.setString(4, obj.getFirst_parameter());
            callableStatement.setString(5, obj.getSecond_parameter());
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

}
