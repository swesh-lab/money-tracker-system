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
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;

import application.run.*;
import application.dbtask.Operation;
import java.sql.*;
import javax.swing.JDesktopPane;
import javax.swing.ButtonGroup;

public class Dashboard extends JFrame implements ActionListener{

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	JMenuItem menulogout;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JLabel lblusername;
	static int userIDonDashboard;			// stores final userID after logIn or signUp
	
	LogIn login = new LogIn();
	int loguserid = LogIn.userid;	// userID via logIn frame
	
	SignUp signup = new SignUp();
	int signuserid = SignUp.userid;	// userID via signUp frame
	
	
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		con = Operation.createConnection();
		dashboardFrame();
	}

	private void dashboardFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/application/image/icon.png")));
		setTitle("Money Tracker System- Dashboard");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1390, 860);
		
		JMenuBar menubar = new JMenuBar();
		menubar.setToolTipText("");
		menubar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menubar.setBorder(new MatteBorder(4, 1, 2, 1, (Color) new Color(128, 0, 128)));
		menubar.setForeground(new Color(128, 0, 128));
		menubar.setBackground(new Color(255, 255, 255));
		menubar.setFont(new Font("Open Sans", Font.BOLD, 20));
		setJMenuBar(menubar);
		
		JMenu menuexpensecategory = new JMenu("Expense Category");
		menuexpensecategory.setFont(new Font("Raleway", Font.BOLD, 15));
		menuexpensecategory.setForeground(new Color(128, 0, 128));
		menubar.add(menuexpensecategory);
		
			JMenuItem menuaddcategory = new JMenuItem("Add Expense Category");
			menuaddcategory.setFont(new Font("Raleway", Font.PLAIN, 15));
			menuaddcategory.setForeground(new Color(128, 0, 128));
			menuexpensecategory.add(menuaddcategory);
			menuaddcategory.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AddExpenseCategory expensecategory = new AddExpenseCategory();
					desktopPane.add(expensecategory);
					expensecategory.show();
					expensecategory.setLocation(0,0);
				}
			});
			
			JMenuItem menuviewcategory = new JMenuItem("View Expense Category");
			menuviewcategory.setFont(new Font("Raleway", Font.PLAIN, 15));
			menuviewcategory.setForeground(new Color(128, 0, 128));
			menuexpensecategory.add(menuviewcategory);
			menuviewcategory.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ViewExpenseCategory viewexpensecategory = new ViewExpenseCategory();
					desktopPane.add(viewexpensecategory);
					viewexpensecategory.show();
					viewexpensecategory.setLocation(0,0);
				}
			});
		
		JMenu menuexpense = new JMenu("Expense");
		menuexpense.setFont(new Font("Raleway", Font.BOLD, 15));
		menuexpense.setForeground(new Color(128, 0, 128));
		menubar.add(menuexpense);
		
			JMenuItem menuaddexpense = new JMenuItem("Add Expense");
			menuaddexpense.setFont(new Font("Raleway", Font.PLAIN, 15));
			menuaddexpense.setForeground(new Color(128, 0, 128));
			menuexpense.add(menuaddexpense);
			menuaddexpense.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AddExpense addexpense = new AddExpense();
					desktopPane.add(addexpense);
					addexpense.show();
					addexpense.setLocation(0,0);	
				}
			});
		
		JMenu menudeposit = new JMenu("Deposit");
		menudeposit.setFont(new Font("Raleway", Font.BOLD, 15));
		menudeposit.setForeground(new Color(128, 0, 128));
		menubar.add(menudeposit);
		
			JMenuItem menuadddeposit = new JMenuItem("Add Deposit Amount");
			menuadddeposit.setFont(new Font("Raleway", Font.PLAIN, 15));
			menuadddeposit.setForeground(new Color(128, 0, 128));
			menudeposit.add(menuadddeposit);
			menuadddeposit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AddDeposit adddeposit = new AddDeposit();
					desktopPane.add(adddeposit);
					adddeposit.show();
					adddeposit.setLocation(0,0);
					
				}
			});
		
		JMenu menuview = new JMenu("View Statement");
		menuview.setFont(new Font("Raleway", Font.BOLD, 15));
		menuview.setForeground(new Color(128, 0, 128));
		menubar.add(menuview);
		
			JCheckBoxMenuItem menudaily = new JCheckBoxMenuItem("Daily Basis");
			buttonGroup.add(menudaily);
			menudaily.setFont(new Font("Raleway", Font.PLAIN, 15));
			menudaily.setForeground(new Color(128, 0, 128));
			menuview.add(menudaily);
			menudaily.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					DailyBasis dailybasis = new DailyBasis();
					desktopPane.add(dailybasis);
					dailybasis.show();
					dailybasis.setLocation(0, 0);
				}
			});
			
			JCheckBoxMenuItem menumonthly = new JCheckBoxMenuItem("Monthly Basis");
			buttonGroup.add(menumonthly);
			menumonthly.setFont(new Font("Raleway", Font.PLAIN, 15));
			menumonthly.setForeground(new Color(128, 0, 128));
			menuview.add(menumonthly);
			menumonthly.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					MonthlyBasis monthlybasis = new MonthlyBasis();
					desktopPane.add(monthlybasis);
					monthlybasis.show();
					monthlybasis.setLocation(0, 0);
				}
			});
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		menubar.add(separator);
		
		// display userName/userID
		if (LogIn.submitButton==1) {
			userIDonDashboard = loguserid;									// store final userID on LogIn			
//			lblusername = new JLabel("Welcome!" + loguserid + " :) ");
		}
		
		else if (SignUp.submitButton==2) {
			userIDonDashboard = signuserid;									// stores final userID on SignUp		
//			lblusername = new JLabel("Welcome!" + signuserid +" :) ");
		}
		
		else {		
			JOptionPane.showMessageDialog(this, "Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		displayUserName(userIDonDashboard);
		
		lblusername.setForeground(new Color(255, 140, 0));
		lblusername.setFont(new Font("Martina", Font.PLAIN, 17));
		menubar.add(lblusername);
		
		JMenu menusettings = new JMenu("Settings");
		menusettings.setIcon(new ImageIcon(Dashboard.class.getResource("/application/image/settings.jpg")));
		menusettings.setBackground(new Color(255, 255, 255));
		menusettings.setFont(new Font("Raleway", Font.BOLD, 15));
		menusettings.setForeground(new Color(128, 0, 128));
		menubar.add(menusettings);
		
		JMenuItem menuprofile = new JMenuItem("Profile");
		menuprofile.setFont(new Font("Raleway", Font.PLAIN, 15));
		menuprofile.setForeground(new Color(128, 0, 128));
		menusettings.add(menuprofile);
		menuprofile.addActionListener(this);
		
		JMenuItem menuprivacy = new JMenuItem("Privacy Policy");
		menuprivacy.setFont(new Font("Raleway", Font.PLAIN, 15));
		menuprivacy.setForeground(new Color(128, 0, 128));
		menusettings.add(menuprivacy);
		menusettings.addSeparator();
		menuprivacy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Privacy privacy = new Privacy();
				privacy.setVisible(true);
			}
		});
		
		menulogout = new JMenuItem("Log Out");
		menulogout.setFont(new Font("Raleway", Font.PLAIN, 15));
		menulogout.setForeground(new Color(0, 0, 0));
		menulogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		menusettings.add(menulogout);
		contentPane = new JPanel();
//		contentPane.setBackground(new Color(125, 119, 202));
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		menulogout.addActionListener(this);
		
	
		JLabel lblrupee = new JLabel("\u20B9");
		lblrupee.setHorizontalAlignment(SwingConstants.CENTER);
		lblrupee.setForeground(new Color(238, 130, 238));
		lblrupee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblrupee.setBounds(976, 8, 31, 37);
		contentPane.add(lblrupee);
		
		JLabel label = new JLabel("MONEY TRACKER");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(238, 130, 238));
		label.setFont(new Font("Woodcut", Font.PLAIN, 30));
		label.setBackground(Color.WHITE);
		label.setBounds(510, 8, 485, 64);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("Money Tracking, Simplified");
		label_2.setHorizontalTextPosition(SwingConstants.CENTER);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(218, 112, 214));
		label_2.setFont(new Font("Rage Italic", Font.PLAIN, 26));
		label_2.setBounds(591, 63, 306, 29);
		contentPane.add(label_2);
		
		JLabel topgif = new JLabel("");
		topgif.setIcon(new ImageIcon(Dashboard.class.getResource("/application/image/particles.gif")));
		topgif.setBounds(777, 0, 752, 100);
		contentPane.add(topgif);
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/application/image/piggysave.gif"));
		Image image = icon.getImage();
		desktopPane = new JDesktopPane() {
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(image, 250, 0, 1280, 720, this);
			}
		};
		desktopPane.setBackground(new Color(125, 119, 202));
		desktopPane.setBounds(0, 102, 1532, 698);
		contentPane.add(desktopPane);
		
//		JLabel background = new JLabel("");
//		background.setIcon(new ImageIcon(Dashboard.class.getResource("/application/image/piggysave.gif")));
//		background.setHorizontalAlignment(SwingConstants.CENTER);
//		background.setBounds(250, 90, 1280, 720);
//		contentPane.add(background);
//		setLocationRelativeTo(null);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String menuname = e.getActionCommand();
		if (menuname.equals("Log Out")) {
			userIDonDashboard = 0;			// clear id after LogOut
			loguserid = 0;
			signuserid = 0;
			LogIn login = new LogIn();
			login.setVisible(true);
			this.dispose();
		}
		if (menuname.equals("Profile")) {
			Profile profile = new Profile();
			desktopPane.add(profile);
			profile.show();
			profile.setSize(profile.getWidth(), profile.getHeight());
			profile.setLocation(0,0);
		
		}
	}
	

	private void displayUserName (int userIDonDashboard) {
		
		try {
			String sql = "SELECT name FROM userdetails WHERE userID = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userIDonDashboard);
			rs = ps.executeQuery();
			while (rs.next()) {
				String userName = rs.getString("name");
				lblusername = new JLabel("Welcome! " + userName + " :) ");
			}
		}
		
		catch (SQLException sqlex) {
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
			
			if (con != null) {
				try {
				con.close();
				}
				catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		
	}

}
