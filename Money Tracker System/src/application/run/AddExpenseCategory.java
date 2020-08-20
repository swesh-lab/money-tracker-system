package application.run;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.Highlighter;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;

import application.dbtask.Operation;
import application.run.*;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AddExpenseCategory extends JInternalFrame implements ActionListener{

	private Connection con;
	private PreparedStatement ps;
	private JTextField txtcategoryid;
	private JTextField txtname;
	private JTextArea txtdesc;
	
	Dashboard dashboard = new Dashboard();
	int id = Dashboard.userIDonDashboard;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddExpenseCategory frame = new AddExpenseCategory();
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
	public AddExpenseCategory() {
		Image icon = new ImageIcon(AddExpenseCategory.class.getResource("/application/image/rupee.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon frameIcon = new ImageIcon(icon); 
		setFrameIcon(frameIcon);
		getContentPane().setBackground(new Color(125, 119, 202));
		setResizable(false);
		con = Operation.createConnection();
		addExpenseCategoryFrame();
	}
	
	public void addExpenseCategoryFrame() {
		setOpaque(true);
		setClosable(true);
		setTitle("Add Expense Category");
		setBounds(100, 100, 1533, 700);
		getContentPane().setLayout(null);
		
		// block InternalFrame to shift from NorthBar
		BasicInternalFrameUI internal = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
		for (MouseListener listener:internal.getNorthPane().getMouseListeners()) {
			internal.getNorthPane().removeMouseListener(listener);
					
		}
		
		JLabel lbloptional = new JLabel("(Optional)");
		lbloptional.setFont(new Font("Open Sans", Font.PLAIN, 11));
		lbloptional.setBounds(946, 328, 61, 13);
		getContentPane().add(lbloptional);
		
		JLabel lbladd = new JLabel("Add Expense Category");
		lbladd.setForeground(Color.BLACK);
		lbladd.setFont(new Font("Neon Lights", Font.PLAIN, 40));
		lbladd.setHorizontalAlignment(SwingConstants.CENTER);
		lbladd.setBackground(new Color(255, 0, 0));
		lbladd.setBounds(852, 70, 507, 53);
		getContentPane().add(lbladd);
		
		JLabel lblimg = new JLabel("");
		lblimg.setHorizontalAlignment(SwingConstants.CENTER);
		lblimg.setIcon(new ImageIcon(AddExpenseCategory.class.getResource("/application/image/category.png")));
		lblimg.setBounds(23, 186, 220, 232);
		getContentPane().add(lblimg);
		
		JLabel lblid = new JLabel("Category ID");
		lblid.setForeground(Color.BLACK);
		lblid.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblid.setBounds(946, 219, 95, 27);
		getContentPane().add(lblid);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblName.setBounds(946, 259, 95, 27);
		getContentPane().add(lblName);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.BLACK);
		lblDescription.setFont(new Font("Open Sans", Font.BOLD, 16));
		lblDescription.setBounds(946, 301, 95, 27);
		getContentPane().add(lblDescription);
		
		txtcategoryid = new JTextField();
		txtcategoryid.setEditable(false);
		txtcategoryid.setHorizontalAlignment(SwingConstants.CENTER);
		txtcategoryid.setForeground(Color.WHITE);
		txtcategoryid.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtcategoryid.setColumns(10);
		txtcategoryid.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
		txtcategoryid.setBackground(Color.BLACK);
		txtcategoryid.setBounds(1096, 219, 220, 27);
		getContentPane().add(txtcategoryid);
		fillCategoryID();
		
		txtname = new JTextField();
		txtname.setOpaque(false);
		txtname.setHorizontalAlignment(SwingConstants.CENTER);
		txtname.setForeground(Color.WHITE);
		txtname.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtname.setColumns(10);
		txtname.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
		txtname.setBackground(Color.BLACK);
		txtname.setBounds(1096, 261, 220, 27);
		getContentPane().add(txtname);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(1096, 309, 220, 105);
		getContentPane().add(scrollPane);
		
		txtdesc = new JTextArea();
		txtdesc.setBackground(new Color(125, 119, 202));
		txtdesc.setLineWrap(true);
		scrollPane.setViewportView(txtdesc);
		txtdesc.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtdesc.setForeground(Color.WHITE);
		txtdesc.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtdesc.setWrapStyleWord(true);
		
		JButton btnadd = new JButton("Add Category");
		btnadd.setMnemonic(KeyEvent.VK_ENTER);
		btnadd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnadd.setForeground(new Color(218,112, 214));
		btnadd.setFont(new Font("Open Sans", Font.PLAIN, 16));
		btnadd.setBorder(new LineBorder(new Color(153, 51, 153), 1, true));
		btnadd.setBackground(Color.BLACK);
		btnadd.setBounds(1174, 465, 142, 37);
		getContentPane().add(btnadd);
		btnadd.addActionListener(this);
		
		JLabel lblsubtitle = new JLabel("<html><p>It takes seconds to record daily transactions. Put them into clear and visualized <b>categories</b> such as <i>Expense: Food, Shopping, Gift</i> and so on. </p></html>");
		lblsubtitle.setForeground(Color.WHITE);
		lblsubtitle.setFont(new Font("Rondalo", Font.PLAIN, 22));
		lblsubtitle.setBounds(254, 240, 489, 122);
		getContentPane().add(lblsubtitle);
		
		JLabel lblwall = new JLabel("");
		lblwall.setIcon(new ImageIcon(AddExpenseCategory.class.getResource("/application/image/particles.gif")));
		lblwall.setBounds(-256, 301, 925, 441);
		getContentPane().add(lblwall);
		
		
	}
	
	int categoryid;
	private void fillCategoryID() {
		// auto allot categoryID
		try {
			String allotID = "SELECT categoryID FROM expensecategory ORDER BY categoryID desc limit 1;";
			ps = con.prepareStatement(allotID);
			ResultSet value = ps.executeQuery();
			if (value.next()) {
				categoryid = value.getInt("categoryID")+1;
				txtcategoryid.setText(String.valueOf(categoryid));
			}
		}
		catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		addCategory(id);
	}

	private void addCategory(int id) {
			String name = txtname.getText().trim();
			String description = txtdesc.getText().trim();
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Expense Category Name Missing", "Missing Field", JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					String addQuery = "INSERT INTO expensecategory (`userID`, `categoryID`, `name`, `description`) VALUES (?,?,?,?)";
					ps = con.prepareStatement(addQuery);
					ps.setInt(1, id);
					ps.setInt(2, categoryid);
					ps.setString(3, name);
					ps.setString(4, description);
					
					int value = ps.executeUpdate();
					if (value>0) {
						ImageIcon icon = new ImageIcon(AddExpenseCategory.class.getResource("/application/image/checked.png"));
						JOptionPane.showMessageDialog(this, "Expense Category Added Successfully", "Added", JOptionPane.PLAIN_MESSAGE, icon);
						fillCategoryID();
						txtname.setText("");
						txtdesc.setText("");
					}
				}
				catch(SQLException sqlex) {
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
		}
			
	
}
