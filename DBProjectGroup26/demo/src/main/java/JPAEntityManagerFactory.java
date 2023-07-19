
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAEntityManagerFactory {

    public static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory(){
        if(entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
        }
        return entityManagerFactory;
    }

    public static void shutdown(){
        if(entityManagerFactory != null){
            entityManagerFactory.close();
        }
    }

}
