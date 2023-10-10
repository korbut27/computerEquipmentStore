package db;

import ComputerEquipmentStore.Receive;

import java.sql.SQLException;
import java.util.ArrayList;

public class SQLReceive implements ISQLReceive {
    private static SQLReceive instance;
    private ConnectionDB dbConnection;

    private SQLReceive() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLReceive getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLReceive();
        }
        return instance;
    }

    public ArrayList<Receive> get() {
        String str = "select sum(payments.balance) as receive, languages.`language`\n" +
                "    from students\n" +
                "    join `groups` on `groups`.idgroup = students.idgroup\n" +
                "    join `courses` on `courses`.idcourse = `groups`.idcourse\n" +
                "    join languages on `languages`.idlanguage = courses.idlanguage\n" +
                "    join `payments` on `payments`.idpayment = students.idpayment\n" +
                "    group by languages.`language`";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Receive> infList = new ArrayList<>();
        for (String[] items: result){
            Receive receive = new Receive();
//            receive.setBalance(Integer.parseInt(items[0]));
//            receive.setLanguage(items[1]);
            infList.add(receive);
        }
        return infList;
    }

    public ArrayList<Receive> getChart() {
        String str = "select sumprice,date\n" +
                "from orders;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Receive> infList = new ArrayList<>();
        for (String[] items: result){
            String[] item = items[1].split(" ", 4);
            Receive receive = new Receive();
            receive.setTotal_spent(Double.parseDouble(items[0]));
            receive.setDate(item[0] + " " + item[1] + " " + item[2]);
            infList.add(receive);
        }
        ArrayList<Receive> finalres = new ArrayList<>();
        String checkdate = "";
        for (Receive items: infList){
            if(!checkdate.equals(items.getDate())){
                Receive receive = new Receive();
                receive.setDate(items.getDate());
                double sumprice = 0;
                for (Receive item: infList){
                    if(items.getDate().equals(item.getDate())){
                        sumprice += item.getTotal_spent();
                    }
                }
                receive.setTotal_spent(sumprice);
                finalres.add(receive);
                checkdate = items.getDate();
            }
        }
        System.out.println(finalres);
        return infList;
    }
}
