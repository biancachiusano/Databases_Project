package entity;

import javax.persistence.*;

@Entity
@Table(name = "toppings_on_pizza", schema = "usersdb", catalog = "")
public class ToppingsOnPizza {
    private int orderId;
    private int pizzaId;
    private Integer toppingId;

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "pizza_id")
    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    @Basic
    @Column(name = "topping_id")
    public Integer getToppingId() {
        return toppingId;
    }

    public void setToppingId(Integer toppingId) {
        this.toppingId = toppingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToppingsOnPizza that = (ToppingsOnPizza) o;

        if (orderId != that.orderId) return false;
        if (pizzaId != that.pizzaId) return false;
        if (toppingId != null ? !toppingId.equals(that.toppingId) : that.toppingId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + pizzaId;
        result = 31 * result + (toppingId != null ? toppingId.hashCode() : 0);
        return result;
    }
}
