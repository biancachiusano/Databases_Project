import entity.Desserts;
import entity.Drinks;
import entity.Pizza;
import entity.Toppings;

import javax.persistence.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Menu extends JFrame{


    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction transaction;

    private ButtonGroup pizzaTypesRB;

    JCheckBox chinotto;
    JCheckBox moretti;
    JCheckBox icnusa;
    JCheckBox nebbiolo;

    String[] toppings = {"Burrata", "Tomato", "Olives", "Tuna", "Mushrooms", "Parma Ham", "Pepperoni", "Sausage", "Anchovy", "Mozzarella"};
    JCheckBox[] toppingsBoxes = new JCheckBox[toppings.length]; //  Each checkbox will get a name of food from food array.

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private String standard = "Standard";
    private String glutenFree = "Gluten Free";

    private JLabel calcPrice; // based on type, topp, drinks, desserts
    private JLabel isVegetarian; //based on the toppings --> DB

    private JButton nextPage;

    private ActionListener rbListener;
    private ItemListener drinkListener;

    private Double totalPrice = 0.0;

    private boolean veg = true;
    private ArrayList<Boolean> vegTracking = new ArrayList<>();
    private ArrayList<Drinks> selectedDrinks = new ArrayList<>();
    private ArrayList<Desserts> selectedDesserts = new ArrayList<>();
    private ArrayList<Pizza> selectedPizza = new ArrayList<>();
    private ArrayList<Toppings> selectedToppings = new ArrayList<>();

    public Menu(){

        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        nextPage = new JButton("Continue");
        nextPage.setEnabled(false);
        //PIZZA
        JPanel pizzaPanel = new JPanel();
        pizzaPanel.setBorder(new TitledBorder(new EtchedBorder(), "Pizza Type"));

        JRadioButton standardPizzaType = new JRadioButton(standard);
        //standardPizzaType.setActionCommand(standard);
        standardPizzaType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
                entityManager.getTransaction().begin();

                Pizza p1 = new Pizza();
                Pizza p2 = new Pizza();

                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(selectedPizza.size() == 0)
                        selectedPizza.add(p1);
                    else {
                        selectedPizza.add(p1);
                        selectedPizza.remove(p2);
                    }

                    nextPage.setEnabled(true);

                    if(totalPrice != 0){
                        p2 = entityManager.find(Pizza.class, 2);
                        totalPrice = totalPrice - p2.getPizzaPrice();
                    }
                    p1 = entityManager.find(Pizza.class, 1);
                    totalPrice = totalPrice + p1.getPizzaPrice();
                }

                calcPrice.setText("Total Price: " + df2.format(totalPrice));
                entityManager.getTransaction().commit();
                entityManager.close();
            }
        });

        JRadioButton glutenFreePizzaType = new JRadioButton(glutenFree);
        glutenFreePizzaType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
                entityManager.getTransaction().begin();

                Pizza p1 = new Pizza();
                Pizza p2 = new Pizza();

                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(selectedPizza.size() == 0)
                        selectedPizza.add(p2);
                    else {
                        selectedPizza.add(p2);
                        selectedPizza.remove(p1);
                    }

                    nextPage.setEnabled(true);
                    if(totalPrice != 0){
                        p1 = entityManager.find(Pizza.class, 1);
                        totalPrice = totalPrice - p1.getPizzaPrice();
                    }
                    p2 = entityManager.find(Pizza.class, 2);
                    totalPrice = totalPrice + p2.getPizzaPrice();
                }

                calcPrice.setText("Total Price: " + df2.format(totalPrice));
                entityManager.getTransaction().commit();
                entityManager.close();
            }
        });


        pizzaTypesRB = new ButtonGroup();
        pizzaTypesRB.add(standardPizzaType);
        pizzaTypesRB.add(glutenFreePizzaType);

        pizzaPanel.add(standardPizzaType);
        pizzaPanel.add(glutenFreePizzaType);

        //TOPPINGS
        JPanel toppingsPanel = new JPanel(); //if there are a lot of toppings use vboxes + hboxes
        toppingsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Toppings"));

        for(int i = 0; i < toppingsBoxes.length; i++) {
            toppingsBoxes[i] = new JCheckBox(toppings[i]);
            int finalI = i;
            toppingsBoxes[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    evaluateToppingsPrice(toppingsBoxes[finalI], finalI +1);
                }
            });
            toppingsPanel.add(toppingsBoxes[i]);
        }

        //DESSERTS
        JPanel dessertsPanel = new JPanel();
        dessertsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Desserts"));

        JCheckBox crostata = new JCheckBox("Crostata");
        crostata.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                evaluateDessertsPrice(crostata, 1);
            }
        });
        JCheckBox tiramisu = new JCheckBox("Tiramisù");
        tiramisu.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                evaluateDessertsPrice(tiramisu, 2);
            }
        });
        JCheckBox meringa = new JCheckBox("Meringa");
        meringa.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                evaluateDessertsPrice(meringa, 3);
            }
        });

        dessertsPanel.add(crostata);
        dessertsPanel.add(tiramisu);
        dessertsPanel.add(meringa);

        //DRINKS
        JPanel drinksPanel = new JPanel();
        drinksPanel.setBorder(new TitledBorder(new EtchedBorder(), "Drinks"));

        chinotto = new JCheckBox("Chinotto");
        chinotto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               evaluateDrinksPrice(chinotto, 1);
            }
        });
        drinksPanel.add(chinotto);

        moretti = new JCheckBox("Moretti");
        moretti.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                evaluateDrinksPrice(moretti, 2);
            }
        });
        drinksPanel.add(moretti);

        icnusa = new JCheckBox("Icnusa");
        icnusa.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                evaluateDrinksPrice(icnusa, 3);
            }
        });
        drinksPanel.add(icnusa);

        nebbiolo = new JCheckBox("Nebbiolo");
        nebbiolo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                evaluateDrinksPrice(nebbiolo, 4);
            }
        });
        drinksPanel.add(nebbiolo);

        //VEGETARIAN
        isVegetarian = new JLabel("Vegetarian: " + veg);
        //PRICE
        calcPrice = new JLabel("Total Price: ");
        //NEXT
        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Pass the pizza and drinks and stuff that were selected
                CustomerDetails.initData(selectedPizza, selectedToppings, selectedDesserts, selectedDrinks, totalPrice);
                CustomerDetails.main(null);
                setVisible(false);
            }
        });

        Box hbox = Box.createHorizontalBox();
        hbox.add(isVegetarian);
        hbox.add(Box.createHorizontalStrut(30));
        hbox.add(calcPrice);
        hbox.add(Box.createHorizontalStrut(30));
        hbox.add(nextPage);

        Box vbox = Box.createVerticalBox();
        vbox.add(pizzaPanel);
        vbox.add(toppingsPanel);
        vbox.add(Box.createVerticalStrut(10));
        vbox.add(dessertsPanel);
        vbox.add(drinksPanel);
        vbox.add(hbox);

        mainPanel.add(vbox);

        add(mainPanel);
    }


    public static void main(String[] ags){
        JFrame loadMenu = new Menu();
        loadMenu.setTitle("Main Menu");
        loadMenu.setSize(500, 400);
        loadMenu.setLocationRelativeTo(null);
        loadMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadMenu.setVisible(true);
    }


    public void evaluateToppingsPrice(JCheckBox cb, int pk){

        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Toppings t = entityManager.find(Toppings.class, pk);

        if(cb.isSelected()) {
            selectedToppings.add(t);
            totalPrice = totalPrice + t.getToppingPrice();
            if(t.getIsVegetarian() == 0) {
                veg = false;
                vegTracking.add(false);
            }
        }
        else {
            selectedToppings.remove(t);
            if (totalPrice != 0)
                totalPrice = totalPrice - t.getToppingPrice();
            if(t.getIsVegetarian() == 0) {
                vegTracking.remove(vegTracking.size() - 1);
            }
        }
        if(vegTracking.size() == 0)
            veg = true;

        calcPrice.setText("Total Price: " + df2.format(totalPrice));
        isVegetarian.setText("Vegetarian: " + veg);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public void evaluateDrinksPrice(JCheckBox cb, int pk){

        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Drinks d = entityManager.find(Drinks.class, pk);


        if(cb.isSelected()) {
            selectedDrinks.add(d);
            totalPrice = totalPrice + d.getDrinkPrice();
        }
        else {
            selectedDrinks.remove(d);
            if (totalPrice != 0)
                totalPrice = totalPrice - d.getDrinkPrice();
        }
        calcPrice.setText("Total Price: " + df2.format(totalPrice));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void evaluateDessertsPrice(JCheckBox cb, int pk){

        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Desserts d = entityManager.find(Desserts.class, pk);

        if(cb.isSelected()) {
            selectedDesserts.add(d);
            totalPrice = totalPrice + d.getDessertPrice();
        }
        else {
            selectedDesserts.remove(d);
            if (totalPrice != 0)
                totalPrice = totalPrice - d.getDessertPrice();
        }
        calcPrice.setText("Total Price: " + df2.format(totalPrice));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
