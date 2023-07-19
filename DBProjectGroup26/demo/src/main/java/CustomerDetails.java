import entity.Desserts;
import entity.Drinks;
import entity.Pizza;
import entity.Toppings;

import javax.persistence.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.UID;
import java.util.ArrayList;


public class CustomerDetails extends JFrame{



    private JTextField name;
    private JTextField lastName;
    private JTextField address;
    private JTextField phoneNumber;
    private JButton finishButton;
    private ActionListener finishButtonListener;

    private static ArrayList<Drinks> drinks = new ArrayList<>();
    private static ArrayList<Desserts> desserts = new ArrayList<>();
    private static ArrayList<Pizza> pizzas = new ArrayList<>();
    private static ArrayList<Toppings> toppings = new ArrayList<>();
    private static Double price;

    public CustomerDetails(){

        JPanel mainPanel = new JPanel();
        JLabel detailsLabel = new JLabel("ENTER YOUR DETAILS");
        Box vbox = Box.createVerticalBox();
        vbox.add(detailsLabel);



        name = new JTextField();
        name.setBorder(new TitledBorder(new EtchedBorder(), "Name"));
        name.setSize(100, 20);
        lastName = new JTextField();
        lastName.setBorder(new TitledBorder(new EtchedBorder(), "Surname"));
        lastName.setSize(100, 20);
        address = new JTextField();
        address.setBorder(new TitledBorder(new EtchedBorder(), "Address"));
        address.setSize(100, 20);
        phoneNumber = new JTextField();
        phoneNumber.setBorder(new TitledBorder(new EtchedBorder(), "Phone Number"));
        phoneNumber.setSize(100,20);


        vbox.add(name);
        vbox.add(lastName);
        vbox.add(address);
        vbox.add(phoneNumber);

        finishButton = new JButton("Order!");
        finishButtonListener = new finishButtonListenerClass();
        finishButton.addActionListener(finishButtonListener);

        vbox.add(finishButton);

        mainPanel.add(vbox);
        add(mainPanel);
    }

    public static void main(String[] args){
        JFrame frame = new CustomerDetails();
        frame.setSize(500, 230);
        frame.setTitle("Customer Details");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void initData(ArrayList<Pizza> selectedPizza, ArrayList<Toppings> selectedToppings, ArrayList<Desserts> selectedDesserts, ArrayList<Drinks> selectedDrinks, Double totalPrice) {
        pizzas = selectedPizza;
        toppings = selectedToppings;
        desserts = selectedDesserts;
        drinks = selectedDrinks;
        price = totalPrice;
    }

    public void checkForExistingCustomer(EntityManager entityManager, CRUDOperationsCustomer operationsCustomer){
        //name exists, so increase number of orders for this customer and update address and number if it changed
        Query custId = entityManager.createNativeQuery("SELECT customer_id FROM customers WHERE customerName =:CustName AND customerSurname =:CustSur");
        custId.setParameter("CustName", name.getText());
        custId.setParameter("CustSur", lastName.getText());

        int id = (int)custId.getSingleResult();

        Query custOrders = entityManager.createNativeQuery("SELECT numberPizzaOrdered from customers where customer_id=:CustId");
        custOrders.setParameter("CustId", id);

        if(((int)custOrders.getSingleResult()) == 10){ //if its the 11th order

            double percent = price*0.1;
            price = price - percent;

            //Reset the number of pizza's ordered
            Query updateNumOfOrders = entityManager.createNativeQuery("UPDATE customers SET numberPizzaOrdered = 1 WHERE customer_id =:CustId");
            updateNumOfOrders.setParameter("CustId", id);
            updateNumOfOrders.executeUpdate();

            Query currentDiscount = entityManager.createNativeQuery("UPDATE customers SET hasCurrentDiscount = true WHERE customer_id =:CustId");
            currentDiscount.setParameter("CustId", id);
            currentDiscount.executeUpdate();
        }
        else{
            Query currentDiscount = entityManager.createNativeQuery("UPDATE customers SET hasCurrentDiscount = false WHERE customer_id =:CustId");
            currentDiscount.setParameter("CustId", id);
            currentDiscount.executeUpdate();
            //increase the amount of orders for the customer
            Query increase = entityManager.createNativeQuery("UPDATE customers SET numberPizzaOrdered = numberPizzaOrdered + 1 WHERE  customer_id=:CustId");
            increase.setParameter("CustId", id);
            increase.executeUpdate();
        }
        //INSERT ORDER
        CRUDOperationsOrder operationsOrder = new CRUDOperationsOrder();
        insertOrder(entityManager, operationsOrder, id);
    }

    public void insertOrder(EntityManager entityManager, CRUDOperationsOrder operationsOrder, int custid){
        CRUDOperationsTopOnPizza operationsTopOnPizza = new CRUDOperationsTopOnPizza();

        //insert order AND get the order id
        operationsOrder.insertOrderEntity(custid); //in the orders table

        //OrderStatus.initData(price, custid);
        OrderStatus.initData(price);
        OrderStatus.main(null);
    }

    private class finishButtonListenerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


            EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            CRUDOperationsCustomer operationsCustomer = new CRUDOperationsCustomer();
           // CRUDOperationsOrder operationsOrder = new CRUDOperationsOrder();

            try {
                checkForExistingCustomer(entityManager, operationsCustomer);


            } catch (NoResultException exception){
                //if name doesn't exists, insert person into the table
                operationsCustomer.insertCustomerEntity(name.getText(), lastName.getText(), address.getText(), phoneNumber.getText());
                OrderStatus.initData(price);
                OrderStatus.main(null);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
}
