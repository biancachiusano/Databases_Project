import entity.Orders;

import javax.persistence.EntityManager;

public class CRUDOperationsOrder {

    public void insertOrderEntity(int customerId){

        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Orders o = new Orders();
        o.setCustomerId(customerId);
        o.setIsConfirmed((byte) 1);
        o.setOrderStatus("Preparing Order");

        entityManager.persist(o);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void findOrderEntity(){

    }
    public void updateOrderEntity(){


    }
    public void removeOrderEntity(){
        //if order is cancelled by customer
    }
}
