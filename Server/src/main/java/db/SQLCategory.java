package db;

import ComputerEquipmentStore.Category;
import ComputerEquipmentStore.Role;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class SQLCategory implements ISQLCategory {
    private static SQLCategory instance;
    private ConnectionDB dbConnection;

    private SQLCategory() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLCategory getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLCategory();
        }
        return instance;
    }


    @Override
    public ArrayList<Category> findCategories(Category obj) {
        String str = "select name, parameter1, parameter2\n" +
                "from categories" +  " where `categories`.name = \"" + obj.getName() + "\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Category> categoryList = new ArrayList<>();
        for (String[] items: result){
            Category category = new Category();
            category.setName(items[0]);
            category.setParameter_1(items[1]);
            category.setParameter_2(items[2]);
            categoryList.add(category);
        }
        return categoryList;
    }

    @Override
    public Category findCategory(String name) {
        String str = "select name, parameter1, parameter2\n" +
                "from categories" +  " where `categories`.name = \"" + name + "\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Category category = new Category();
        for (String[] items: result){
            category.setName(items[0]);
            category.setParameter_1(items[1]);
            category.setParameter_2(items[2]);
        }
        return category;
    }

    @Override
    public ArrayList<Category> findCategoryClient(int id, Category obj) {
        return null;
    }

    @Override
    public boolean deleteCategory(Category obj) {
        String proc = "{call delete_category(?)}";
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
    public ArrayList<Category> get() {
        String str = "select name, parameter1, parameter2\n" +
                "from categories;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Category> infList = new ArrayList<>();
        for (String[] items: result){
            Category Category = new Category();
            Category.setName(items[0]);
            Category.setParameter_1(items[1]);
            Category.setParameter_2(items[2]);
            infList.add(Category);
        }
        return infList;
    }


    @Override
    public Category getCategory(Role r) {
        String str = "select name, parameter1, parameter2\n" +
                "from Categories;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Category Category = new Category();
        for (String[] items: result){
            Category.setName(items[0]);
            Category.setParameter_1(items[1]);
            Category.setParameter_2(items[2]);
        }
        return Category;
    }

    @Override
    public boolean insert(Category obj) {
        String proc = "{call insert_category(?,?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            System.out.println(obj.toString());
            callableStatement.setString(1, obj.getName());
            callableStatement.setString(2, obj.getParameter_1());
            callableStatement.setString(3, obj.getParameter_2());
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
    public boolean changeCategory(Category obj) {
        String proc = "{call change_category(?,?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getName());
            callableStatement.setString(2, obj.getParameter_1());
            callableStatement.setString(3, obj.getParameter_2());
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
