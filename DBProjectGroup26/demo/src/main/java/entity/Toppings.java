package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Toppings {
    private int toppingId;
    private String toppingName;
    private double toppingPrice;
    private byte isVegetarian;

    @Id
    @Column(name = "topping_id")
    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    @Basic
    @Column(name = "toppingName")
    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    @Basic
    @Column(name = "toppingPrice")
    public double getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(double toppingPrice) {
        this.toppingPrice = toppingPrice;
    }

    @Basic
    @Column(name = "isVegetarian")
    public byte getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(byte isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Toppings toppings = (Toppings) o;

        if (toppingId != toppings.toppingId) return false;
        if (Double.compare(toppings.toppingPrice, toppingPrice) != 0) return false;
        if (isVegetarian != toppings.isVegetarian) return false;
        if (toppingName != null ? !toppingName.equals(toppings.toppingName) : toppings.toppingName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = toppingId;
        result = 31 * result + (toppingName != null ? toppingName.hashCode() : 0);
        temp = Double.doubleToLongBits(toppingPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) isVegetarian;
        return result;
    }
}
