import entity.Orders;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class OrderStatus extends JFrame{

    //Time
    //Order status
    //Possibility to cancel
    //Delivery Person(?)
    //Execute on the console how much the restaurant makes --> 40% of the order

    JLabel priceLabel;
    JLabel discountLabel;
    JLabel confirmationLabel;
    JLabel deliveryTime;
    JLabel orderStatusLabel;

    JButton cancelOrderButton;

    static Double totPrice;

    //pass an order entity

    public OrderStatus(){
        JPanel mainPanel = new JPanel();
        JPanel overview = new JPanel();
        overview.setBorder(new TitledBorder(new EtchedBorder(), "Order Status"));

        priceLabel = new JLabel("Total Price: " + totPrice);
        discountLabel = new JLabel("10% Discount on this order: " );
        confirmationLabel = new JLabel("Order Confirmed: Yes"  );
        orderStatusLabel = new JLabel("Order is: Being Prepared" );
        deliveryTime = new JLabel("Delivery time: 15min");

        Box vbox = Box.createVerticalBox();
        vbox.add(priceLabel);
        vbox.add(discountLabel);
        vbox.add(confirmationLabel);
        vbox.add(orderStatusLabel);
        vbox.add(deliveryTime);

        overview.add(vbox);

        JPanel more = new JPanel();
        more.setBorder(new TitledBorder(new EtchedBorder(), "More..."));

        cancelOrderButton = new JButton("Cancel Order");
        more.add(cancelOrderButton);

        mainPanel.add(overview);
        mainPanel.add(more);

        add(mainPanel);
    }

    public static void initData(double price){
        /*EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Query ordId = entityManager.createNativeQuery("SELECT order_id from orders where customer_id=:CustId");
        ordId.setParameter("CustId", custId);
        int id = (int) ordId.getSingleResult();

        Orders o = entityManager.find(Orders.class, id);

        priceLabel = new JLabel("Total Price: " + price);
        discountLabel = new JLabel("10% Discount on this order: " );
        confirmationLabel = new JLabel("Order Confirmed: " + o.getIsConfirmed());
        orderStatusLabel = new JLabel("Order is: " + o.getOrderStatus());
        deliveryTime = new JLabel("Delivery time: 15min");

        entityManager.getTransaction().commit();
        entityManager.close();
         */

        totPrice = price;
    }

    public static void main(String[] args){
        JFrame frame = new OrderStatus();
        frame.setSize(500, 230);
        frame.setTitle("Order Status");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
