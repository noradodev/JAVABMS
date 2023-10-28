import java.sql.*;
import java.util.*;

public class checkUserTransaction {
	public static void checkTransaction(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("-----------------");
		System.out.print("Transaction Page");
        System.out.println("-----------------");
        System.out.print("Enter Users ID: ");
		int accountNumber =scanner.nextInt();
		//select from table
		String sql = "SELECT * FROM transactions WHERE account_number = ?";	
		try (PreparedStatement checkStatement = connection.prepareStatement(sql)) {
            checkStatement.setInt(1, accountNumber);
            ResultSet resultSet = checkStatement.executeQuery();
            //your transaction table
            if (resultSet.next()) {
            	//modify this for information from transaction table
            	int accTransactionId = resultSet.getInt("transaction_id");
            	String accNumber= resultSet.getString("account_number");
            	double accTransactionAmount= resultSet.getDouble("amount");
            	String transactionType = resultSet.getString("transaction_type");
            	System.out.println("");
            	System.out.println("");
            	System.out.println("");
            	System.out.println("");
                System.out.println("------------------------------------------------");
                System.out.println("Transaction Detailed for User ID: "+ accNumber);
                System.out.println("------------------------------------------------");
                System.out.println("Transaction Id : "+ accTransactionId);
                System.out.println("Transaction Type : "+ transactionType);
                System.out.println("------------------------------------------------");
                System.out.println("Transaction Amounts: $"+ accTransactionAmount);
                System.out.println("------------------------------------------------");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("");
         
               
            } else {
            	System.out.println("------------------------------------------------");
                System.out.println("No account with the specified ID found.");
                System.out.println("------------------------------------------------");
            }
        
    } catch (SQLException e) {
        e.printStackTrace();
    };
    
    
    
    
		
	}

}
