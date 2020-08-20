package application.run;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import com.toedter.calendar.JDateChooser;

import application.dbtask.Operation;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AddDeposit extends JInternalFrame implements ActionListener, KeyListener{
	private JTextField txtdeposit;
	private JDateChooser txtdepositdate;
	private JTextField txtdepositamount;
	private JTextField txtcurrentbalance;

	private Float depositAmount;
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
//					AddDeposit frame = new AddDeposit();
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
	public AddDeposit() {
		getContentPane().setForeground(new Color(255, 182, 193));
		Image icon = new ImageIcon(AddDeposit.class.getResource("/application/image/rupee.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon frameIcon = new ImageIcon(icon); 
		setFrameIcon(frameIcon);
		getContentPane().setBackground(new Color(255, 182, 193));
		setResizable(false);
		
		con = Operation.createConnection();
		addDepositFrame();
	}
	
	public void addDepositFrame() {
		setOpaque(true);
		setClosable(true);
		setTitle("Add Deposit Amount");
		setBounds(100, 100, 1533, 700);
		getContentPane().setLayout(null);
		
		JLabel lblimg = new JLabel("");
		lblimg.setHorizontalAlignment(SwingConstants.CENTER);
		lblimg.setIcon(new ImageIcon(AddDeposit.class.getResource("/application/image/deposite.png")));
		lblimg.setBounds(23, 186, 220, 232);
		getContentPane().add(lblimg);
		
		JLabel lblsubtitle = new JLabel("<html><p>It takes seconds to record daily transactions. Add <b>deposite</b> amount and store the <b>balance</b>.</p></html>");
		lblsubtitle.setForeground(Color.BLACK);
		lblsubtitle.setFont(new Font("Rondalo", Font.PLAIN, 22));
		lblsubtitle.setBounds(254, 240, 506, 122);
		getContentPane().add(lblsubtitle);
		
		JLabel lbltitle = new JLabel("Deposit Amount");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setForeground(new Color(0, 0, 0));
		lbltitle.setFont(new Font("Neon Lights", Font.PLAIN, 40));
		lbltitle.setBackground(Color.RED);
		lbltitle.setBounds(951, 64, 395, 53);
		getContentPane().add(lbltitle);
		
		txtdeposit = new JTextField();
		txtdeposit.setEditable(false);
		txtdeposit.setHorizontalAlignment(SwingConstants.CENTER);
		txtdeposit.setForeground(Color.WHITE);
		txtdeposit.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtdeposit.setColumns(10);
		txtdeposit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
		txtdeposit.setBackground(Color.BLACK);
		txtdeposit.setBounds(1085, 230, 220, 27);
		fillDepositID();
		getContentPane().add(txtdeposit);
		
		txtdepositdate = new JDateChooser();
		txtdepositdate.setMinSelectableDate(new java.util.Date());		// import java.util.Date
		txtdepositdate.setOpaque(false);
		txtdepositdate.setForeground(Color.WHITE);
		txtdepositdate.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtdepositdate.setBackground(Color.BLACK);
		txtdepositdate.setBounds(1085, 276, 220, 27);
		txtdepositdate.setDateFormatString("dd-MM-yyyy");
		txtdepositdate.addKeyListener(this);
		getContentPane().add(txtdepositdate);
		
		JLabel label = new JLabel();
		label.setText("\u20B9");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, Color.GRAY, null, null));
		label.setBackground(Color.BLACK);
		label.setBounds(1085, 322, 28, 27);
		getContentPane().add(label);
		
		txtdepositamount = new JTextField();
		txtdepositamount.setHorizontalAlignment(SwingConstants.CENTER);
		txtdepositamount.setForeground(Color.BLACK);
		txtdepositamount.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtdepositamount.setColumns(10);
		txtdepositamount.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
		txtdepositamount.setBackground(Color.WHITE);
		txtdepositamount.setBounds(1118, 322, 187, 27);
		txtdepositamount.addKeyListener(this);
		getContentPane().add(txtdepositamount);
		
		txtcurrentbalance = new JTextField();
		txtcurrentbalance.setBorder(null);
		txtcurrentbalance.setOpaque(false);
		txtcurrentbalance.setEditable(false);
		txtcurrentbalance.setHorizontalAlignment(SwingConstants.RIGHT);
		txtcurrentbalance.setForeground(new Color(148, 0, 211));
		txtcurrentbalance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtcurrentbalance.setBackground(Color.PINK);
		txtcurrentbalance.setBounds(908, 480, 397, 27);
		getContentPane().add(txtcurrentbalance);
		
		JButton btnadd = new JButton("Add");
		btnadd.setMnemonic(KeyEvent.VK_ENTER);
		btnadd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnadd.setForeground(new Color(218, 112, 214));
		btnadd.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnadd.setBorder(new LineBorder(new Color(153, 51, 153), 1, true));
		btnadd.setBackground(Color.BLACK);
		btnadd.setBounds(1214, 392, 91, 37);
		getContentPane().add(btnadd);
		btnadd.addActionListener(this);
		
		JLabel lblid = new JLabel("Deposit ID");
		lblid.setForeground(Color.BLACK);
		lblid.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblid.setBounds(908, 230, 135, 27);
		getContentPane().add(lblid);
		
		JLabel lbldate = new JLabel("Deposit Date");
		lbldate.setForeground(Color.BLACK);
		lbldate.setFont(new Font("Open Sans", Font.BOLD, 16));
		lbldate.setBounds(908, 276, 135, 27);
		getContentPane().add(lbldate);
		
		JLabel lbldepositamount = new JLabel("Deposit Amount");
		lbldepositamount.setForeground(Color.BLACK);
		lbldepositamount.setFont(new Font("Open Sans", Font.BOLD, 16));
		lbldepositamount.setBounds(908, 322, 135, 27);
		getContentPane().add(lbldepositamount);
		
		JButton btnviewbalance = new JButton("View Balance Amount");
		btnviewbalance.setToolTipText("Know Your Current Balance Amount");
		btnviewbalance.setMnemonic(KeyEvent.VK_ENTER);
		btnviewbalance.setHorizontalTextPosition(SwingConstants.CENTER);
		btnviewbalance.setForeground(new Color(218, 112, 214));
		btnviewbalance.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnviewbalance.setBorder(new LineBorder(new Color(153, 51, 153), 1, true));
		btnviewbalance.setBackground(Color.BLACK);
		btnviewbalance.setBounds(1118, 439, 187, 37);
		btnviewbalance.addActionListener(this);
		getContentPane().add(btnviewbalance);
		
		JLabel lblwall = new JLabel("");
		lblwall.setIcon(new ImageIcon(AddDeposit.class.getResource("/application/image/particles.gif")));
		lblwall.setBounds(-59, 370, 886, 436);
		getContentPane().add(lblwall);
		
		// block InternalFrame to shift from NorthBar
		BasicInternalFrameUI internal = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
		for (MouseListener listener:internal.getNorthPane().getMouseListeners()) {
			internal.getNorthPane().removeMouseListener(listener);		
		}
			
	}

	int depositid;
	public void fillDepositID() {
		// auto allot depositID
		try {
			String allotID = "SELECT depositID FROM deposit ORDER BY depositID desc limit 1";
			ps = con.prepareStatement(allotID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				depositid = rs.getInt("depositID")+1;
				txtdeposit.setText(String.valueOf(depositid));
			}
		}
		catch (SQLException ex) {
			System.out.println(ex);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Add") {
			addDepositDetails(id);
		}
		if (e.getActionCommand() == "View Balance Amount") {
			getCurrentBalance(id);
		}
	}
	
	private void addDepositDetails(int id) {
		String amount = txtdepositamount.getText().trim();
		
		if (txtdepositdate.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Select Deposit Date", "Date Not Selected", JOptionPane.WARNING_MESSAGE);
		}
		
		else if (amount.isBlank()) {
			JOptionPane.showMessageDialog(this, "Enter Deposit Amount", "Missing Field", JOptionPane.WARNING_MESSAGE);
		}
		
		else {
			try {
				depositAmount = Float.parseFloat(amount);
				if (depositAmount>0) {							// for +floatType value
					
					insertIntoBalanceSheet(id, depositAmount);
					
					java.util.Date date = txtdepositdate.getDate();
					long dateSelected = date.getTime();
					Date sqlDate = new Date(dateSelected);
					
					try {
						String insertQuery = "INSERT INTO deposit (`userID`, `depositID`, `depositDate`, `depositAmount`) VALUES (?,?,?,?)";
						ps = con.prepareStatement(insertQuery);
						ps.setInt(1, id);
						ps.setInt(2, depositid);
						ps.setDate(3, sqlDate);
						ps.setFloat(4, depositAmount);
						
						int value = ps.executeUpdate();
						if (value>0) {
								 ImageIcon icon = new ImageIcon(AddDeposit.class.getResource("/application/image/checked.png"));
								 JOptionPane.showMessageDialog(this, "Deposit Amount Added Successfully", "Added", JOptionPane.PLAIN_MESSAGE, icon);
								 fillDepositID();
								 txtcurrentbalance.setText("");
								 txtdepositdate.setCalendar(null);
								 txtdepositamount.setText("");
						}
					}
					catch (SQLException e) {
						System.out.println(e);
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Deposit Amount Is Negative. Enter Valid Amount", "Invalid", JOptionPane.WARNING_MESSAGE);
				}
			}
			
			catch (NumberFormatException nfex) {
				JOptionPane.showMessageDialog(this, "Enter Valid Amount", "Invalid", JOptionPane.WARNING_MESSAGE);
			}
			
			
		}
	}
	
	private void getCurrentBalance(int id){
		float previousBalance = 0.0f;
		try {
			String getPreviousBalance = "SELECT balanceAmount FROM balancesheet WHERE userID = ? ORDER BY `transactionDate` DESC, `transactionTime` DESC limit 1";
			ps = con.prepareStatement(getPreviousBalance);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				previousBalance = rs.getFloat("balanceAmount");
				if (previousBalance == 0) {
					txtcurrentbalance.setText("\u20B9 0.0 (No Account Balance)");
				}
				else {
					txtcurrentbalance.setText("\u20B9 " + previousBalance);
				}
			}
			else {
				txtcurrentbalance.setText("(Account Balance Not Added Yet)");
			}
		}
		catch (SQLException ex) {
			System.out.println(ex);
		}
	}
	

	private void insertIntoBalanceSheet(int id, float depositAmount) {
		
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
		
		java.util.Date date = txtdepositdate.getDate();
		long time = date.getTime();
		java.sql.Date transactionDate = new Date(time);
		java.util.Date utilDate = new java.util.Date();
		java.sql.Time transactionTime = new java.sql.Time(utilDate.getTime());
		try {
			String insertQuery = "INSERT INTO balancesheet (`userID`, `transactionDate`, `transactionTime`, `depositAmount`, `depositID`, `balanceAmount`) VALUES (?,?,?,?,?,?)";
			ps = con.prepareStatement(insertQuery);
			ps.setInt(1, id);
			ps.setDate(2, transactionDate);
			ps.setTime(3, transactionTime);
			ps.setFloat(4, depositAmount);
			ps.setInt(5, depositid);
			ps.setFloat(6, (previousBalance + depositAmount));
			ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		finally {
			if(ps != null) {
				try {
					ps.close();
				}
				catch(SQLException ex) {
					System.out.println(ex);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			addDepositDetails(id);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
