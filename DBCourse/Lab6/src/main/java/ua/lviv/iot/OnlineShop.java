package ua.lviv.iot;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


class OnlineShop {

    private static ArrayList<String> column = new ArrayList<>();
    private static ArrayList<String> type = new ArrayList<>();

    static void readData(String table, Statement statement) throws SQLException {
        table = table.substring(0, 1).toUpperCase() + table.substring(1);
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM " + table);
        while (rs.next()) {
            int count = rs.getInt(1);
            System.out.format("\ncount: %d\n", count);
        }

        rs = statement.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int i = 1; i <= columnCount; i++ ) {
            column.add(rsmd.getColumnName(i));
            type.add(rsmd.getColumnTypeName(i));
        }

        System.out.format("\nTable " + table + " --------------------------------------------\n");
        for (String aColumn : column) {
            System.out.format("%-15s", aColumn);
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 0; i < column.size(); i++){
                if (type.get(i).equalsIgnoreCase("varchar")) {
                    System.out.format("%-15s", rs.getString(column.get(i)));
                }
                else System.out.format("%-15d", rs.getInt(column.get(i)));
            }
            System.out.println();
        }
        column.clear();
        type.clear();
    }

    static void readJoinData(Statement statement) throws SQLException{
        String query = "SELECT " +
                "(SELECT concat(first_name, ' ', last_name) FROM customer " +
                "WHERE pass_name=C.customer_pass_name) AS Name, " +
                "(SELECT concat(color, ' ', type) FROM clothes WHERE id=C.clothes_id) AS Clothes " +
                "FROM clothes_has_customer AS C";
        ResultSet rs = statement.executeQuery(query);

        System.out.format("\nJoining Table CustomerClothes ----\n");
        System.out.format("%-20s %-10s\n", "Name", "Clothes");
        while (rs.next()) {
            String name = rs.getString("Name");
            String clothes = rs.getString("Clothes");
            System.out.format("%-20s %-10s\n", name, clothes);
        }
    }

    static void updateCustomerName(Statement statement) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input customer name what you want to update: ");
        String name = input.next();
        System.out.println("Input new customer name for %s: " + name);
        String namenew = input.next();

        statement.execute("UPDATE customer SET first_name='" + namenew + "' " +
                "WHERE first_name='" + name + "';");
    }

    static void insertDataMaker(Connection connection) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new company name: ");
        String c_name = input.next();
        System.out.println("Input the owners first name: ");
        String f_name = input.next();
        System.out.println("Input the owners last name: ");
        String l_name = input.next();
        System.out.println("Input a product price: ");
        String price = input.next();

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT maker VALUES (?,?,?,?)");
        preparedStatement.setString(1, c_name);
        preparedStatement.setString(2, f_name);
        preparedStatement.setString(3, l_name);
        preparedStatement.setInt(4, Integer.parseInt(price));
        int n = preparedStatement.executeUpdate();
        System.out.println("Count rows that inserted: " + n);

    }

    static void deleteCustomerName(Connection connection) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a customer name for delete: ");
        String name = input.next();

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE first_name=?");
        preparedStatement.setString(1, name);
        int n = preparedStatement.executeUpdate();
        System.out.println("Count rows that deleted: " + n);
    }
}
