package application.run;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollBar;
import javax.swing.JFormattedTextField;
import java.awt.Cursor;
import java.awt.Point;

public class Privacy extends JFrame implements ActionListener {

	private JPanel contentPane;
	JButton btnclose;
	private final JLabel lblNewLabel_1 = new JLabel("");
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Privacy frame = new Privacy();
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
	public Privacy() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 603, 830);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><p>This Privacy Policy sets forth how personal information is collected and used. Unless otherwise indicated, this Privacy Policy only applies to personal information collected through the Money Tracker. \r\nBy using this system, you agree to be bound by this Privacy Policy and our Terms of Use, as they may be amended from time to time in the future.</p><br>\r\n<p>\r\n<b>We collect the following personal information from you:</b> <br><br>\r\n\u2022\tcontact information such as name, email, address, contact number; <br>\r\n\u2022\texpense and deposit information; <br>\r\n\u2022\tunique identifiers such as user id, password; <br>\r\n\u2022\tpreferences information such as categories, notes and <br>\r\n\u2022\tdemographic information such as age, gender.<br>\r\n\r\n</p> <br>\r\n<p>\r\n<b>Information Use and Sharing</b><br>\r\nMoney Tracker uses and discloses your personal information only as follows:<br><br>\r\n\u2022\tto analyze system usage and improve the service;<br>\r\n\u2022\tto avail you daily/monthly review of money spent;<br>\r\n\u2022\tto fulfill your requests for certain services;\r\n</p>\r\n<br>\r\n<p><b>Changes to this Privacy Policy</b><br>\r\nWe may update this privacy policy to reflect changes to our information practices. If we make any material changes we will notify you by email or by means of a notice on this system prior to the change becoming effective. We encourage you to periodically review this page for the latest information on our privacy practices.</p><br>\r\n<p><b>Contact Us</b>\r\n<br>If you have any questions about our Privacy Policy, mail us at <b><i>moneytracker@gmail.com</i></b><br>\r\n<br><b>@Money Tracker</b>\r\n\r\n</html>");
		lblNewLabel.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(54, 193, 493, 550);
		contentPane.add(lblNewLabel);
		
		btnclose = new JButton("Okay, Got It!");
		btnclose.setBackground(new Color(255, 255, 255));
		btnclose.setBorder(new LineBorder(new Color(247, 217, 229), 8));
		btnclose.setFont(new Font("Open Sans", Font.BOLD, 14));
		btnclose.setBounds(26, 783, 550, 37);
		contentPane.add(btnclose);
		btnclose.addActionListener(this);
		
		JLabel lblnotice = new JLabel("Privacy Notice");
		lblnotice.setHorizontalTextPosition(SwingConstants.CENTER);
		lblnotice.setForeground(new Color(204, 0, 204));
		lblnotice.setHorizontalAlignment(SwingConstants.CENTER);
		lblnotice.setFont(new Font("Induction", Font.PLAIN, 25));
		lblnotice.setBounds(64, 127, 443, 42);
		contentPane.add(lblnotice);
		
		JLabel lbltitle = new JLabel("MONEY TRACKER");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setForeground(new Color(128, 0, 128));
		lbltitle.setFont(new Font("Woodcut", Font.PLAIN, 28));
		lbltitle.setBackground(Color.WHITE);
		lbltitle.setBounds(46, 29, 485, 64);
		contentPane.add(lbltitle);
		
		JLabel lblrupee = new JLabel("\u20B9");
		lblrupee.setHorizontalAlignment(SwingConstants.CENTER);
		lblrupee.setForeground(new Color(128, 0, 128));
		lblrupee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblrupee.setBounds(494, 29, 31, 37);
		contentPane.add(lblrupee);
		
		JLabel label = new JLabel("Money Tracking, Simplified");
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(153, 0, 153));
		label.setFont(new Font("Rage Italic", Font.PLAIN, 25));
		label.setBounds(138, 83, 306, 29);
		contentPane.add(label);
		
		JLabel lblsub = new JLabel("Effective March 15, 2020");
		lblsub.setHorizontalTextPosition(SwingConstants.CENTER);
		lblsub.setHorizontalAlignment(SwingConstants.CENTER);
		lblsub.setForeground(new Color(153, 51, 153));
		lblsub.setFont(new Font("Open Sans", Font.ITALIC, 12));
		lblsub.setBounds(193, 162, 188, 27);
		contentPane.add(lblsub);
		
		JLabel privacybg = new JLabel("");
		privacybg.setLocation(new Point(0, 20));
		privacybg.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		privacybg.setHorizontalAlignment(SwingConstants.CENTER);
		privacybg.setIcon(new ImageIcon(Privacy.class.getResource("/application/image/privacybg.jpg")));
		privacybg.setBounds(0, -33, 603, 863);
		contentPane.add(privacybg);
		lblNewLabel_1.setIcon(new ImageIcon(Privacy.class.getResource("/application/image/lightbg.jpg")));
		lblNewLabel_1.setBounds(0, 825, 603, 40);
		contentPane.add(lblNewLabel_1);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
