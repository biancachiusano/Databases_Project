package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Delivery {
    private int deliveryId;
    private int orderId;
    private String deliveryTime;

    @Id
    @Column(name = "delivery_id")
    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Basic
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "deliveryTime")
    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delivery delivery = (Delivery) o;

        if (deliveryId != delivery.deliveryId) return false;
        if (orderId != delivery.orderId) return false;
        if (deliveryTime != null ? !deliveryTime.equals(delivery.deliveryTime) : delivery.deliveryTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deliveryId;
        result = 31 * result + orderId;
        result = 31 * result + (deliveryTime != null ? deliveryTime.hashCode() : 0);
        return result;
    }
}
