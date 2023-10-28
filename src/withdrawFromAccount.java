import java.sql.*;
import java.time.LocalDateTime;
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
                    //create withraw amount
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
                        		
                        		  String transactionType = "Withraw";
                        		  LocalDateTime create_date = LocalDateTime.now();
                        		    int transactionID = RandomTransactionNum();
                        		   Timestamp transaction_date = Timestamp.valueOf(create_date);
                        		   
                        		   
                                String insertSqlAmount = "INSERT transactions (transaction_id, account_number, transaction_type, amount, transaction_date) VALUES (?,?,?,?,?)";
                                try(PreparedStatement insertSqlAmountWithraw = connection.prepareStatement(insertSqlAmount)) {
                                	insertSqlAmountWithraw.setInt(1, transactionID);
                                	insertSqlAmountWithraw.setInt(2, accountNumber);
                                	insertSqlAmountWithraw.setString(3, transactionType);
                                	insertSqlAmountWithraw.setDouble(4, withrawAmount);
                                	insertSqlAmountWithraw.setTimestamp(5, transaction_date);
                                	int rowsinset = insertSqlAmountWithraw.executeUpdate();
                                	
                          	      if (rowsinset > 0) {
                          	        System.out.println("added new transaction to table");
                          	      } else{
                          	        System.out.println("Failed");
                          	      }
                                
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
                            	System.out.println("Withraw successful.");
                            
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
    
    private static int RandomTransactionNum() {
		 // create Random object
       Random random = new Random();
       // generate random number from 0 to 3
       int number = random.nextInt(99999)+1000000;
		return number;
		
		
	}
}
