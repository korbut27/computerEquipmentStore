package db;

import ComputerEquipmentStore.Category;
import ComputerEquipmentStore.Role;

import java.util.ArrayList;

public interface ISQLCategory {
    ArrayList<Category> findCategories(Category obj);

    Category findCategory(String name);

    ArrayList<Category> findCategoryClient(int id, Category obj);

    boolean deleteCategory(Category obj);

    ArrayList<Category> get();

    Category getCategory(Role r);

    boolean insert(Category obj);

    boolean changeCategory(Category obj);
}
