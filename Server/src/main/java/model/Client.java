package model;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable {
    private String firstname;
    private String lastname;
    private int orders_amount;
    private double total_spent;
    private String login;
    private String password;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getOrders_amount() {
        return orders_amount;
    }

    public void setOrders_amount(int orders_amount) {
        this.orders_amount = orders_amount;
    }

    public double getTotal_spent() {
        return total_spent;
    }

    public void setTotal_spent(double total_spent) {
        this.total_spent = total_spent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client that = (Client) o;

        return  Objects.equals(this.login, that.login) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.firstname, that.firstname) &&
                Objects.equals(this.lastname, that.lastname)&&
                Objects.equals(this.orders_amount, that.orders_amount) &&
                Objects.equals(this.total_spent, that.total_spent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.login, this.password, this.firstname, this.lastname);
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", orders amount='" + orders_amount + '\'' +
                ", total spent='" + total_spent + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password +
                '}';
    }
}
