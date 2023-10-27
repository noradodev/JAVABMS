import java.sql.*;
import java.util.*;

public class ViewAccounts {
	
	private static final String DB_URL = "jdbc:mysql://localhost/bams";
	private static final String DB_USER = "root";
	
	private static final String DB_PASSWORD = "Rado@2019";
	
	
	public static void displayInfo() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts");

            System.out.println("Account Info");
            System.out.println("-------------------");
            while (resultSet.next()) {
                int accountNumber = resultSet.getInt("account_number");
                String name = resultSet.getString("account_holder");
                int balance = resultSet.getInt("balance");

                System.out.println("Account number: " + accountNumber);
                System.out.println("Account Holder's Name: " + name);
                System.out.println("Balance: $" + balance );
                System.out.println("----------------");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
