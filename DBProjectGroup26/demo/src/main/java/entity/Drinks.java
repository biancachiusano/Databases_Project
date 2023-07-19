package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Drinks {
    private int drinkId;
    private String drinkName;
    private double drinkPrice;

    @Id
    @Column(name = "drink_id")
    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    @Basic
    @Column(name = "drinkName")
    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    @Basic
    @Column(name = "drinkPrice")
    public double getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(double drinkPrice) {
        this.drinkPrice = drinkPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drinks drinks = (Drinks) o;

        if (drinkId != drinks.drinkId) return false;
        if (Double.compare(drinks.drinkPrice, drinkPrice) != 0) return false;
        if (drinkName != null ? !drinkName.equals(drinks.drinkName) : drinks.drinkName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = drinkId;
        result = 31 * result + (drinkName != null ? drinkName.hashCode() : 0);
        temp = Double.doubleToLongBits(drinkPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
