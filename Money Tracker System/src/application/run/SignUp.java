/*
 * This system allows the user to view daily/monthly review of money spent and can analyze his/her expense.
 * Created by Sweta Sharma on 10/07/2020
 *  
 * */

package application.run;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

import application.dbtask.Operation;
import java.sql.*;

public class SignUp extends JFrame implements ActionListener, KeyListener{

	private Connection con;
	private PreparedStatement ps;
	public static int userid;
	static int submitButton = 0;
	
	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtcontact;
	private JTextField txtaddress;
	private JTextField txtmail;
	private JPasswordField txtpassword;
	private JTextField txtuid;
	private JTextField txtcity;
	private final ButtonGroup gender = new ButtonGroup();
	JButton btnlogin;
	JButton btncreate;
	JCheckBox checkbox;
	
	JRadioButton btnmale;
	JRadioButton btnfemale;
	
	Privacy privacy = new Privacy();
	private int id;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SignUp frame = new SignUp();
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
	
	public SignUp() {
		con = Operation.createConnection();
		signUpFrame();
	}
	
	public void signUpFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignUp.class.getResource("/application/image/icon.png")));
		setTitle("Money Tracker - Sign Up");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 10, 1390, 860);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtuid = new JTextField();
		txtuid.setEditable(false);
		txtuid.setOpaque(false);
		txtuid.setBackground(new Color(211, 211, 211));
		txtuid.setHorizontalAlignment(SwingConstants.CENTER);
		txtuid.setForeground(new Color(128, 0, 128));
		txtuid.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtuid.setColumns(10);
		txtuid.setBorder(new LineBorder(new Color(204, 51, 204), 2, true));
		txtuid.setBounds(1136, 277, 181, 27);
		contentPane.add(txtuid);
		
		JLabel uidgif = new JLabel("");
		uidgif.setIcon(new ImageIcon(SignUp.class.getResource("/application/image/uidback.gif")));
		uidgif.setHorizontalAlignment(SwingConstants.CENTER);
		uidgif.setBounds(1136, 277, 181, 27);
		contentPane.add(uidgif);
		
		
		JLabel lblimg = new JLabel("");
		lblimg.setIcon(new ImageIcon(SignUp.class.getResource("/application/image/welcome.gif")));
		lblimg.setBounds(0, 251, 710, 571);
		contentPane.add(lblimg);
		lblimg.setHorizontalAlignment(SwingConstants.CENTER);
		
		btncreate = new JButton("Create Account");
		btncreate.setOpaque(false);
		btncreate.setHorizontalTextPosition(SwingConstants.CENTER);
		btncreate.setForeground(new Color(102, 0, 102));
		btncreate.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btncreate.setBorder(new LineBorder(new Color(153, 51, 153), 1, true));
		btncreate.setBackground(new Color(255, 102, 255));
		btncreate.setBounds(1065, 697, 187, 37);
		contentPane.add(btncreate);
		btncreate.addActionListener(this);
		btncreate.addKeyListener(this);
		
		JLabel btngif = new JLabel("");
		btngif.setIcon(new ImageIcon(SignUp.class.getResource("/application/image/button.gif")));
		btngif.setHorizontalAlignment(SwingConstants.CENTER);
		btngif.setBounds(1065, 697, 187, 37);
		contentPane.add(btngif);
		
		JLabel lblsignin = new JLabel("Sign Up");
		lblsignin.setHorizontalAlignment(SwingConstants.CENTER);
		lblsignin.setForeground(new Color(204, 51, 204));
		lblsignin.setFont(new Font("Induction", Font.PLAIN, 30));
		lblsignin.setAutoscrolls(true);
		lblsignin.setBounds(998, 172, 288, 64);
		contentPane.add(lblsignin);
		
		JLabel lbltext = new JLabel("Already have an account !?");
		lbltext.setHorizontalTextPosition(SwingConstants.CENTER);
		lbltext.setHorizontalAlignment(SwingConstants.CENTER);
		lbltext.setForeground(new Color(153, 51, 153));
		lbltext.setFont(new Font("Open Sans", Font.ITALIC, 16));
		lbltext.setBounds(995, 227, 230, 27);
		contentPane.add(lbltext);
		
		btnlogin = new JButton("Log In");
		btnlogin.setOpaque(false);
		btnlogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnlogin.setForeground(new Color(102, 0, 102));
		btnlogin.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnlogin.setBorder(null);
		btnlogin.setBackground(new Color(255, 255, 255));
		btnlogin.setBounds(1221, 226, 86, 29);
		contentPane.add(btnlogin);
		btnlogin.addActionListener(this);
		btnlogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					LogIn login = new LogIn();
					login.setVisible(true);
			}
		});
		
		JLabel lblname = new JLabel("Name");
		lblname.setForeground(new Color(153, 51, 153));
		lblname.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblname.setBounds(977, 341, 72, 29);
		contentPane.add(lblname);
		
		txtname = new JTextField();
		txtname.setOpaque(false);
		txtname.setBackground(new Color(216, 191, 216));
		txtname.setHorizontalAlignment(SwingConstants.CENTER);
		txtname.setForeground(new Color(153, 51, 153));
		txtname.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtname.setColumns(10);
		txtname.setBorder(new LineBorder(new Color(204, 51, 204), 1, true));
		txtname.setBounds(1136, 341, 181, 27);
		contentPane.add(txtname);
		txtname.addActionListener(this);
		txtname.addKeyListener(this);
		
		JLabel lblcontact = new JLabel("Contact No.");
		lblcontact.setForeground(new Color(153, 51, 153));
		lblcontact.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblcontact.setBounds(977, 380, 109, 29);
		contentPane.add(lblcontact);
		
		txtcontact = new JTextField();
		txtcontact.setOpaque(false);
		txtcontact.setBackground(new Color(216, 191, 216));
		txtcontact.setHorizontalAlignment(SwingConstants.CENTER);
		txtcontact.setForeground(new Color(153, 51, 153));
		txtcontact.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtcontact.setBorder(new LineBorder(new Color(204, 51, 204), 1, true));
		txtcontact.setBounds(1136, 380, 181, 27);
		contentPane.add(txtcontact);
		txtcontact.addActionListener(this);
		txtcontact.addKeyListener(this);
		
		JLabel lbladdress = new JLabel("Address");
		lbladdress.setForeground(new Color(153, 51, 153));
		lbladdress.setFont(new Font("Open Sans", Font.BOLD, 16));
		lbladdress.setBounds(977, 419, 72, 29);
		contentPane.add(lbladdress);
		
		txtaddress = new JTextField();
		txtaddress.setOpaque(false);
		txtaddress.setBackground(new Color(216, 191, 216));
		txtaddress.setHorizontalAlignment(SwingConstants.CENTER);
		txtaddress.setForeground(new Color(153, 51, 153));
		txtaddress.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtaddress.setColumns(10);
		txtaddress.setBorder(new LineBorder(new Color(204, 51, 204), 1, true));
		txtaddress.setBounds(1136, 419, 181, 27);
		contentPane.add(txtaddress);
		txtaddress.addKeyListener(this);
		
		JLabel lblmail = new JLabel("E-Mail");
		lblmail.setForeground(new Color(153, 51, 153));
		lblmail.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblmail.setBounds(977, 497, 72, 29);
		contentPane.add(lblmail);
		
		txtmail = new JTextField();
		txtmail.setOpaque(false);
		txtmail.setBackground(new Color(216, 191, 216));
		txtmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtmail.setForeground(new Color(153, 51, 153));
		txtmail.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtmail.setColumns(10);
		txtmail.setBorder(new LineBorder(new Color(204, 51, 204), 1, true));
		txtmail.setBounds(1136, 497, 181, 27);
		contentPane.add(txtmail);
		txtmail.addKeyListener(this);
		
		JLabel lblgender = new JLabel("Gender");
		lblgender.setForeground(new Color(153, 51, 153));
		lblgender.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblgender.setBounds(977, 536, 72, 29);
		contentPane.add(lblgender);
		
		txtpassword = new JPasswordField();
		txtpassword.setOpaque(false);
		txtpassword.setBackground(new Color(216, 191, 216));
		txtpassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtpassword.setForeground(new Color(153, 51, 153));
		txtpassword.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtpassword.setColumns(10);
		txtpassword.setBorder(new LineBorder(new Color(204, 51, 204), 1, true));
		txtpassword.setBounds(1137, 575, 181, 27);
		contentPane.add(txtpassword);
		txtpassword.addKeyListener(this);
		
		JLabel lblsetpassword = new JLabel("Set Password");
		lblsetpassword.setForeground(new Color(153, 51, 153));
		lblsetpassword.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblsetpassword.setBounds(977, 575, 109, 29);
		contentPane.add(lblsetpassword);
		
		JLabel lbluid = new JLabel("Alloted User ID");
		lbluid.setForeground(new Color(128, 0, 128));
		lbluid.setFont(new Font("Open Sans", Font.BOLD, 16));
		lbluid.setBounds(977, 275, 122, 29);
		contentPane.add(lbluid);
		
		JLabel lblcity = new JLabel("City");
		lblcity.setForeground(new Color(153, 51, 153));
		lblcity.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblcity.setBounds(977, 458, 72, 29);
		contentPane.add(lblcity);
		
		txtcity = new JTextField();
		txtcity.setOpaque(false);
		txtcity.setBackground(new Color(216, 191, 216));
		txtcity.setHorizontalAlignment(SwingConstants.CENTER);
		txtcity.setForeground(new Color(153, 51, 153));
		txtcity.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtcity.setColumns(10);
		txtcity.setBorder(new LineBorder(new Color(204, 51, 204), 1, true));
		txtcity.setBounds(1136, 458, 181, 27);
		contentPane.add(txtcity);
		txtcity.addKeyListener(this);
		
		btnmale = new JRadioButton("Male");
		btnmale.setOpaque(false);
		btnmale.setBackground(new Color(255, 255, 255));
		btnmale.setForeground(new Color(153, 50, 204));
		btnmale.setFont(new Font("Open Sans", Font.PLAIN, 16));
		gender.add(btnmale);
		btnmale.setBounds(1136, 540, 86, 21);
		contentPane.add(btnmale);
		
		btnfemale = new JRadioButton("Female");
		btnfemale.setOpaque(false);
		btnfemale.setBackground(new Color(255, 255, 255));
		btnfemale.setForeground(new Color(153, 50, 204));
		btnfemale.setFont(new Font("Open Sans", Font.PLAIN, 16));
		gender.add(btnfemale);
		btnfemale.setBounds(1241, 540, 86, 21);
		contentPane.add(btnfemale);
		
		checkbox = new JCheckBox("I agree to the Privacy Policy.");
		checkbox.setOpaque(false);
		checkbox.setBackground(new Color(255, 255, 255));
		checkbox.setFont(new Font("Open Sans", Font.BOLD, 16));
		checkbox.setForeground(new Color(128, 0, 128));
		checkbox.setHorizontalAlignment(SwingConstants.CENTER);
		checkbox.setBounds(986, 637, 341, 29);
		contentPane.add(checkbox);
		checkbox.addActionListener(new ActionListener() {
		//display Privacy Panel on selection of CheckBox
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkbox.isSelected())
					privacy.setVisible(true);				
			}
		});
		checkbox.addKeyListener(this);
		
		JLabel lbltitle = new JLabel("MONEY TRACKER");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setForeground(new Color(128, 0, 128));
		lbltitle.setFont(new Font("Woodcut", Font.PLAIN, 30));
		lbltitle.setBackground(Color.WHITE);
		lbltitle.setBounds(906, 78, 485, 64);
		contentPane.add(lbltitle);
		
		JLabel lblrupee = new JLabel("\u20B9");
		lblrupee.setHorizontalAlignment(SwingConstants.CENTER);
		lblrupee.setForeground(new Color(128, 0, 128));
		lblrupee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblrupee.setBounds(1372, 78, 31, 37);
		contentPane.add(lblrupee);
		
		JLabel lblsubtitle = new JLabel("Money Tracking, Simplified");
		lblsubtitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblsubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblsubtitle.setForeground(new Color(153, 0, 153));
		lblsubtitle.setFont(new Font("Rage Italic", Font.PLAIN, 26));
		lblsubtitle.setBounds(987, 133, 306, 29);
		contentPane.add(lblsubtitle);
		
		JLabel panelbg = new JLabel("");
		panelbg.setHorizontalAlignment(SwingConstants.LEFT);
		panelbg.setBounds(776, 0, 756, 852);
		contentPane.add(panelbg);
		ImageIcon panel= new ImageIcon(SignUp.class.getResource("/application/image/lightbg.jpg"));
		Image newpanelbg = panel.getImage().getScaledInstance(panelbg.getWidth(), panelbg.getHeight(), Image.SCALE_SMOOTH);
		panelbg.setIcon(new ImageIcon(newpanelbg));
		setLocationRelativeTo(null);
		
		JLabel label = new JLabel("<html>GAIN TOTAL CONTROL OF <br>\r\nYOUR MONEY\r\n</html>");
		label.setForeground(new Color(128, 0, 128));
		label.setFont(new Font("Terminator Two", Font.PLAIN, 30));
		label.setBounds(117, 40, 451, 101);
		contentPane.add(label);
		
		JLabel lblsayNoMore = new JLabel("<html>Say no more to carrying paper-bills. This virtual <b>Money Tracker</b> System keeps you on track with daily expenses all the time.<br><br>\r\nViewing and analyzing the money spent in a month are now easier than ever to track. Simply add them to your dashboard to see and monitor them all at once.\r\n</html>");
		lblsayNoMore.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblsayNoMore.setBounds(117, 132, 462, 95);
		contentPane.add(lblsayNoMore);
		
		// auto allot user id
		try {
			String allotID = "select userID from userdetails order by userID desc limit 1;";
			ps = con.prepareStatement(allotID);
			ResultSet set = ps.executeQuery();
			if(set.next()) {
				id = set.getInt("userID")+1;		// increment +1 to display next userID via dataBase
				txtuid.setText(String.valueOf(id));
					userid = (id-1);						// stores userID via signUp (decrement -1 to store actual userID)
			}
		}
		catch(SQLException sqlex) {
			System.out.println(sqlex);
		}
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnname = e.getActionCommand();
		if(btnname.equals("Log In")) {		
			this.dispose();								
			privacy.dispose();							//close Privacy Panel if unclosed
		}
		if(btnname.equals("Create Account")) {
			insertRecord();
		}	
	}

		
	private void insertRecord() {
		
		String name = txtname.getText().trim();
		String contact = txtcontact.getText().trim();
		String address = txtaddress.getText().trim();
		String mail = txtmail.getText().trim();
		String city = txtcity.getText().trim();
		String gender = "";
		if(btnmale.isSelected()) {
			gender = btnmale.getText();
		}
		if(btnfemale.isSelected()) {
			gender = btnfemale.getText();
		}
		String password = String.valueOf(txtpassword.getPassword());
		
		if (name.isBlank()||contact.isBlank()||address.isBlank()||mail.isBlank()||gender.isBlank()||city.isBlank()||password.isBlank()) {
			JOptionPane.showMessageDialog(this, "One or more required field is empty! Fill the complete account details.", "Missing Field", JOptionPane.WARNING_MESSAGE);
		}
		else if(checkbox.isSelected()==false) {
			JOptionPane.showMessageDialog(this, "In order to access Money Tracker Dashboard, you must agree to the Privacy Policy.", "Privacy Policy", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
				if (JOptionPane.showConfirmDialog(this, name + ", Your account will be created with the User ID " + txtuid.getText() + ". Are you sure you want to continue?")==0){
					
										
					try {
						String sql = "INSERT INTO userdetails(userID, name, contact, address, city, mail, gender, password) values(?,?,?,?,?,?,?,?)";
						ps = con.prepareStatement(sql);
						ps.setInt(1, id);
						ps.setString(2, name);
						ps.setString(3, contact);
						ps.setString(4, address);
						ps.setString(5, city);
						ps.setString(6, mail);
						ps.setString(7, gender);
						ps.setString(8, password);
						int count = ps.executeUpdate();
						if (count>0) {
							ImageIcon icon = new ImageIcon(SignUp.class.getResource("/application/image/checked.png"));
							JOptionPane.showMessageDialog(this, "Your account has been created!", "Success", JOptionPane.PLAIN_MESSAGE, icon);
						
						}
						submitButton = 2; 			// stores the name of the frame on SignUp
						Dashboard dashboard = new Dashboard();
						dashboard.setVisible(true);
						this.dispose();
						privacy.dispose();
						
					}
					catch (SQLException sqlex) {
						System.out.println(sqlex);
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

		}	//else-closing
		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
	// numeric value for contact number
		if(e.getKeyChar()>='a' && e.getKeyChar()<='z') {
			txtcontact.setEditable(false);
		}
		else {
			txtcontact.setEditable(true);
		}
		
	// alphabets/charValue for name
		if(e.getKeyChar()>='0' && e.getKeyChar()<='9') {
			txtname.setEditable(false);
		}
		else {
			txtname.setEditable(true);
		}	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			insertRecord();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
