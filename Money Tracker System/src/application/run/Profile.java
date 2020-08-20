package application.run;

import java.awt.EventQueue;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;

import java.awt.Button;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import java.sql.*;
import application.run.*;
import application.dbtask.Operation;

public class Profile extends JInternalFrame implements ActionListener, MouseListener{
	private JTextField txtname;
	private JTextField txtnumber;
	private JTextField txtaddress;
	private JTextField txtcity;
	private JTextField txtmail;
	private JTextField txtgender;
	private JTextField txtid;
	private JPasswordField txtpassword;

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	Dashboard dashboard = new Dashboard();
	int id = Dashboard.userIDonDashboard;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Profile frame = new Profile();
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
	
	public Profile() {
		getContentPane().setBackground(new Color(125, 119, 202));
		setResizable(false);
		con = Operation.createConnection();
		profileFrame();
	}
	
	public void profileFrame() {

		setOpaque(true);
		Image icon = new ImageIcon(Profile.class.getResource("/application/image/profile.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon frameIcon = new ImageIcon(icon); 
		setFrameIcon(frameIcon);
		setTitle("Profile");
		setClosable(true);
		setBounds(100, 100, 1533, 700);
		getContentPane().setLayout(null);
		
		// block InternalFrame to shift from NorthBar
		BasicInternalFrameUI internal = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
		for (MouseListener listener:internal.getNorthPane().getMouseListeners()) {
			internal.getNorthPane().removeMouseListener(listener);
			
		}
		
		JLabel refreshImg = new JLabel("");
		Image refresh = new ImageIcon(Profile.class.getResource("/application/image/refresh.jpg")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon refreshIcon = new ImageIcon(refresh);
		refreshImg.setIcon(refreshIcon);
		refreshImg.setBounds(641, 116, 45, 29);
		getContentPane().add(refreshImg);
		

		JButton btnViewProfile = new JButton("Refresh");
		btnViewProfile.setForeground(Color.LIGHT_GRAY);
		btnViewProfile.setBackground(Color.BLACK);
		btnViewProfile.setFont(new Font("Open Sans", Font.PLAIN, 15));
		btnViewProfile.setBounds(539, 116, 92, 29);
		getContentPane().add(btnViewProfile);
		btnViewProfile.addActionListener(this);
		
		JLabel lblTitle = new JLabel("View / Update Profile");
		lblTitle.setForeground(new Color(128, 0, 128));
		lblTitle.setFont(new Font("Neon Lights", Font.PLAIN, 40));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(476, 64, 555, 53);
		getContentPane().add(lblTitle);
		
		JButton btnView = new JButton("View");
		btnView.setToolTipText("View Password");
		btnView.setOpaque(false);
		btnView.setHorizontalTextPosition(SwingConstants.CENTER);
		btnView.setForeground(new Color(102, 0, 102));
		btnView.setFont(new Font("Open Sans", Font.PLAIN, 15));
		btnView.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnView.setBackground(new Color(255, 102, 255));
		btnView.setBounds(900, 442, 65, 34);
		getContentPane().add(btnView);
		btnView.addMouseListener(this);
		
		JLabel viewbtngif = new JLabel("");
		viewbtngif.setIcon(new ImageIcon(Profile.class.getResource("/application/image/button.gif")));
		viewbtngif.setHorizontalAlignment(SwingConstants.CENTER);
		viewbtngif.setBounds(900, 442, 65, 34);
		getContentPane().add(viewbtngif);
		
		JButton btnChange = new JButton("Change");
		btnChange.setToolTipText("Change Your Contact Number");
		btnChange.setOpaque(false);
		btnChange.setHorizontalTextPosition(SwingConstants.CENTER);
		btnChange.setForeground(new Color(102, 0, 102));
		btnChange.setFont(new Font("Open Sans", Font.PLAIN, 15));
		btnChange.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnChange.setBackground(new Color(255, 102, 255));
		btnChange.setBounds(900, 248, 65, 34);
		getContentPane().add(btnChange);
		btnChange.addActionListener(this);
		
		JLabel changebtngif = new JLabel("");
		changebtngif.setIcon(new ImageIcon(Profile.class.getResource("/application/image/button.gif")));
		changebtngif.setHorizontalAlignment(SwingConstants.CENTER);
		changebtngif.setBounds(900, 248, 65, 34);
		getContentPane().add(changebtngif);
		
		txtgender = new JTextField();
		txtgender.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtgender.setEditable(false);
		txtgender.setOpaque(false);
		txtgender.setHorizontalAlignment(SwingConstants.CENTER);
		txtgender.setForeground(new Color(139, 0, 139));
		txtgender.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtgender.setBackground(new Color(216, 191, 216));
		txtgender.setBounds(701, 404, 189, 34);
		getContentPane().add(txtgender);
		
		JLabel lblname = new JLabel("Name");
		lblname.setForeground(new Color(139, 0, 139));
		lblname.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblname.setBounds(550, 209, 72, 29);
		getContentPane().add(lblname);
		
		JLabel lblnumber = new JLabel("Contact Number");
		lblnumber.setForeground(new Color(139, 0, 139));
		lblnumber.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblnumber.setBounds(550, 248, 133, 29);
		getContentPane().add(lblnumber);
		
		JLabel lbladdress = new JLabel("Address");
		lbladdress.setForeground(new Color(139, 0, 139));
		lbladdress.setFont(new Font("Open Sans", Font.BOLD, 16));
		lbladdress.setBounds(550, 287, 72, 29);
		getContentPane().add(lbladdress);
		
		JLabel lblcity = new JLabel("City");
		lblcity.setForeground(new Color(139, 0, 139));
		lblcity.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblcity.setBounds(550, 326, 72, 29);
		getContentPane().add(lblcity);
		
		JLabel lblmail = new JLabel("E-Mail");
		lblmail.setForeground(new Color(139, 0, 139));
		lblmail.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblmail.setBounds(550, 365, 72, 29);
		getContentPane().add(lblmail);
		
		JLabel lblgender = new JLabel("Gender");
		lblgender.setForeground(new Color(139, 0, 139));
		lblgender.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblgender.setBounds(550, 404, 72, 29);
		getContentPane().add(lblgender);
		
		JLabel lblpassword = new JLabel("Password");
		lblpassword.setForeground(new Color(139, 0, 139));
		lblpassword.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblpassword.setBounds(550, 441, 109, 29);
		getContentPane().add(lblpassword);
		
		txtname = new JTextField();
		txtname.setEditable(false);
		txtname.setOpaque(false);
		txtname.setHorizontalAlignment(SwingConstants.CENTER);
		txtname.setForeground(new Color(139, 0, 139));
		txtname.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtname.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtname.setBackground(new Color(216, 191, 216));
		txtname.setBounds(701, 209, 189, 34);
		getContentPane().add(txtname);
		
		txtnumber = new JTextField();
		txtnumber.setEditable(false);
		txtnumber.setOpaque(false);
		txtnumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtnumber.setForeground(new Color(139, 0, 139));
		txtnumber.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtnumber.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtnumber.setBackground(new Color(216, 191, 216));
		txtnumber.setBounds(701, 248, 189, 34);
		getContentPane().add(txtnumber);
		
		txtaddress = new JTextField();
		txtaddress.setEditable(false);
		txtaddress.setOpaque(false);
		txtaddress.setHorizontalAlignment(SwingConstants.CENTER);
		txtaddress.setForeground(new Color(139, 0, 139));
		txtaddress.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtaddress.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtaddress.setBackground(new Color(216, 191, 216));
		txtaddress.setBounds(701, 287, 189, 34);
		getContentPane().add(txtaddress);
		
		txtcity = new JTextField();
		txtcity.setEditable(false);
		txtcity.setOpaque(false);
		txtcity.setHorizontalAlignment(SwingConstants.CENTER);
		txtcity.setForeground(new Color(139, 0, 139));
		txtcity.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtcity.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtcity.setBackground(new Color(216, 191, 216));
		txtcity.setBounds(701, 326, 189, 34);
		getContentPane().add(txtcity);
		
		txtmail = new JTextField();
		txtmail.setEditable(false);
		txtmail.setOpaque(false);
		txtmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtmail.setForeground(new Color(139, 0, 139));
		txtmail.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtmail.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtmail.setBackground(new Color(216, 191, 216));
		txtmail.setBounds(701, 365, 189, 34);
		getContentPane().add(txtmail);
		
		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setOpaque(false);
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setForeground(new Color(139, 0, 139));
		txtid.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtid.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtid.setBackground(new Color(255, 228, 225));
		txtid.setBounds(701, 172, 189, 34);
		getContentPane().add(txtid);
//		txtid.setText(String.valueOf(id));
		
		JLabel lblid = new JLabel("ID");
		lblid.setForeground(new Color(139, 0, 139));
		lblid.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblid.setBounds(550, 170, 72, 29);
		getContentPane().add(lblid);
		
		txtpassword = new JPasswordField();
		txtpassword.setEditable(false);
		txtpassword.setOpaque(false);
		txtpassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtpassword.setForeground(new Color(139, 0, 139));
		txtpassword.setFont(new Font("Open Sans", Font.PLAIN, 16));
		txtpassword.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtpassword.setBackground(new Color(216, 191, 216));
		txtpassword.setBounds(701, 442, 189, 34);
		getContentPane().add(txtpassword);
		
		JLabel lblcoin = new JLabel("");
		lblcoin.setHorizontalAlignment(SwingConstants.CENTER);
		lblcoin.setIcon(new ImageIcon(Profile.class.getResource("/application/image/coin.gif")));
		lblcoin.setBounds(308, 246, 216, 256);
		getContentPane().add(lblcoin);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(Profile.class.getResource("/application/image/lightbg.jpg")));
		background.setBounds(0, 0, 1521, 671);
		getContentPane().add(background);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		if (buttonName.equals("Refresh")) {
			fillUserDetails(id);
		}
		
		if (buttonName.equals("Change")) {
			changeContactNumber(txtnumber.getText());
		}
	}
	

	private void fillUserDetails(int id) {
			try {
			String sql = "SELECT * FROM userdetails WHERE userID = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				txtid.setText(rs.getString(String.valueOf("userID")));
				txtname.setText(rs.getString("name"));
				txtnumber.setText(rs.getString("contact"));
				txtaddress.setText(rs.getString("address"));
				txtcity.setText(rs.getString("city"));
				txtmail.setText(rs.getString("mail"));
				txtgender.setText(rs.getString("gender"));
				txtpassword.setText(rs.getString("password"));
			}
			else {
				JOptionPane.showMessageDialog(this, "Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(SQLException sqlex) {
			System.out.println(sqlex);
		}
	}
	
	private void changeContactNumber(String txtnumber) {
		if (txtnumber.equals("")) {
			JOptionPane.showMessageDialog(this, "Refresh Profile To Change Contact Number", "Refresh Undone", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			String newContactNumber = JOptionPane.showInputDialog(this, "Enter New Contact Number: ", "Change Contact Number", JOptionPane.PLAIN_MESSAGE);
			if (newContactNumber.matches("[0-9]+")) {			// matches complete input number 
				try {
					String update = "UPDATE userdetails set contact=? WHERE contact=?";
					ps = con.prepareStatement(update);
					ps.setString(1, newContactNumber);
					ps.setString(2, txtnumber);
					int value = ps.executeUpdate();
					
					if (value>0) {
						ImageIcon updateIcon = new ImageIcon(SignUp.class.getResource("/application/image/checked.png"));
						JOptionPane.showMessageDialog(this, "Contact Number Changed Successfully. Refresh Profile.", "Update", JOptionPane.PLAIN_MESSAGE , updateIcon);
					}
					
				}
				catch (SQLException sqle) {
					System.out.println(sqle);
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

			else {
				JOptionPane.showMessageDialog(this, "Contact Number Must Be A Digit", "Invalid Input", JOptionPane.WARNING_MESSAGE);
			}
		}

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		String showPassword = String.valueOf(txtpassword.getPassword());
		txtpassword.setEchoChar((char)0);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		txtpassword.setEchoChar('•');					//		• (ALT + 0149)  \u2022
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

