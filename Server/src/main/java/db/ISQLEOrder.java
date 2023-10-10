package db;

import ComputerEquipmentStore.Order;
import ComputerEquipmentStore.Role;

import java.util.ArrayList;

public interface ISQLEOrder {
    ArrayList<Order> findOrder(Order obj);

    ArrayList<Order> findOrderClient(int id, Order obj);

    boolean deleteOrder(Order obj);

    ArrayList<Order> get();

    ArrayList<Order> getClient(int id);

    Order getOrder(Role r);
    boolean insert(Order obj);

}
