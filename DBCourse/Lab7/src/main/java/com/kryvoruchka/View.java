package com.kryvoruchka;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class View {
    private Map<String, String> menu;
    private static Scanner input = new Scanner(System.in, "UTF-8");
    private static final SessionFactory sessionFactory;

    public View() {
        menu = new LinkedHashMap<>();
        menu.put("1", "  1 - Read Data From A Table");
        menu.put("2", "  2 - Read Join Data From CustomerClothes");
        menu.put("3", "  3 - Update Customer Name");
        menu.put("4", "  4 - Insert To Table");
        menu.put("5", "  5 - Insert Clothes For Customer");
        menu.put("6", "  6 - Delete Customer By Name");
        menu.put("E", "  E - exit");
    }

    private void outputMenu() {
        System.out.print("\nMENU:\n");
        for (String str : menu.values()) {
            System.out.print(str + "\n");
        }
    }

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    private void manager(final String num) {
        try (Session session = getSession()) {


            switch (num) {
                case "1": {
                    System.out.println("Input a table name: ");
                    String table = input.nextLine();
                    EntityToDB.ReadAllTable(session, table);
                    break;
                }
                case "2": {
                    EntityToDB.ReadClothesOfCustomer(session);
                    break;
                }
                case "3": {
                    EntityToDB.updateCustomerName(session);
                    break;
                }
                case "4": {
                    System.out.println("Input a table name: ");
                    String table = input.nextLine();
                    if (table.equalsIgnoreCase("clothes")) {
                        EntityToDB.insertClothes(session);
                    } else if (table.equalsIgnoreCase("maker")) {
                        EntityToDB.insertMaker(session);
                    } else if (table.equalsIgnoreCase("customer")){
                        EntityToDB.insertCustomer(session);
                    }
                    else {
                        System.out.println("Error, there is no table with this name");
                    }
                    break;
                }
                case "5": {
                    EntityToDB.AddClothesForCustomer(session);
                    break;
                }
                case "6": {
                    EntityToDB.deleteCustomerByName(session);
                    break;
                }
                case "E": {
                    System.out.println("  Goodbye!!!");
                    return;
                }
                default: {
                    System.out.println("Error! Menu has not this point");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid class name");
        }
    }

    public final void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point");
            keyMenu = input.nextLine().toUpperCase();
            manager(keyMenu);
            do {
                System.out.println("\n  M - return menu\n  E - exit");
                keyMenu = input.nextLine().toUpperCase();
                if (keyMenu.equalsIgnoreCase("E")) {
                    manager(keyMenu);
                    return;
                }
            } while (!keyMenu.equalsIgnoreCase("M"));

        } while (!keyMenu.equalsIgnoreCase("E"));
    }
}