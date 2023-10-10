package db;

import ComputerEquipmentStore.Client;
import ComputerEquipmentStore.Role;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.ArrayList;

public class SQLClient implements ISQLClient {
    private static SQLClient instance;
    private ConnectionDB dbConnection;

    private SQLClient() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLClient getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLClient();
        }
        return instance;
    }

    public ArrayList<Client> findClient(Client obj) {
        String str = "select `keys`.login, firstname, lastname, orders_amount, total_spent" +
                " from client" +
                " join `keys` on `keys`.`id_keys` = client.id_keys" +
                " where `keys`.login = \"" + obj.getLogin() + "\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Client> clientList = new ArrayList<>();
        for (String[] items: result){
            Client client = new Client();
            client.setLogin(items[0]);
            client.setFirstname(items[1]);
            client.setLastname(items[2]);
            client.setOrders_amount(Integer.parseInt(items[3]));
            client.setTotal_spent(Double.parseDouble(items[4]));
            clientList.add(client);
        }
        return clientList;
    }

    public boolean deleteClient(Client obj) {
        String proc = "{call delete_client(?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getLogin());
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
    public ArrayList<Client> get() {
        String str = "select `keys`.login, firstname, lastname, orders_amount, total_spent" +
                " from client" +
                " join `keys` on `keys`.`id_keys` = client.id_keys;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Client> infList = new ArrayList<>();
        for (String[] items: result){
            Client client = new Client();
            client.setLogin(items[0]);
            client.setFirstname(items[1]);
            client.setLastname(items[2]);
            client.setOrders_amount(Integer.parseInt(items[3]));
            client.setTotal_spent(Double.parseDouble(items[4]));
            infList.add(client);
        }
        return infList;
    }

    @Override
    public Client getClient(Role r) {
        String str = "select `keys`.login, firstname, lastname, orders_amount, total_spent" +
                " from client" +
                " join `keys` on `keys`.`id_keys` = client.id_keys" +
                " where client.id_keys = " + r.getId();;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Client client = new Client();
        for (String[] items: result){
            client.setLogin(items[0]);
            client.setFirstname(items[1]);
            client.setLastname(items[2]);
            client.setOrders_amount(Integer.parseInt(items[3]));
            client.setTotal_spent(Double.parseDouble(items[4]));
        }
        return client;
    }


    @Override
    public Role insert(Client obj) {
        String proc = "{call insert_client(?,?,?,?,?)}";
        Role r = new Role();
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getFirstname());
            callableStatement.setString(2, obj.getLastname());
            callableStatement.setString(3, obj.getLogin());
            callableStatement.setString(4, obj.getPassword());
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.execute();
            r.setId(callableStatement.getInt(5));
            r.setRole("student");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
