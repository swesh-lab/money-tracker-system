package application.run;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import java.sql.*;
import java.text.SimpleDateFormat;

import application.dbtask.Operation;
import javax.swing.JComboBox;

public class AddExpense extends JInternalFrame implements ActionListener{
	private JTextField txtexpenseid;
	private JTextField txtamount;
	private JDateChooser txtdate;
	private JTextArea txtnote;
	private JComboBox comboboxid;
	
	private Connection con;
	private PreparedStatement ps;
	ResultSet rs;
	
	Dashboard dashboard = new Dashboard();
	int id = Dashboard.userIDonDashboard;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddExpense frame = new AddExpense();
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
	public AddExpense() {
		Image icon = new ImageIcon(AddExpense.class.getResource("/application/image/rupee.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon frameIcon = new ImageIcon(icon); 
		setFrameIcon(frameIcon);
		getContentPane().setBackground(new Color(157, 226, 217));
		setResizable(false);

		con = Operation.createConnection();
		addExpenseFrame();
	}
	
	public void addExpenseFrame() {
		setOpaque(true);
		setClosable(true);
		setTitle("Add Expense");
		setBounds(100, 100, 1533, 700);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("(Optional)");
		label.setFont(new Font("Open Sans", Font.PLAIN, 11));
		label.setBounds(140, 386, 61, 13);
		getContentPane().add(label);
		
		JLabel lblAddExpense = new JLabel("Add Expense");
		lblAddExpense.setForeground(Color.BLACK);
		lblAddExpense.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddExpense.setFont(new Font("Neon Lights", Font.PLAIN, 40));
		lblAddExpense.setBackground(Color.RED);
		lblAddExpense.setBounds(100, 64, 323, 53);
		getContentPane().add(lblAddExpense);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AddExpense.class.getResource("/application/image/piggyexpense.gif")));
		lblNewLabel.setBounds(735, 50, 800, 600);
		getContentPane().add(lblNewLabel);
		
		JLabel lbleid = new JLabel("Expense ID");
		lbleid.setForeground(Color.BLACK);
		lbleid.setFont(new Font("Open Sans", Font.BOLD, 16));
		lbleid.setBounds(140, 198, 103, 27);
		getContentPane().add(lbleid);
		
		JLabel lbldate = new JLabel("Date");
		lbldate.setForeground(Color.BLACK);
		lbldate.setFont(new Font("Open Sans", Font.BOLD, 16));
		lbldate.setBounds(140, 238, 103, 27);
		getContentPane().add(lbldate);
		
		JLabel lblcid = new JLabel("Category ID");
		lblcid.setForeground(Color.BLACK);
		lblcid.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblcid.setBounds(140, 280, 103, 27);
		getContentPane().add(lblcid);
		
		JLabel lblnote = new JLabel("Note");
		lblnote.setForeground(Color.BLACK);
		lblnote.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblnote.setBounds(140, 359, 103, 27);
		getContentPane().add(lblnote);
		
		JLabel lblamt = new JLabel("Amount");
		lblamt.setForeground(Color.BLACK);
		lblamt.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblamt.setBounds(140, 322, 103, 27);
		getContentPane().add(lblamt);
		
		txtexpenseid = new JTextField();
		txtexpenseid.setEditable(false);
		txtexpenseid.setHorizontalAlignment(SwingConstants.CENTER);
		txtexpenseid.setForeground(Color.WHITE);
		txtexpenseid.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtexpenseid.setColumns(10);
		txtexpenseid.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, Color.GRAY, null, null));
		txtexpenseid.setBackground(Color.BLACK);
		txtexpenseid.setBounds(311, 198, 220, 27);
		fillExpenseID();
		getContentPane().add(txtexpenseid);
		
		txtdate = new JDateChooser();
		txtdate.setOpaque(false);
		txtdate.setAlignmentX(CENTER_ALIGNMENT);
		txtdate.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtdate.setDateFormatString("dd-MM-yyyy");
		txtdate.setBounds(311, 240, 220, 27);
		txtdate.setMinSelectableDate(new java.util.Date());	// set minDate range
		getContentPane().add(txtdate);
		
		txtamount = new JTextField();
		txtamount.setHorizontalAlignment(SwingConstants.CENTER);
		txtamount.setForeground(Color.BLACK);
		txtamount.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtamount.setColumns(10);
		txtamount.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, Color.GRAY, null, null));
		txtamount.setBackground(Color.WHITE);
		txtamount.setBounds(344, 322, 187, 27);
		getContentPane().add(txtamount);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(311, 363, 220, 105);
		getContentPane().add(scrollPane);
		
		txtnote = new JTextArea();
		txtnote.setOpaque(true);
		txtnote.setForeground(Color.BLACK);
		txtnote.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtnote.setBackground(Color.WHITE);
		scrollPane.setViewportView(txtnote);
		txtnote.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtnote.setLineWrap(true);
		txtnote.setWrapStyleWord(true);
		
		JButton btnadd = new JButton("Add");
		btnadd.setMnemonic(KeyEvent.VK_ENTER);
		btnadd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnadd.setForeground(new Color(218, 112, 214));
		btnadd.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnadd.setBorder(new LineBorder(new Color(153, 51, 153), 1, true));
		btnadd.setBackground(Color.BLACK);
		btnadd.setBounds(440, 504, 91, 37);
		getContentPane().add(btnadd);
		btnadd.addActionListener(this);
		
		comboboxid = new JComboBox();
		comboboxid.setForeground(Color.BLACK);
		comboboxid.setFont(new Font("Open Sans", Font.PLAIN, 14));
		comboboxid.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, Color.GRAY, null, null));
		comboboxid.setBackground(Color.WHITE);
		comboboxid.setBounds(311, 280, 220, 27);
		fillComboBox();
		getContentPane().add(comboboxid);
		
		JLabel lblrupee = new JLabel();
		lblrupee.setText("\u20B9");
		lblrupee.setOpaque(true);
		lblrupee.setHorizontalAlignment(SwingConstants.CENTER);
		lblrupee.setForeground(Color.WHITE);
		lblrupee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblrupee.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, Color.GRAY, null, null));
		lblrupee.setBackground(Color.BLACK);
		lblrupee.setBounds(311, 322, 28, 27);
		getContentPane().add(lblrupee);
		
		// block InternalFrame to shift from NorthBar
		BasicInternalFrameUI internal = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
		for (MouseListener listener:internal.getNorthPane().getMouseListeners()) {
				internal.getNorthPane().removeMouseListener(listener);
							
		}
	}
	
	int expenseid;
	private void fillExpenseID() {
		// auto allot expenseID
		try {
			String allotID = "SELECT expenseID FROM expense ORDER BY expenseID desc limit 1;";
			ps = con.prepareStatement(allotID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				expenseid = rs.getInt("expenseID")+1;
				txtexpenseid.setText(String.valueOf(expenseid));
			}
		}
		catch(SQLException ex) {
			System.out.println(ex);
		}
	}

	private void fillComboBox() {
		String sql = "SELECT categoryID from expensecategory WHERE userID = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				int categoryid = rs.getInt("categoryID");
				comboboxid.addItem(categoryid);
			}
		
		}
		catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand()=="Add") {
			addExpenseDetails(id);
		}
	}
	

	private void addExpenseDetails(int id) {
		
		String amount = txtamount.getText().trim();
		
		if (txtdate.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Select Expense Date", "Date Not Selected", JOptionPane.WARNING_MESSAGE);
		}
		
		else if (comboboxid.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Add Category From 'Add Expense Category' Page Inorder To Proceed.", "No Expense Category Found", JOptionPane.ERROR_MESSAGE);
		}
		
		else if (amount.isBlank()) {
			JOptionPane.showMessageDialog(this, "Enter Expense Amount", "Missing Field", JOptionPane.WARNING_MESSAGE);
		}
		
		else {
			try {	
				//	accept float value for expenseAmount
					Float expenseAmount = Float.parseFloat(amount);
					if (expenseAmount>0) {
						
							if (insertIntoBalanceSheet(id, expenseAmount) == false) {
								JOptionPane.showMessageDialog(this, "Insufficient Account Balance. Deposit Amount To Make Transaction.", "No Balance Amount", JOptionPane.ERROR_MESSAGE);
							}
							else {
								insertIntoBalanceSheet(id, expenseAmount);
								int selectedCategoryID = (int) comboboxid.getSelectedItem();
								java.util.Date date = txtdate.getDate();
								long dateSelected = date.getTime();
								Date sqlDate = new Date(dateSelected);
								
						//		SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
								try {
									String insertQuery = "INSERT INTO expense (`userID`, `expenseID`, `date`, `categoryID`, `amount`, `note`) VALUES (?,?,?,?,?,?)";
									ps = con.prepareStatement(insertQuery);
									ps.setInt(1, id);
									ps.setInt(2, expenseid);
									ps.setDate(3, sqlDate);
									ps.setInt(4, selectedCategoryID);
									ps.setFloat(5, expenseAmount);
									ps.setString(6, txtnote.getText());
									
									int value = ps.executeUpdate();
									if (value>0) {
										 ImageIcon icon = new ImageIcon(AddExpense.class.getResource("/application/image/checked.png"));
										 JOptionPane.showMessageDialog(this, "Expense Amount Added Successfully", "Added", JOptionPane.PLAIN_MESSAGE, icon);
										 fillExpenseID();
										 txtdate.setCalendar(null);
										 txtamount.setText("");
										 txtnote.setText("");
									}
								}
								catch (SQLException e) {
									System.out.println(e);
								}
							}

						
					}
					else {
						JOptionPane.showMessageDialog(this, "Expense Amount Is Negative. Enter Valid Amount", "Invalid", JOptionPane.WARNING_MESSAGE);
					}
			}
			
			catch (NumberFormatException nfex) {
				JOptionPane.showMessageDialog(this, "Enter Valid Amount", "Invalid", JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}

	private boolean insertIntoBalanceSheet(int id, float expenseAmount) {
		float previousBalance = 0.0f;
		try {
			String getPreviousBalance = "SELECT balanceAmount FROM balancesheet WHERE userID = ? ORDER BY `transactionDate` DESC, `transactionTime` DESC limit 1";
			ps = con.prepareStatement(getPreviousBalance);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				previousBalance = rs.getFloat("balanceAmount");
			}
		}
		catch (SQLException ex) {
			System.out.println(ex);
		}
		if (expenseAmount > previousBalance) {		// condition for no balance amount
			return false;
		}
		
		else {
			java.util.Date date = txtdate.getDate();
			long time = date.getTime();
			java.sql.Date transactionDate = new Date(time);
			java.util.Date utilDate = new java.util.Date();
			java.sql.Time transactionTime = new java.sql.Time(utilDate.getTime());
			try {
				String insertQuery = "INSERT INTO balancesheet (`userID`, `transactionDate`, `transactionTime`, `expenseAmount`, `expenseID`, `expenseNote`, `balanceAmount`) VALUES (?,?,?,?,?,?,?)";
				ps = con.prepareStatement(insertQuery);
				ps.setInt(1, id);
				ps.setDate(2, transactionDate);
				ps.setTime(3, transactionTime);
				ps.setFloat(4, expenseAmount);
				ps.setInt(5, expenseid);
				ps.setString(6, txtnote.getText());
				ps.setFloat(7, (previousBalance - expenseAmount));
				ps.executeUpdate();
			}
			catch (SQLException e) {
				System.out.println(e);
			}
			return true;
		}
	}

}
