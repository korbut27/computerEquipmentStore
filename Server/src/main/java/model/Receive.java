package model;

import java.io.Serializable;

public class Receive implements Serializable {
    double total_spent;
    String date;

    public double getTotal_spent() {
        return total_spent;
    }

    public void setTotal_spent(double total_spent) {
        this.total_spent = total_spent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Receive{" +
                "total_spent=" + total_spent +
                ", date='" + date + '\'' +
                '}';
    }
}
