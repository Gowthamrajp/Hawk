package com.amazon.Hawk.TestSuites;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.amazon.Hawk.Base.BaseTest;
import com.amazon.Hawk.Utils.DbHelper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbTestSuite extends BaseTest{

    private static Connection connection;
    private static DbHelper dbHelper;

    @SuppressWarnings("deprecation")
	@BeforeClass
    public void setUp() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        // Set up the database connection
        String url = "jdbc:mysql://localhost:3306/sys?useSSL=true";
        String driver ="com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "Adarsh@1410";
        
        Class.forName(driver).newInstance();
        
        connection = DriverManager.getConnection(url, username, password);
        DbTestSuite.dbHelper = new DbHelper(connection);
    }

    @Test(enabled = false)
    public void createTables() throws SQLException {
        // Create Order Table
    	
        dbHelper.runQuery("CREATE TABLE OrderTable (orderId INT PRIMARY KEY, productName VARCHAR(255), quantity INT)", connection);

        // Create Customer Table
        dbHelper.runQuery("CREATE TABLE CustomerTable (customerId INT PRIMARY KEY, customerName VARCHAR(255), region VARCHAR(255))", connection);

        // Create Sales Table
        dbHelper.runQuery("CREATE TABLE SalesTable (saleId INT PRIMARY KEY, orderId INT, customerId INT, price DECIMAL(10, 2), "
                + "FOREIGN KEY (orderId) REFERENCES OrderTable(orderId), "
                + "FOREIGN KEY (customerId) REFERENCES CustomerTable(customerId))", connection);
    }

    @Test(enabled = false)
    public void insertData() throws SQLException {
        // Inserting data into OrderTable and verifying
    	dbHelper.runQuery("INSERT INTO Orders (OrderID, CustomerID, ProductID, OrderDate, ShipDate, Quantity, TotalAmount, ShippingAddress, OrderStatus) VALUES (1, 1, 1, '2023-01-10', '2023-01-15', 2, 13998.99, '123 Sunshine Lane, Mumbai', 'Delivered')", connection);
    	
        // Inserting data into CustomerTable and verifying
    	dbHelper.runQuery("INSERT INTO Customers (CustomerID, FirstName, LastName, Email, PhoneNumber, Address, City, State, ZipCode, Country) VALUES (1, 'John', 'Doe', 'john.doe@email.com', '555-1234', '123 Main St', 'City1', 'State1', '12345', 'Country1')", connection);

        // Inserting data into ProductTable and verifying
    	dbHelper.runQuery("INSERT INTO Products (ProductID, ProductName, Category, Description, Price, StockQuantity) VALUES (1, 'Smartphone', 'Electronics', 'High-end smartphone with AI camera', 29999.99, 50)", connection);
        
    }

    @Test
    public void findDuplicates() throws SQLException {
    	assertFalse(dbHelper.areDuplicatesPresent(),"Duplicate records are seen");
    }

    @Test
    public void findMaxPriceProduct() throws SQLException {
    	assertEquals(dbHelper.getMaxPriceProduct(), 79999.99); 
    }

    @Test
    public void verifyMostSoldProduct() throws SQLException {
        assertEquals(dbHelper.getMostSoldProduct(), "Smart Watch"); 
    }

    @AfterClass
    public void tearDown() throws SQLException {
        // Close the database connection
        if (connection != null) {
            connection.close();
        }
    }

    
}



