package db;

import ComputerEquipmentStore.Receive;

import java.util.ArrayList;

public interface ISQLReceive {
    ArrayList<Receive> get();
    ArrayList<Receive> getChart();
}
