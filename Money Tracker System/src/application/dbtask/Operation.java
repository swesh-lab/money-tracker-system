package application.dbtask;

import java.sql.*;
import javax.swing.JOptionPane;

/**
*
* @author SwetaSharma, HemantTiwari
* 
* Money Tracker System
* 			It allows the user to view daily/monthly review of money spent and can analyze his/her expense.
* 
* Launch the application.
*/

public class Operation {
	private static Connection con;
	public static Connection createConnection(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moneytracker", "root", "siren");
		}
		
		catch(ClassNotFoundException|SQLException ex){
			JOptionPane.showMessageDialog(null, "Money Tracker Database Access Error!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return con;
	}
	
	public static void main(String[] args) {
		Connection c = createConnection();
//		System.out.println(c);
	}
}
