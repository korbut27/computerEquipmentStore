package model;

import java.io.Serializable;

public class BasketItem implements Serializable {
    int id;
    String name;

    String producer;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getFirst_parameter() {
        return first_parameter;
    }

    public void setFirst_parameter(String first_parameter) {
        this.first_parameter = first_parameter;
    }

    public String getSecond_parameter() {
        return second_parameter;
    }

    public void setSecond_parameter(String second_parameter) {
        this.second_parameter = second_parameter;
    }

    String category;
    String first_parameter;
    String second_parameter;
    String price;

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


    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", category='" + category + '\'' +
                ", parameter1='" + first_parameter + '\'' +
                ", parameter2='" + second_parameter + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
