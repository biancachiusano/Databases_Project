import entity.DrinksForOrder;
import entity.ToppingsOnPizza;

import javax.persistence.EntityManager;

public class CRUDOperationsTopOnPizza {

    public void insertTPEntity(int order_id, int pizza_id, int topping_id){

        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        ToppingsOnPizza tp = new ToppingsOnPizza();
        tp.setOrderId(order_id);
        tp.setPizzaId(pizza_id);
        tp.setToppingId(topping_id);

        entityManager.persist(tp);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
