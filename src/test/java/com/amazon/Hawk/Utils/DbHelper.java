package com.amazon.Hawk.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {
	Connection connection;
	
	public DbHelper(Connection connection) {
		this.connection = connection;
	}
	
	public void runQuery(String query, Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }
    
    public boolean areDuplicatesPresent() {
    	String query = "SELECT OrderID, COUNT(*) AS DuplicateCount FROM Orders GROUP BY OrderID, CustomerID, ProductID, OrderDate, ShipDate HAVING DuplicateCount > 1";


        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Check if there are any duplicates
            if (resultSet.next()) {
                int duplicateCount = resultSet.getInt("DuplicateCount");

                if (duplicateCount > 0) {
                    System.out.println("Duplicates found in Orders table: " + duplicateCount);
                    return true; // Duplicates found
                } else {
                    System.out.println("No duplicates found in Orders table.");
                    return false; // No duplicates
                }
            } else {
                System.out.println("No duplicates found in Orders table.");
                return false; // No data
            }

        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
            return false; // Error occurred
        }
    }
    
    public String getMostSoldProduct() {
        String query = "SELECT p.ProductID, p.ProductName, SUM(o.Quantity) AS TotalQuantitySold FROM Orders o JOIN Products p ON o.ProductID = p.ProductID GROUP BY p.ProductID, p.ProductName ORDER BY TotalQuantitySold DESC LIMIT 1";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Check if there are any results
            if (resultSet.next()) {
                int mostSoldProductID = resultSet.getInt("ProductID");
                String mostSoldProductName = resultSet.getString("ProductName");
                int totalQuantitySold = resultSet.getInt("TotalQuantitySold");

                System.out.println("Most sold product ID: " + mostSoldProductID);
                System.out.println("Most sold product name: " + mostSoldProductName);
                System.out.println("Total quantity sold: " + totalQuantitySold);

                // You can return the most sold product name or perform additional actions as needed
                return mostSoldProductName;
            } else {
                System.out.println("No data retrieved from the query.");
                return "No data retrieved from the query.";
            }

        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
            return "Error executing query: " + e.getMessage();
        }
    }

    public double getMaxPriceProduct() {
        String query = "SELECT ProductID, ProductName, Price FROM Products ORDER BY Price DESC LIMIT 1";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Check if there are any results
            if (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                double price = resultSet.getDouble("Price");

                System.out.println("Product with maximum price:");
                System.out.println("Product ID: " + productID);
                System.out.println("Product Name: " + productName);
                System.out.println("Price: " + price);

                // You can return the information or perform additional actions as needed
                return price;
            } else {
                System.out.println("No data retrieved from the query.");
                return -1; // Return a default value or handle the absence of data accordingly
            }

        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
            return -1; // Return a default value or handle the error accordingly
        }
    }
}
