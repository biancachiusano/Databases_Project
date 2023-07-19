package entity;

import javax.persistence.*;

@Entity
@Table(name = "desserts_for_order", schema = "usersdb", catalog = "")
public class DessertsForOrder {
    private int orderId;
    private Integer dessertId;

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "dessert_id")
    public Integer getDessertId() {
        return dessertId;
    }

    public void setDessertId(Integer dessertId) {
        this.dessertId = dessertId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DessertsForOrder that = (DessertsForOrder) o;

        if (orderId != that.orderId) return false;
        if (dessertId != null ? !dessertId.equals(that.dessertId) : that.dessertId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (dessertId != null ? dessertId.hashCode() : 0);
        return result;
    }
}
