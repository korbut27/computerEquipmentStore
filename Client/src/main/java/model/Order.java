package model;

import java.io.Serializable;
import java.util.Objects;

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

    public String getSumprice() {
        return Double.toString(sumprice);
    }

    public void setSumprice(double sumprice) {
        this.sumprice = sumprice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order that = (Order) o;

        return  Objects.equals(this.id_user, that.id_user) &&
                Objects.equals(this.contents, that.contents) &&
                Objects.equals(this.sumprice, that.sumprice) &&
                Objects.equals(this.date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id_user, this.contents, this.sumprice, this.date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", id_user='" + id_user + '\'' +
                ", contents='" + contents + '\'' +
                ", sumprice='" + sumprice + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
