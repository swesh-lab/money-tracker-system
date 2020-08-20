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
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.DebugGraphics;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import application.dbtask.Operation;
import application.run.*;

public class LogIn extends JFrame implements ActionListener, KeyListener{

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	 
	private JPanel contentPane;
	private JTextField txtuid;
	private JLabel uidicon;
	private JLabel lblpassword;
	private JLabel picon;
	private JLabel lbltext;
	private JButton btnsignup;
	private JPasswordField txtpassword;
	JButton btncontinue;
	
	public static int userid;
	static int submitButton = 0;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LogIn frame = new LogIn();
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
	
	public LogIn() {
		con = Operation.createConnection();
		logInFrame();
	}
	
	public void logInFrame() {
		setTitle("Money Tracker - Log In");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogIn.class.getResource("/application/image/icon.png")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1390, 860);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(255, 153, 204));
		contentPane.setFont(new Font("Rondalo", Font.PLAIN, 14));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel sparkle = new JLabel("");
		sparkle.setIcon(new ImageIcon(LogIn.class.getResource("/application/image/sparkle.gif")));
		sparkle.setHorizontalAlignment(SwingConstants.CENTER);
		sparkle.setBounds(-14, 365, 451, 480);
		contentPane.add(sparkle);
		
		JLabel lblNewLabel = new JLabel("<html>Say no more to carrying paper-bills. This virtual <b>Money Tracker</b> System keeps you on track with daily expenses all the time.</html>");
		lblNewLabel.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblNewLabel.setBounds(117, 194, 462, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblheading = new JLabel("<html>GAIN TOTAL CONTROL OF <br>\r\nYOUR MONEY\r\n</html>");
		lblheading.setFont(new Font("Terminator Two", Font.PLAIN, 30));
		lblheading.setForeground(new Color(128, 0, 128));
		lblheading.setBounds(117, 102, 451, 101);
		contentPane.add(lblheading);
		
		lbltext = new JLabel("New to the Money Tracker !?");
		lbltext.setForeground(new Color(153, 51, 153));
		lbltext.setFont(new Font("Open Sans", Font.ITALIC, 16));
		lbltext.setHorizontalAlignment(SwingConstants.CENTER);
		lbltext.setHorizontalTextPosition(SwingConstants.CENTER);
		lbltext.setBounds(972, 330, 230, 27);
		contentPane.add(lbltext);
		
		JLabel lbllogin = new JLabel("Log In");
		lbllogin.setHorizontalAlignment(SwingConstants.CENTER);
		lbllogin.setForeground(new Color(204, 51, 204));
		lbllogin.setAutoscrolls(true);
		lbllogin.setFont(new Font("Induction", Font.PLAIN, 30));
		lbllogin.setBounds(1015, 268, 288, 64);
		contentPane.add(lbllogin);
		
		
		JLabel lbluid = new JLabel("User ID");
		lbluid.setForeground(new Color(153, 51, 153));
		lbluid.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lbluid.setBounds(1026, 421, 72, 29);
		contentPane.add(lbluid);
		
		txtuid = new JTextField();
		txtuid.setBackground(new Color(0, 0, 0));
		txtuid.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtuid.setForeground(new Color(218, 112, 214));
		txtuid.setBorder(new LineBorder(new Color(204, 51, 204), 1, true));
		txtuid.setHorizontalAlignment(SwingConstants.CENTER);
		txtuid.setBounds(1134, 421, 181, 27);
		contentPane.add(txtuid);
		txtuid.setColumns(10);
		txtuid.addKeyListener(this);
		
		uidicon = new JLabel("");
		uidicon.setHorizontalTextPosition(SwingConstants.CENTER);
		uidicon.setHorizontalAlignment(SwingConstants.CENTER);
		uidicon.setBounds(985, 421, 31, 29);
		contentPane.add(uidicon);
		ImageIcon uidimg= new ImageIcon(LogIn.class.getResource("/application/image/uid.png"));
		Image newuidpimg = uidimg.getImage().getScaledInstance(uidicon.getWidth(), uidicon.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon fixuidimg = new ImageIcon(newuidpimg);
		uidicon.setIcon(fixuidimg);
		
		lblpassword = new JLabel("Password");
		lblpassword.setForeground(new Color(153, 51, 153));
		lblpassword.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblpassword.setBounds(1027, 460, 86, 29);
		contentPane.add(lblpassword);
		
		txtpassword = new JPasswordField();
		txtpassword.setBackground(new Color(0, 0, 0));
		txtpassword.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtpassword.setForeground(new Color(218, 112, 214));
		txtpassword.setBorder(new LineBorder(new Color(204, 51, 204), 1, true));
		txtpassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtpassword.setColumns(10);
		txtpassword.setBounds(1135, 460, 181, 27);
		contentPane.add(txtpassword);
		txtpassword.addKeyListener(this);
		
		picon = new JLabel("");
		picon.setHorizontalTextPosition(SwingConstants.CENTER);
		picon.setHorizontalAlignment(SwingConstants.CENTER);
		picon.setBounds(985, 460, 31, 29);
		contentPane.add(picon);
		ImageIcon pimg= new ImageIcon(LogIn.class.getResource("/application/image/password.png"));
		Image newpimg = pimg.getImage().getScaledInstance(picon.getWidth(), picon.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon fixpimg = new ImageIcon(newpimg);
		picon.setIcon(fixpimg);
		
		btncontinue = new JButton("Continue");
		btncontinue.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btncontinue.setOpaque(false);
		btncontinue.setHorizontalTextPosition(SwingConstants.CENTER);
		btncontinue.setBorder(new LineBorder(new Color(153, 51, 153), 1, true));
		btncontinue.setBackground(new Color(255, 102, 255));
		btncontinue.setForeground(new Color(102, 0, 102));
		btncontinue.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btncontinue.setBounds(1068, 547, 187, 37);
		contentPane.add(btncontinue);
		btncontinue.addActionListener(this);
		btncontinue.addKeyListener(this);
		
		JLabel lblsubtitle = new JLabel("Money Tracking, Simplified");
		lblsubtitle.setForeground(new Color(218, 112, 214));
		lblsubtitle.setFont(new Font("Rage Italic", Font.PLAIN, 26));
		lblsubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblsubtitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblsubtitle.setBounds(1010, 207, 306, 29);
		contentPane.add(lblsubtitle);
		
		btnsignup = new JButton("Create Account");
		btnsignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnsignup.setBorder(null);
		btnsignup.setBackground(new Color(0, 0, 0));
		btnsignup.setForeground(new Color(218, 112, 214));
		btnsignup.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnsignup.setHorizontalTextPosition(SwingConstants.CENTER);
		btnsignup.setBounds(1197, 330, 126, 25);
		contentPane.add(btnsignup);
		btnsignup.addActionListener(this);
		
		JLabel buttongif = new JLabel("");
		buttongif.setIcon(new ImageIcon(LogIn.class.getResource("/application/image/button.gif")));
		buttongif.setBounds(1068, 547, 187, 37);
		contentPane.add(buttongif);
		
		JLabel leftimg = new JLabel("");
		leftimg.setHorizontalAlignment(SwingConstants.CENTER);
		leftimg.setBounds(89, 227, 520, 538);
		contentPane.add(leftimg);
		ImageIcon img= new ImageIcon(LogIn.class.getResource("/application/image/piggyfree.png"));
		Image newimg = img.getImage().getScaledInstance(leftimg.getWidth(), leftimg.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon fiximg = new ImageIcon(newimg);
		leftimg.setIcon(fiximg);
		
		
		JLabel lbltitle = new JLabel("MONEY TRACKER");
		lbltitle.setForeground(new Color(238, 130, 238));
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("Woodcut", Font.PLAIN, 30));
		lbltitle.setBackground(Color.WHITE);
		lbltitle.setBounds(912, 152, 485, 64);
		contentPane.add(lbltitle);
		
		JLabel lblrupee = new JLabel("\u20B9");
		lblrupee.setForeground(new Color(238, 130, 238));
		lblrupee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblrupee.setHorizontalAlignment(SwingConstants.CENTER);
		lblrupee.setBounds(1378, 152, 31, 37);
		contentPane.add(lblrupee);
		
		JLabel background = new JLabel("");
		background.setHorizontalTextPosition(SwingConstants.CENTER);
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setBounds(0, 0, 775, 860);
		contentPane.add(background);
		ImageIcon bg= new ImageIcon(LogIn.class.getResource("/application/image/background.jpg"));
		Image newbg = bg.getImage().getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon fixbg = new ImageIcon(newbg);
		background.setIcon(fixbg);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnname = e.getActionCommand();					
		if (btnname.equals("Create Account")) {					// GOTO SIGN UP Panel
			submitButton = 0;									// clear previous submitButton value if selected
			SignUp signup = new SignUp();
			signup.setVisible(true);
			this.dispose();
		}	
		if (btnname.equals("Continue")) {
			openDashboard();
		}
	}

	private void openDashboard() {
		String id = txtuid.getText().trim();
			userid = Integer.parseInt(id);						// stores userID via logIn
		String password = String.valueOf(txtpassword.getPassword());
		if (id.isBlank()||password.isBlank()) {
			JOptionPane.showMessageDialog(this, "One or more required field is empty!", "Missing Field", JOptionPane.WARNING_MESSAGE);
			
		}	
		else {
				
				try {
					String verify = "SELECT `userID`, `password` FROM `userdetails`";
					ps = con.prepareStatement(verify);
					rs = ps.executeQuery();
					int flag = 0;
					while(rs.next()) {
						String uid = rs.getString("userID");
						String pwd = rs.getString("password");
						if((id.equals(uid)) && (password.equals(pwd))) {
							flag = 1;
							submitButton = 1;							// stores the name of the frame on logIn
							Dashboard dashboard = new Dashboard();
							dashboard.setVisible(true);
							this.dispose();
						}
					}
					
					if (flag==0) {
						JOptionPane.showMessageDialog(this, "Incorrect UserID/Password", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
						txtuid.setText("");
						txtpassword.setText("");
					}
				}
				
				catch(SQLException sqlex) {
					System.out.println(sqlex);
				}
				
				finally {
					if (rs != null) {
						try {
							rs.close();
						}
						catch (SQLException s) {
							System.out.println(s);
						}
					}
					if (ps != null) {
						try {
							ps.close();
						}
						catch (SQLException e) {
							System.out.println(e);
						}
					}
				}			// finally closing
			}		
	}
		
	
	@Override
	public void keyTyped(KeyEvent e) {
	// numeric value for userID
		if(e.getKeyChar()>='a' && e.getKeyChar()<='z') {
			txtuid.setEditable(false);
		}
		else {
			txtuid.setEditable(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			openDashboard();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
