


import java.util.*;
import java.sql.*;


public class DeleteAccount {
	 protected static void deleteInfo(Connection connection, Scanner scanner) throws SQLException {
	      System.out.print("Enter the account number you want to delete: ");
	      int accountNumber = scanner.nextInt();

	      String sql = "DELETE FROM accounts WHERE account_number = ?";
	      try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	          preparedStatement.setInt(1, accountNumber);
	          int rowsDeleted = preparedStatement.executeUpdate();
	          if (rowsDeleted > 0) {
	            	System.out.println("╔════════════════════════════════════════╗");
	            	System.out.println("║   Congrate! Your account is delete.   ║");
	            	System.out.println("╚════════════════════════════════════════╝");

	            } else {
	            	System.out.println("╔════════════════════════════════════════╗");
	            	System.out.println("║  No account with the specified ID found.  ║");
	            	System.out.println("╚════════════════════════════════════════╝");

	            }
	      } catch (Exception e) {
			// TODO: handle exception
	    	  e.printStackTrace();
		}
	  }
}
