package com.kryvoruchka;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class EntityToDB {
    public static void ReadAllTable(Session session, String table) throws ClassNotFoundException {
        table = table.substring(0, 1).toUpperCase() + table.substring(1);

        Query query = session.createQuery(new StringBuilder().append("from ").append(table).append("Entity").toString());
        System.out.format("\nTable " + table + " --------------------\n");
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        List<String> columnNames = new ArrayList<>(Arrays.asList(sessionFactory.getClassMetadata(Class.forName("com.kryvoruchka" +
                "." + table + "Entity")).getPropertyNames()));
        String str = sessionFactory.getClassMetadata(Class.forName("com.kryvoruchka." + table + "Entity"))
                .getIdentifierPropertyName();
        columnNames.add(str);

        for (String s : columnNames) {
            System.out.format("%-15s", s);
        }
        System.out.println();

        for (Object obj : query.list()) {
            if (table.equalsIgnoreCase("maker")) {
                MakerEntity c = (MakerEntity) obj;
                if (c.getClothesByMaker().size() != 0) {
                    System.out.format("%-14s %-14s %-14s %-14s %-14s\n", c.getClothesByMaker().iterator().next().getType(), c
                            .getFirstName(), c.getLastName(), c.getPrice(), c.getCompanyName());
                } else {
                    System.out.format("%-14s %-14s %-14s %-14s %-14s\n", "---", c.getFirstName(), c.getLastName(), c
                            .getPrice(), c.getCompanyName());
                }

            } else if (table.equalsIgnoreCase("customer")) {
                CustomerEntity c = (CustomerEntity) obj;
                if (c.getClothes().size() != 0) {
                    System.out.format("%-14s %-14s %-14s %-14s %-14s %-14s\n", c.getAdress(), c.getClothes().iterator()
                            .next().getType(), c.getFirstName(), c.getLastName(), c.getPayment(), c
                            .getPassName());
                } else {
                    System.out.format("%-14s %-14s %-14s %-14s %-14s %-14s\n", c.getAdress(), "---", c.getFirstName(), c
                            .getLastName(), c.getPayment(), c.getPassName());
                }

            } else if (table.equalsIgnoreCase("clothes")) {
                ClothesEntity c = (ClothesEntity) obj;
                if (c.getCustomers().size() != 0) {
                    System.out.format("%-14s %-14s %-14s %-14s %-14s %-14s %-45s\n", c.getAmount(), c.getColor(), c
                            .getCustomers().iterator().next().getFirstName(), c.getFkClothesMaker().getFirstName(), c.getSize(), c
                            .getType(), c.getId());
                } else {
                    System.out.format("%-14s %-14s %-14s %-14s %-14s %-14s %-45s\n", c.getAmount(), c.getColor(),
                            "---", c.getFkClothesMaker().getFirstName(), c.getSize(), c.getType(), c.getId());
                }

            }
        }
    }

    public static void ReadClothesOfCustomer(Session session) {
        Query query = session.createQuery("from " + "CustomerEntity");
        System.out.format("\nTable Customer --------------------\n");
        System.out.format("%-12s %-12s %-12s \n", "Pass", "Name", "Surname");
        for (Object obj : query.list()) {
            CustomerEntity customer = (CustomerEntity) obj;
            System.out.format("%-12d %-12s %-12s->\n", customer.getPassName(), customer.getFirstName(), customer
                    .getLastName());
            for (ClothesEntity clothes : customer.getClothes()) {
                System.out.format("\t\t%s // %s\n", clothes.getColor(), clothes.getType());
            }
        }
    }

    public static void insertCustomer(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a name: ");
        String name = input.next();
        System.out.println("Input a surname: ");
        String surname = input.next();
        System.out.println("Input an address: ");
        String address = input.next();
        System.out.println("Input a pass number: ");
        int pass = Integer.parseInt(input.next());
        System.out.println("Input a payment: ");
        String payment = input.next();

        session.beginTransaction();
        CustomerEntity cityEntity = new CustomerEntity(name, surname, address, pass, payment);
        session.save(cityEntity);
        session.getTransaction().commit();

        System.out.println("End insert customer");
    }

    public static void insertMaker(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input company name: ");
        String company = input.nextLine();
        System.out.println("Input name: ");
        String name = input.next();
        System.out.println("Input surname: ");
        String surname = input.next();
        System.out.println("Input price: ");
        int price = Integer.parseInt(input.next());

        session.beginTransaction();
        MakerEntity maker = new MakerEntity(company, name, surname, price);
        session.save(maker);
        session.getTransaction().commit();
        System.out.println("End insert maker");
    }

    public static void insertClothes(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input type: ");
        String type = input.next();
        System.out.println("Input amount: ");
        int amount = Integer.parseInt(input.next());
        System.out.println("Input color: ");
        String color = input.next();
        System.out.println("Input size: ");
        String size = input.next();
        System.out.println("Input company name: ");
        String company = input.next();

        session.beginTransaction();
        Query query = session.createQuery(new StringBuilder().append("from MakerEntity where companyName='")
                .append(company).append("'").toString());
        for (Object obj : query.list()) {
            MakerEntity maker = (MakerEntity) obj;
            ClothesEntity clothes = new ClothesEntity(type, amount, color, size, maker);
            session.save(clothes);
        }
        session.getTransaction().commit();
        System.out.println("End insert clothes");
    }

    public static void updateCustomerName(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nInput old customer name: ");
        String name = input.nextLine();
        System.out.println(name);
        System.out.println("Input new customer name: ");
        String newName = input.next();
        int pass = 0;
        Query query = session.createQuery(new StringBuilder().append("from CustomerEntity where firstName='")
                .append(name).append("'").toString());
        for (Object obj : query.list()) {
            CustomerEntity c = (CustomerEntity) obj;
            pass = c.getPassName();
        }

        CustomerEntity customerEntity = (CustomerEntity) session.load(CustomerEntity.class, pass);
        if (customerEntity != null) {
            session.beginTransaction();
            Query new_query = session.createQuery("update CustomerEntity set firstName=:code1  where firstName = " +
                    ":code2");
            new_query.setParameter("code1", newName);
            new_query.setParameter("code2", name);
            int result = new_query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("End update customer name: " + result);
        } else System.out.println("There is no customer with this name");
    }

    public static void AddClothesForCustomer(Session session) {
        System.out.println("Give a clothes to customer --------------");
        Scanner input = new Scanner(System.in);
        System.out.println("Choose customer name:");
        String name = input.next();
        System.out.println("Choose clothes type:");
        String typeCode = input.next();
        System.out.println("Choose clothes color:");
        String colorCode = input.next();

        Query query = session.createQuery("from " + "CustomerEntity where firstName = :code");
        query.setParameter("code", name);

        if (!query.list().isEmpty()) {
            CustomerEntity customerEntity = (CustomerEntity) query.list().get(0);
            query = session
                    .createQuery("from " + "ClothesEntity where type = :type_code and color = :color_code");
            query.setParameter("type_code", typeCode);
            query.setParameter("color_code", colorCode);
            if (!query.list().isEmpty()) {
                ClothesEntity clothesEntity = (ClothesEntity) query.list().get(0);
                session.beginTransaction();
                customerEntity.addClothesEntity(clothesEntity);
                session.save(customerEntity);
                session.getTransaction().commit();
                System.out.println("End insert clothes for customer");
            } else {
                System.out.println("There is no clothes with this type");
            }
        } else {
            System.out.println("There is no customer with this name");
        }

    }

    public static void deleteCustomerByName(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nInput customer name: ");
        String name = input.next();
        int pass = 0;
        Query new_query = session.createQuery(new StringBuilder().append("from CustomerEntity where firstName='")
                .append(name).append("'").toString());
        for (Object obj : new_query.list()) {
            CustomerEntity c = (CustomerEntity) obj;
            pass = c.getPassName();
        }
        CustomerEntity customerEntity = (CustomerEntity) session.load(CustomerEntity.class, pass);
        if (customerEntity != null) {
            session.beginTransaction();
            Query query = session.createQuery("delete CustomerEntity where firstName=:code");
            query.setParameter("code", name);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("End deleting customer by name: " + result);
        } else System.out.println("There is no customer with this name");
    }
}
