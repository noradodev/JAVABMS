import java.sql.*;
import java.util.*;

public class withdrawFromAccount {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bams", "root", "Rado@2019");
             Scanner scanner = new Scanner(System.in)) {
           withdraw(connection, scanner);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void withdraw(Connection connection, Scanner scan) {
        try {
        	
            System.out.print("Enter Deposit Account Number: ");
            int accountNumber = scan.nextInt();

         

            // Check if the account exists
            String checkSql = "SELECT * FROM accounts WHERE account_number = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setInt(1, accountNumber);
                ResultSet resultSet = checkStatement.executeQuery();
                

                if (resultSet.next()) {
                	String fname = resultSet.getString("account_holder");
                    System.out.print("Hello, "+ fname +"! ");
                	   System.out.print("Enter the withdrawal amount: ");
                       double withrawAmount = scan.nextDouble();
                    double currentBalance = resultSet.getDouble("balance");
    

                    // Update the balance
                    String updateSql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    	if(withrawAmount<=currentBalance) {
                    		updateStatement.setDouble(1, currentBalance - withrawAmount);
                            
                    	}else {
                    		updateStatement.setDouble(1, currentBalance);
                    	}
                    	
                        updateStatement.setInt(2, accountNumber);
                        int rowsUpdated = updateStatement.executeUpdate();
                        if(withrawAmount<=currentBalance) {
                        	if (rowsUpdated > 0) {
                                
                            	System.out.println("withrawn successful.");
                            
                        	} else {
                        		System.out.println("Deposit failed. Please try again.");
                        		}
                        	
                        } else {
                        	System.out.print("withdraw failed. Withdraw Amount sould be Greater or Equal to $1.");
                        }

                        
                    }
                } else {
                    System.out.println("No account with the specified ID found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
