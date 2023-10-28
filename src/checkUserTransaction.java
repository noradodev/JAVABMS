import java.sql.*;
import java.util.*;

public class checkUserTransaction {
	public static void checkTransaction(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("-----------------");
		System.out.print("Transaction Page");
        System.out.println("-----------------");
        System.out.print("Enter Users Account Number: ");
		int accountNumber =scanner.nextInt();
		//select from table
		String sql = "SELECT * FROM transactions WHERE account_number = ?";	
		try (PreparedStatement checkStatement = connection.prepareStatement(sql)) {
            checkStatement.setInt(1, accountNumber);
            ResultSet resultSet = checkStatement.executeQuery();
            //your transaction table
if(resultSet.next()) {
	 String accNumber = resultSet.getString("account_number");
    System.out.println("------------------------------------------------");
    System.out.println(" Transactions Detail for User Accounts Number: " + accNumber);
    System.out.println("------------------------------------------------");
    
    
    boolean hasTransactions = false;
    while (resultSet.next()) {
        int accTransactionId = resultSet.getInt("transaction_id");
       
        double accTransactionAmount = resultSet.getDouble("amount");
        String transactionType = resultSet.getString("transaction_type");
        System.out.println("------------------------------------------------");
        System.out.println("Transaction ID : " + accTransactionId);
        System.out.println("Transaction Type : " + transactionType);
        System.out.println("------------------------------------------------");
        System.out.println("Transaction Amounts: $" + accTransactionAmount);
        System.out.println("------------------------------------------------");
        System.out.println("");
        
        // Set the hasTransactions flag to true
        hasTransactions = true;
    }

}else{
                System.out.println("------------------------------------------------");
                System.out.println("No transactions found for the specified account number.");
                System.out.println("------------------------------------------------");
            }

        
    } catch (SQLException e) {
        e.printStackTrace();
    };
    
    
    
    
		
	}

}
