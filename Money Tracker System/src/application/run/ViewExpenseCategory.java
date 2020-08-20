package application.run;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
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

import java.sql.*;
import application.beans.ExpenseCategory;
import application.dbtask.Operation;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;

public class ViewExpenseCategory extends JInternalFrame implements ActionListener{
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	Dashboard dashboard = new Dashboard();
	int id = Dashboard.userIDonDashboard;
	
	ExpenseCategory expenseCategory;
	private JTable table;
	DefaultTableModel model=new DefaultTableModel();
	String[] cols = new String[] {
			"Category ID", "Name", "Description"
	};
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewExpenseCategory frame = new ViewExpenseCategory();
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
	public ViewExpenseCategory() {
		
		Image icon = new ImageIcon(ViewExpenseCategory.class.getResource("/application/image/rupee.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon frameIcon = new ImageIcon(icon); 
		setFrameIcon(frameIcon);
		getContentPane().setBackground(new Color(125, 119, 202));
		setResizable(false);
		con = Operation.createConnection();		
		viewExpenseCategoryFrame();
	}
	
	public void viewExpenseCategoryFrame() {
		setOpaque(true);
		setClosable(true);
		setTitle("View Expense Category");
		setBounds(100, 100, 1533, 700);
		getContentPane().setLayout(null);
		
		// block InternalFrame to shift from NorthBar
		BasicInternalFrameUI internal = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
		for (MouseListener listener:internal.getNorthPane().getMouseListeners()) {
			internal.getNorthPane().removeMouseListener(listener);
					
		}
		
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 170, 948, 463);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(23);
		table.setSelectionForeground(Color.BLACK);
		table.setSelectionBackground(new Color(192, 192, 192));
		table.setForeground(new Color(0, 0, 100));
		table.setFont(new Font("Open Sans", Font.PLAIN, 14));
		table.setBackground(new Color(211, 211, 211));
		table.setFillsViewportHeight(true);
		
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Open Sans", Font.PLAIN, 16));
		
		model.setColumnIdentifiers(cols);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnrefresh = new JButton("Refresh");
		btnrefresh.setForeground(Color.WHITE);
		btnrefresh.setFont(new Font("Open Sans", Font.PLAIN, 15));
		btnrefresh.setBackground(Color.BLACK);
		btnrefresh.setBounds(1007, 170, 92, 29);
		getContentPane().add(btnrefresh);
		btnrefresh.addActionListener(this);		
		
		JLabel lblicon = new JLabel("");
		Image refresh = new ImageIcon(ViewExpenseCategory.class.getResource("/application/image/refresh.jpg")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon refreshIcon = new ImageIcon(refresh);
		lblicon.setIcon(refreshIcon);
		lblicon.setBounds(1109, 170, 45, 29);
		getContentPane().add(lblicon);
		
		JLabel lblViewExpenseCategory = new JLabel("View Expense Category");
		lblViewExpenseCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewExpenseCategory.setForeground(Color.BLACK);
		lblViewExpenseCategory.setFont(new Font("Neon Lights", Font.PLAIN, 40));
		lblViewExpenseCategory.setBounds(25, 63, 520, 53);
		getContentPane().add(lblViewExpenseCategory);
		
		JLabel lblimg = new JLabel("");
		lblimg.setHorizontalAlignment(SwingConstants.CENTER);
		lblimg.setIcon(new ImageIcon(ViewExpenseCategory.class.getResource("/application/image/category.png")));
		lblimg.setBounds(1246, 430, 265, 214);
		getContentPane().add(lblimg);
		
		JLabel lblwall = new JLabel("");
		lblwall.setIcon(new ImageIcon(ViewExpenseCategory.class.getResource("/application/image/wbg.jpg")));
		lblwall.setBounds(0, 0, 1521, 671);
		getContentPane().add(lblwall);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		fillCategoryTable(id);
	}
	
	public void setTableColumnWidth() {
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(40);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(400);
	}
	
	public void fillCategoryTable(int id) {
		setTableColumnWidth();
		
		while (model.getRowCount()>0){			// clear table on refresh
			model.removeRow(0);
		}
		
		ArrayList<ExpenseCategory>categorylist = new ArrayList<ExpenseCategory>();
		try {
			String sql = "SELECT `categoryID`, `name`, `description` FROM `expensecategory` WHERE `userID` = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			int flag = 0;
			while(rs.next()) {
				flag = 1;
				int categoryid = rs.getInt("categoryID");
				String name = rs.getString("name");
				String description = rs.getString("description");
				expenseCategory = new ExpenseCategory();
				expenseCategory.setCategoryID(categoryid);
				expenseCategory.setName(name);
				expenseCategory.setDescription(description);
				categorylist.add(expenseCategory);
				
			}
			if (flag == 0) {
				JOptionPane.showMessageDialog(this, "No Category Found! Add Category in 'Add Expense Category' Page.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(SQLException ex) {
			System.out.println(ex);
		}
		
		for (ExpenseCategory ec:categorylist) {
			model.addRow(new Object[]{
				ec.getCategoryID(), ec.getName(), ec.getDescription()
			});
		}
	
	}
}
