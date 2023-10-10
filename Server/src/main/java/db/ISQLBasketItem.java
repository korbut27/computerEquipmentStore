package db;

import ComputerEquipmentStore.BasketItem;
import ComputerEquipmentStore.Role;

import java.util.ArrayList;

public interface ISQLBasketItem {
    ArrayList<BasketItem> findBasketItem(BasketItem obj);
    boolean deleteBasketItem(BasketItem obj);
    ArrayList<BasketItem> get();


    BasketItem getEBasketItem(Role r);


    boolean insertToBasket(BasketItem obj);
}
