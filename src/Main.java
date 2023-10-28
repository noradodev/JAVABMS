import java.util.*;
import java.sql.*;
public class Main {
	
	private static final String DB_URL = "jdbc:mysql://localhost/bams";
	private static final String DB_USER = "root";
	
	private static final String DB_PASSWORD = "Rado@2019";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
		      Scanner scanner = new Scanner(System.in);
		      while(true) {
		        System.out.println("======= W E L C O M E ========");
		        System.out.println("1. Accounts Information");
		        System.out.println("2. Deposit");
		        System.out.println("3. Withdraw");
		        System.out.println("4. Transactions");
			       
		        System.out.println("0. Exit");
		        System.out.println("============ \n");
		        
		        System.out.print("Please choose an option: ");
		        int Choice = scanner.nextInt();
		        
		        switch(Choice) {
		        case 1:
		        	AllAccountsMenu();
		          break;
		        case 2:
		        	depositToAccount.deposit(connection, scanner);
		        	break;

		        case 3:
		        	withdrawFromAccount.withdraw(connection, scanner);
		          break;
		        case 4:
		        	checkUserTransaction.checkTransaction(connection, scanner);
		        	break;
		        case 0:
		          scanner.close();
		          System.exit(0);
		        default:
		          System.out.println("Invalid choice");
		          break;
		        } 
		      }
		    } catch(SQLException e) {
		      e.printStackTrace();
		    }
		  }
	
	public static void AllAccountsMenu() throws SQLException{
		 
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
		      Scanner scanner = new Scanner(System.in);
		      while(true) {

		  		System.out.println("------All Accounts Menu-------");
		  		System.out.println("1. View All Account Information");
		  		System.out.println("2. Create Account");
		  		System.out.println("3. Update Account");
		  		System.out.println("4. Delete Account");
		  		System.out.println("0. Back To Main Menu");
		        
		        System.out.print("Please choose an option: ");
		        int UNi_Choice = scanner.nextInt();
		        
		        switch(UNi_Choice) {
		        case 1:
		        	ViewAccounts.displayInfo();
		          break;
		        case 2:
		        	CreateAccount.createAccount(connection, scanner);
		        	break;
		        case 3:
		        	UpdateAccounts.updateInfo(connection, scanner);	 
			          break;
		        case 4:
		        	DeleteAccount.deleteInfo(connection, scanner);
		        	break;
		        case 0:
		        	return;
		        default:
		          System.out.println("Invalid choice");
		          break;
		        } 
		      }
		    }
		
		
		
		
		
		
		
	}
	
}
