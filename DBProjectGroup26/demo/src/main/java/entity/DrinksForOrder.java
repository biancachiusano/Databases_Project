package entity;

import javax.persistence.*;

@Entity
@Table(name = "drinks_for_order", schema = "usersdb", catalog = "")
public class DrinksForOrder {
    private int orderId;
    private Integer drinkId;

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "drink_id")
    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrinksForOrder that = (DrinksForOrder) o;

        if (orderId != that.orderId) return false;
        if (drinkId != null ? !drinkId.equals(that.drinkId) : that.drinkId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (drinkId != null ? drinkId.hashCode() : 0);
        return result;
    }
}
