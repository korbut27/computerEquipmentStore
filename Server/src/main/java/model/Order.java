package model;

import java.io.Serializable;

public class Order implements Serializable {
    int id;
    int id_user;
    String contents;
    Double sumprice;
    String date;

    private static final long serialVersionUID = -7776403706378974655L;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Double getSumprice() {
        return sumprice;
    }

    public void setSumrice(Double sumprice) {
        this.sumprice = sumprice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId='" + id_user + '\'' +
                ", contents='" + contents + '\'' +
                ", price='" + sumprice + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
