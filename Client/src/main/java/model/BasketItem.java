package model;

import java.io.Serializable;
import java.util.Objects;

public class BasketItem implements Serializable {
    int id;
    String name;
    String category;
    String price;

    private static final long serialVersionUID = -7776403706378974655L;
    private Boolean unselected;

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

    public String getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketItem that = (BasketItem) o;

        return  Objects.equals(this.name, that.name) &&
                Objects.equals(this.category, that.category) &&
                Objects.equals(this.price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.category, this.price);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
