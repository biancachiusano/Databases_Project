package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Desserts {
    private int dessertId;
    private String dessertName;
    private double dessertPrice;

    @Id
    @Column(name = "dessert_id")
    public int getDessertId() {
        return dessertId;
    }

    public void setDessertId(int dessertId) {
        this.dessertId = dessertId;
    }

    @Basic
    @Column(name = "dessertName")
    public String getDessertName() {
        return dessertName;
    }

    public void setDessertName(String dessertName) {
        this.dessertName = dessertName;
    }

    @Basic
    @Column(name = "dessertPrice")
    public double getDessertPrice() {
        return dessertPrice;
    }

    public void setDessertPrice(double dessertPrice) {
        this.dessertPrice = dessertPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Desserts desserts = (Desserts) o;

        if (dessertId != desserts.dessertId) return false;
        if (Double.compare(desserts.dessertPrice, dessertPrice) != 0) return false;
        if (dessertName != null ? !dessertName.equals(desserts.dessertName) : desserts.dessertName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = dessertId;
        result = 31 * result + (dessertName != null ? dessertName.hashCode() : 0);
        temp = Double.doubleToLongBits(dessertPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
