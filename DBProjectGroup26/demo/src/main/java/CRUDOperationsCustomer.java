import entity.Customers;

import javax.persistence.EntityManager;

public class CRUDOperationsCustomer {

    public void insertCustomerEntity(String name, String surname, String address, String phone){ //insert customer
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        //new customer 0 pizzas ordered and no discount
        Customers c = new Customers();
        c.setCustomerName(name);
        c.setCustomerSurname(surname);
        c.setCustomerAddress(address);
        c.setCustomerPhone(phone);
        c.setNumberPizzaOrdered(1);
        c.setHasCurrentDiscount("false");

        entityManager.persist(c);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void findCustomerEntity(int pk){
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Customers temp = entityManager.find(Customers.class, pk);
        System.out.println("Customer id: " + temp.getCustomerId());
        System.out.println("Customer name: " + temp.getCustomerName());
        System.out.println("Customer address: " + temp.getCustomerName());
        System.out.println("Customer phone: " + temp.getCustomerPhone());
        System.out.println("Customer number of orders: " + temp.getNumberPizzaOrdered());

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void updateCustomerEntity(int pk){ //updates amount of orders
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Customers temp = entityManager.find(Customers.class, pk);
        int currentOrders = temp.getNumberPizzaOrdered();
        temp.setNumberPizzaOrdered(currentOrders+1);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void removeCustomerEntity(int pk){ //removes customer
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Customers c = entityManager.find(Customers.class, pk);
        entityManager.remove(c);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

