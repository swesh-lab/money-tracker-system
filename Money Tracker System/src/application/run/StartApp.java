/*
 * This system allows the user to view daily/monthly review of money spent and can analyze his/her expense.
 * Created by Sweta Sharma on 10/07/2020
 *  
 * */

package application.run;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class StartApp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartApp frame = new StartApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public StartApp() {
		startAppFrame();
		load();
	}

	public void startAppFrame() {
		setUndecorated(true);		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 318);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		contentPane.setIgnoreRepaint(true);
		contentPane.setBackground(new Color(12, 12, 12));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblrupee = new JLabel("\u20B9");
		lblrupee.setForeground(new Color(101, 227, 227));
		lblrupee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblrupee.setHorizontalAlignment(SwingConstants.CENTER);
		lblrupee.setBounds(365, 42, 31, 37);
		contentPane.add(lblrupee);
		
		JLabel lbltitle = new JLabel("MONEY TRACKER");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setForeground(new Color(101, 227, 227));
		lbltitle.setFont(new Font("Woodcut", Font.PLAIN, 21));
		lbltitle.setBounds(29, 50, 363, 43);
		contentPane.add(lbltitle);
		
		JLabel lblsubtitle = new JLabel("Money Tracking, Simplified");
		lblsubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblsubtitle.setBounds(49, 88, 323, 23);
		lblsubtitle.setFont(new Font("Rage Italic", Font.PLAIN, 24));
		lblsubtitle.setForeground(new Color(101, 227, 227));
		contentPane.add(lblsubtitle);		
		
		JLabel lblloading = new JLabel("");
		lblloading.setIcon(new ImageIcon(StartApp.class.getResource("/application/image/loading.gif")));
		lblloading.setHorizontalAlignment(SwingConstants.CENTER);
		lblloading.setBounds(0, 40, 425, 278);
		contentPane.add(lblloading);

	}
	
	private void load() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1600);
					LogIn login = new LogIn();
					login.setVisible(true);
					StartApp.this.dispose();
				}
				catch (InterruptedException iex) {
					iex.printStackTrace();
				}
			}
			
		}).start();
		
	}
}
