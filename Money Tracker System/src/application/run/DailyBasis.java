package application.run;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.sql.*;
import application.run.Dashboard;
import application.beans.BalanceSheet;
import application.dbtask.Operation;
import javax.swing.JTextField;
import com.toedter.calendar.demo.DateChooserPanel;
import com.toedter.calendar.JDateChooser;

public class DailyBasis extends JInternalFrame implements ActionListener{

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private JDateChooser btndate;
		
	Dashboard dashboard = new Dashboard();
	int id = Dashboard.userIDonDashboard;
	
	BalanceSheet balanceSheet;
	private JTable table;
	DefaultTableModel model=new DefaultTableModel();
	String[] cols = new String[] {
			"Transaction Date", "Time", "Expense Amount", "Expense ID", "Note", "Deposit Amount", "Deposit ID", "Balance Amount"
	};
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DailyBasis frame = new DailyBasis();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DailyBasis() {
		Image icon = new ImageIcon(DailyBasis.class.getResource("/application/image/rupee.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon frameIcon = new ImageIcon(icon); 
		setFrameIcon(frameIcon);
		getContentPane().setBackground(new Color(218, 165, 32));
		getContentPane().setLayout(null);
		setResizable(false);
		
		con = Operation.createConnection();	
		dailyBasisFrame();
	}

	public void dailyBasisFrame() {
		setTitle("View Statement - Daily Basis");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 1533, 700);
		
		// block InternalFrame to shift from NorthBar
		BasicInternalFrameUI internal = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
		for (MouseListener listener:internal.getNorthPane().getMouseListeners()) {
				internal.getNorthPane().removeMouseListener(listener);
							
		}
		
		JLabel lbldate = new JLabel("Select Date");
		lbldate.setFont(new Font("Open Sans", Font.BOLD, 14));
		lbldate.setBounds(1326, 216, 92, 20);
		getContentPane().add(lbldate);
		
		btndate = new JDateChooser();
		btndate.setForeground(Color.WHITE);
		btndate.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btndate.setBackground(Color.BLACK);
		btndate.setBounds(1326, 239, 171, 29);
		getContentPane().add(btndate);
		
		JLabel lblViewExpenseCategory = new JLabel("View Transaction On Daily Basis");
		lblViewExpenseCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewExpenseCategory.setForeground(Color.BLACK);
		lblViewExpenseCategory.setFont(new Font("Neon Lights", Font.PLAIN, 40));
		lblViewExpenseCategory.setBounds(25, 63, 700, 53);
		getContentPane().add(lblViewExpenseCategory);
		
		JButton btnrefresh = new JButton("Refresh");
		btnrefresh.setForeground(Color.WHITE);
		btnrefresh.setFont(new Font("Open Sans", Font.PLAIN, 15));
		btnrefresh.setBackground(Color.BLACK);
		btnrefresh.setBounds(1326, 170, 128, 29);
		getContentPane().add(btnrefresh);
		btnrefresh.addActionListener(this);
		
		JLabel lblicon = new JLabel("");
		Image refresh = new ImageIcon(DailyBasis.class.getResource("/application/image/refresh.jpg")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon refreshIcon = new ImageIcon(refresh);
		lblicon.setIcon(refreshIcon);
		lblicon.setBounds(1464, 170, 45, 29);
		getContentPane().add(lblicon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 170, 1260, 463);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(23);
		table.setSelectionForeground(new Color(65, 105, 225));
		table.setSelectionBackground(new Color(245, 245, 245));
		table.setForeground(Color.DARK_GRAY);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBackground(new Color(176, 224, 230));
		table.setFillsViewportHeight(true);
		
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Open Sans", Font.PLAIN, 16));
		
		model.setColumnIdentifiers(cols);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JLabel lblsubtitle = new JLabel("<html><b>The whole picture in one place</b>- One report to give a clear view on your daily spending patterns. Understand where your money comes and goes.</html>");
		lblsubtitle.setFont(new Font("Rondalo", Font.PLAIN, 22));
		lblsubtitle.setBounds(40, 110, 1288, 29);
		getContentPane().add(lblsubtitle);
		
		JLabel lblbottomgif = new JLabel("");
		lblbottomgif.setHorizontalAlignment(SwingConstants.CENTER);
		lblbottomgif.setIcon(new ImageIcon(DailyBasis.class.getResource("/application/image/uidback.gif")));
		lblbottomgif.setBounds(0, 344, 375, 327);
		getContentPane().add(lblbottomgif);
		
		JLabel lblbackground = new JLabel("");
		lblbackground.setIcon(new ImageIcon(DailyBasis.class.getResource("/application/image/blueback.jpg")));
		lblbackground.setBounds(0, 0, 1532, 671);
		getContentPane().add(lblbackground);
		
		}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		fillDailyBalanceSheetTable(id);
		
	}

	private void fillDailyBalanceSheetTable(int id) {
		while (model.getRowCount()>0){			// clear table on refresh
			model.removeRow(0);
		}
		
		if (btndate.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Select Date To View Daily Statement(s)", "Date Not Selected", JOptionPane.WARNING_MESSAGE);
		}
		else {
			ArrayList<BalanceSheet>dailystatement = new ArrayList<BalanceSheet>();
			try {
				
				java.util.Date date = btndate.getDate();
				long getTime = date.getTime();
				Date getDate = new Date(getTime);
				
				String sql = "SELECT `transactionDate`, `transactionTime`, `expenseAmount`, `expenseID`, `expenseNote`, `depositAmount`, `depositID`, `balanceAmount` FROM `balancesheet` WHERE userID = ? AND transactionDate = ? ORDER BY `transactionDate` DESC,`transactionTime` DESC";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setDate(2, getDate);
				rs = ps.executeQuery();
				int flag = 0;
				while (rs.next()) {
					flag = 1;
					Date transactionDATE = rs.getDate("transactionDate");
					Time transactionTIME = rs.getTime("transactionTime");
					float expenseAMOUNT = rs.getFloat("expenseAmount");
					int expenseID = rs.getInt("expenseID");
					String note = rs.getString("expenseNote");
					float depositAMOUNT = rs.getFloat("depositAmount");
					int depositID = rs.getInt("depositID");
					float balanceAMOUNT = rs.getFloat("balanceAmount");
					
					balanceSheet = new BalanceSheet();
					balanceSheet.setTransactionDate(transactionDATE);
					balanceSheet.setTransactionTime(transactionTIME);
					balanceSheet.setExpenseAmount(expenseAMOUNT);
					balanceSheet.setExpenseID(expenseID);
					balanceSheet.setExpenseNote(note);
					balanceSheet.setDepositAmount(depositAMOUNT);
					balanceSheet.setDepositID(depositID);
					balanceSheet.setBalanceAmount(balanceAMOUNT);
					dailystatement.add(balanceSheet);
				}
				if (flag == 0) {
					JOptionPane.showMessageDialog(this, "No Daily Statement(s) To Show. Add Expense/Deposit Amount And Make Your Transaction.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			catch(SQLException ex) {
				System.out.println(ex);
			}
			
			for (BalanceSheet bs:dailystatement) {
				if (bs.getExpenseID() == 0) {
					model.addRow(new Object[] {
							
							bs.getTransactionDate(), bs.getTransactionTime(),bs.getDefaultValue(), bs.getDefaultValue(), bs.getDefaultValue(), "\u20B9 "+bs.getDepositAmount(), bs.getDepositID(), "\u20B9 "+bs.getBalanceAmount()	
					});
					
				}
				else {
					model.addRow(new Object[] {
		
							bs.getTransactionDate(), bs.getTransactionTime(), "\u20B9 "+bs.getExpenseAmount(), bs.getExpenseID(), bs.getExpenseNote(), bs.getDefaultValue(), bs.getDefaultValue(), "\u20B9 "+bs.getBalanceAmount()	
					});
				}
			}
		}
	}
}
