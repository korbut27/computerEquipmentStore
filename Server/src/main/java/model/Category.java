package model;

import java.io.Serializable;

public class Category implements Serializable {
    int id;
    String name;
    String parameter_1;
    String parameter_2;

    private static final long serialVersionUID = -7776403706378974655L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParameter_1() {
        return parameter_1;
    }

    public void setParameter_1(String parameter_1) {
        this.parameter_1 = parameter_1;
    }

    public String getParameter_2() {
        return parameter_2;
    }

    public void setParameter_2(String parameter_2) {
        this.parameter_2 = parameter_2;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parameter1='" + parameter_1 + '\'' +
                ", parameter2='" + parameter_2 + '\'' +
                '}';
    }
}
