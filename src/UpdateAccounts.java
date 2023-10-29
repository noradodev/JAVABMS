import java.util.*;
import java.sql.*;

public class UpdateAccounts {

    static void updateInfo(Connection connection, Scanner scanner) {
        System.out.print("Enter the account ID you want to edit: ");
        int accountNumber = scanner.nextInt();

        System.out.print("Enter new account holder's name: ");
        String newName = scanner.next();

        String sql = "UPDATE accounts SET account_holder = ? WHERE account_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, accountNumber);
            int rowsUpdated = preparedStatement.executeUpdate();
            
            //Error Handle when user input
            
            if (rowsUpdated > 0) {
            	System.out.println("╔════════════════════════════════════════╗");
            	System.out.println("║   Congrate! Your account is updated.   ║");
            	System.out.println("╚════════════════════════════════════════╝");

            } else {
            	System.out.println("╔════════════════════════════════════════╗");
            	System.out.println("║  No account with the specified ID found.  ║");
            	System.out.println("╚════════════════════════════════════════╝");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
