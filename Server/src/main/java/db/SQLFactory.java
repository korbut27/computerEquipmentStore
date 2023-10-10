package db;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class SQLFactory extends AbstractFactory {
    public SQLClient getClients() throws SQLException, ClassNotFoundException {
        return SQLClient.getInstance();
    }

    public SQLAuthorization getRole() throws SQLException, ClassNotFoundException {
        return SQLAuthorization.getInstance();
    }

    public SQLAdmin getAdmin() throws SQLException, ClassNotFoundException {
        return SQLAdmin.getInstance();
    }

    public SQLReceive getReceive() throws SQLException, ClassNotFoundException {
        return SQLReceive.getInstance();
    }

    @Override
    public SQLEquipment getEquipments() throws SQLException, ClassNotFoundException {
        return SQLEquipment.getInstance();
    }

    @Override
    public SQLBasketItem getBasketItems() throws SQLException, ClassNotFoundException {
        return SQLBasketItem.getInstance();
    }

    @Override
    public SQLOrder getOrders() throws SQLException, ClassNotFoundException {
        return SQLOrder.getInstance();
    }

    @Override
    public SQLCategory getCategories() throws SQLException, ClassNotFoundException {
        return SQLCategory.getInstance();
    }

    public boolean deleteBasket() {
        String proc = "{call delete_basket()}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
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
