import java.sql.*;
import java.util.*;
import java.time.*;


public class CreateAccount {
	protected static void createAccount(Connection connection, Scanner scanner) throws SQLException {
		int accountNumber = RandomAccountNum();
	    System.out.print(" Enter account holder name: ");
	    String fname = scanner.next();
	    String lname = scanner.next();
	    System.out.print(fname+' ' + lname);

	    System.out.print(" Enter balance: ");
	    int balance = scanner.nextInt();
	    
	    LocalDateTime create_date = LocalDateTime.now();
	    
	    Timestamp create_date_timestamp = Timestamp.valueOf(create_date);
	    
	    String sql = "INSERT INTO accounts (account_number, account_holder, balance, open_date) VALUES (?,?,?, ?)";
	    try(PreparedStatement preparedconnection = connection.prepareStatement(sql)){
	      preparedconnection.setInt(1, accountNumber);
	      preparedconnection.setString(2, fname+ " "+ lname);
	      preparedconnection.setInt(3, balance);
	      preparedconnection.setTimestamp(4, create_date_timestamp);
	      int rowsinset = preparedconnection.executeUpdate();
	      if (rowsinset > 0) {
	        System.out.println("New account has been successfully created");
	      } else{
	        System.out.println("Failed");
	      }
	    }
	  }
	
	private static int RandomAccountNum() {
		 // create Random object
        Random random = new Random();
        // generate random number from 0 to 3
        int number = random.nextInt(999999999)+100000000;
		return number;
		
		
	}

}
