package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pizza {
    private int pizzaId;
    private String pizzaType;
    private double pizzaPrice;

    @Id
    @Column(name = "pizza_id")
    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    @Basic
    @Column(name = "pizzaType")
    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    @Basic
    @Column(name = "pizzaPrice")
    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (pizzaId != pizza.pizzaId) return false;
        if (Double.compare(pizza.pizzaPrice, pizzaPrice) != 0) return false;
        if (pizzaType != null ? !pizzaType.equals(pizza.pizzaType) : pizza.pizzaType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = pizzaId;
        result = 31 * result + (pizzaType != null ? pizzaType.hashCode() : 0);
        temp = Double.doubleToLongBits(pizzaPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
