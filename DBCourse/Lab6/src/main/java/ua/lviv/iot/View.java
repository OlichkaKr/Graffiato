package ua.lviv.iot;

import java.sql.*;
import java.util.*;

import static ua.lviv.iot.OnlineShop.*;

public class View {
    private Map<String, String> menu;
    private static Scanner input = new Scanner(System.in, "UTF-8");

    private static final String url = "jdbc:mysql://localhost:3306/onlineshop?serverTimezone=UTC&useSSL=false";
    private static final String user = "root";
    private static final String password = "olichka121";


    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet rs = null;

    View() {
        menu = new LinkedHashMap<>();
        menu.put("1", "  1 - Read Data From A Table");
        menu.put("2", "  2 - Read Join Data From CustomerClothes");
        menu.put("3", "  3 - Update Customer Name");
        menu.put("4", "  4 - Insert New Maker");
        menu.put("5", "  5 - Delete Customer By Name");
        menu.put("E", "  E - exit");
    }

    private void outputMenu() {
        System.out.print("\nMENU:\n");
        for (String str : menu.values()) {
            System.out.print(str + "\n");
        }
    }

    private void manager(final String num) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            switch (num) {
                case "1": {
                    System.out.println("Input a table name: ");
                    String table = input.nextLine();
                    readData(table, statement);
                    break;
                }
                case "2": {
                    readJoinData(statement);
                    break;
                }
                case "3": {
                    updateCustomerName(statement);
                    break;
                }
                case "4": {
                    insertDataMaker(connection);
                    break;
                }
                case "5": {
                    deleteCustomerName(connection);
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
            System.out.println("MySQL Driver is not loaded");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } finally {
            //close connection ,statement and resultset
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            } // ignore
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
            }
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