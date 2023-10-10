package model;

import java.io.Serializable;

public class Equipment implements Serializable {
    int id;
    String name;
    String category;
    String producer;
    String first_Parameter;

    String second_Parameter;

    String price;
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getFirst_Parameter() {
        return first_Parameter;
    }

    public void setFirst_Parameter(String first_Parameter) {
        this.first_Parameter = first_Parameter;
    }

    public String getSecond_Parameter() {
        return second_Parameter;
    }

    public void setSecond_Parameter(String second_Parameter) {
        this.second_Parameter = second_Parameter;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Equipment() {
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", category='" + category + '\'' +
                ", firstParameter='" + first_Parameter + '\'' +
                ", secondParameter='" + second_Parameter + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
