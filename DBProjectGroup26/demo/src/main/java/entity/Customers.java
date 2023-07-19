package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customers {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private int numberPizzaOrdered;
    private String hasCurrentDiscount;
    private String customerSurname;

    @Id
    @Column(name = "customer_id")
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "customerName")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "customerAddress")
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Basic
    @Column(name = "customerPhone")
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Basic
    @Column(name = "numberPizzaOrdered")
    public int getNumberPizzaOrdered() {
        return numberPizzaOrdered;
    }

    public void setNumberPizzaOrdered(int numberPizzaOrdered) {
        this.numberPizzaOrdered = numberPizzaOrdered;
    }

    @Basic
    @Column(name = "hasCurrentDiscount")
    public String getHasCurrentDiscount() {
        return hasCurrentDiscount;
    }

    public void setHasCurrentDiscount(String hasCurrentDiscount) {
        this.hasCurrentDiscount = hasCurrentDiscount;
    }

    @Basic
    @Column(name = "customerSurname")
    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customers customers = (Customers) o;

        if (customerId != customers.customerId) return false;
        if (numberPizzaOrdered != customers.numberPizzaOrdered) return false;
        if (customerName != null ? !customerName.equals(customers.customerName) : customers.customerName != null)
            return false;
        if (customerAddress != null ? !customerAddress.equals(customers.customerAddress) : customers.customerAddress != null)
            return false;
        if (customerPhone != null ? !customerPhone.equals(customers.customerPhone) : customers.customerPhone != null)
            return false;
        if (hasCurrentDiscount != null ? !hasCurrentDiscount.equals(customers.hasCurrentDiscount) : customers.hasCurrentDiscount != null)
            return false;
        if (customerSurname != null ? !customerSurname.equals(customers.customerSurname) : customers.customerSurname != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (customerAddress != null ? customerAddress.hashCode() : 0);
        result = 31 * result + (customerPhone != null ? customerPhone.hashCode() : 0);
        result = 31 * result + numberPizzaOrdered;
        result = 31 * result + (hasCurrentDiscount != null ? hasCurrentDiscount.hashCode() : 0);
        result = 31 * result + (customerSurname != null ? customerSurname.hashCode() : 0);
        return result;
    }
}
