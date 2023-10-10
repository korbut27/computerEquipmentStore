package db;

import ComputerEquipmentStore.BasketItem;
import ComputerEquipmentStore.Category;
import ComputerEquipmentStore.Equipment;
import ComputerEquipmentStore.Role;

import java.util.ArrayList;

public interface ISQLEquipment {
    ArrayList<Equipment> findEquipment(Equipment obj);

    ArrayList<BasketItem> findEquipmentForBasket(Equipment obj);

    boolean deleteEquipment(Equipment obj);
    boolean deleteEquipmentFromBasket(Equipment obj);

    ArrayList<Equipment> get();
    ArrayList<Equipment> getBasket();
    Equipment getEquipment(Role r);
    boolean insert(Equipment obj, Category ct);

    boolean insertBasket(Equipment obj);

    boolean addEquipmentsToOrder(int id_user);
}
