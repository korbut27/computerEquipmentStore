package db;

import ComputerEquipmentStore.Client;
import ComputerEquipmentStore.Role;

import java.util.ArrayList;

public interface ISQLClient {
    ArrayList<Client> findClient(Client obj);
    Role insert(Client obj);
    boolean deleteClient(Client obj);
    ArrayList<Client> get();
    Client getClient(Role r);

}
