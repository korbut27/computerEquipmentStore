package db;

import java.sql.SQLException;

public abstract class AbstractFactory {
    public abstract SQLClient getClients() throws SQLException, ClassNotFoundException;
    public abstract SQLAuthorization getRole() throws SQLException, ClassNotFoundException;
    public abstract SQLAdmin getAdmin() throws SQLException, ClassNotFoundException;
    public abstract SQLReceive getReceive() throws SQLException, ClassNotFoundException;
    public abstract SQLEquipment getEquipments() throws SQLException, ClassNotFoundException;

    public abstract SQLBasketItem getBasketItems() throws SQLException, ClassNotFoundException;
    public abstract SQLOrder getOrders() throws SQLException, ClassNotFoundException;
    public abstract SQLCategory getCategories() throws SQLException, ClassNotFoundException;
}
