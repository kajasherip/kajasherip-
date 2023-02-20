package com.gui;

import java.awt.EventQueue;
import com.dbutil.DBConnection;
import com.model.Sales;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.text.SimpleDateFormat;
import java.awt.Toolkit;

public class SalesFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cusIDTxtField;
	private JTextField dateTxtField;
	private JTextField productIDTxtField;

	Statement stmt;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Sales salesObj;
	String query;
	Date date;
	
	private String sequential_ID ;
	int count = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesFrame frame = new SalesFrame();
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
	public SalesFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Games\\login.png"));
		setResizable(false);
		setTitle("Sales Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 505);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\Games\\icons\\Money-Increase-icon.png"));
		lblNewLabel.setBounds(10, 56, 280, 264);
		contentPane.add(lblNewLabel);
		
		JLabel cusIDLabel = new JLabel("Customer_ID");
		cusIDLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		cusIDLabel.setBounds(349, 89, 134, 35);
		contentPane.add(cusIDLabel);
		
		JLabel dateLabel = new JLabel("Order_Date");
		dateLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		dateLabel.setBounds(349, 154, 135, 35);
		contentPane.add(dateLabel);
		
		JLabel productIDLabel = new JLabel("Product_ID");
		productIDLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		productIDLabel.setBounds(349, 217, 134, 35);
		contentPane.add(productIDLabel);
		
		cusIDTxtField = new JTextField();
		cusIDTxtField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		cusIDTxtField.setBounds(528, 89, 269, 29);
		contentPane.add(cusIDTxtField);
		cusIDTxtField.setColumns(10);
		
		dateTxtField = new JTextField();
		dateTxtField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		dateTxtField.setBounds(528, 154, 269, 29);
		contentPane.add(dateTxtField);
		dateTxtField.setColumns(10);
		
		productIDTxtField = new JTextField();
		productIDTxtField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		productIDTxtField.setBounds(528, 217, 269, 29);
		contentPane.add(productIDTxtField);
		productIDTxtField.setColumns(10);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = DBConnection.getConnection();
				query = "SELECT MAX(CUSTOMER_ID) FROM SALES";
				stmt = null;
				salesObj = new Sales();
				try {
					 stmt = con.createStatement();
					 rs = stmt.executeQuery(query);
					 rs.next();
					 if(rs.getString(1)==null) {
						 System.out.println("No records");
						 Scanner sc = new Scanner(System.in);
						 System.out.println("Value = ");
						 sequential_ID = sc.next();
						 salesObj.setCustomer_ID("C#" +  sequential_ID);
						 cusIDTxtField.setText(salesObj.getCustomer_ID());
						 System.out.println("Value1 = ");
						 sequential_ID = sc.next();
						 salesObj.setCustomer_ID("P#" +  sequential_ID);
						 productIDTxtField.setText(salesObj.getCustomer_ID());

					 }
					 else{
						 Scanner sc = new Scanner(System.in);
						 System.out.println("Value = ");
						 sequential_ID = sc.next();
						 salesObj.setCustomer_ID("C#" + sequential_ID);
						 cusIDTxtField.setText(salesObj.getCustomer_ID());
						 System.out.println("Value1 = ");
						 sequential_ID = sc.next();
						 salesObj.setCustomer_ID("P#" +  sequential_ID);
						 productIDTxtField.setText(salesObj.getCustomer_ID());
					 }
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				resetControls();
			}
		});
		addButton.setIcon(new ImageIcon("E:\\Games\\icons\\Addcustomer.png"));
		addButton.setMnemonic('A');
		addButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		addButton.setBounds(42, 383, 111, 35);
		contentPane.add(addButton);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save Button Clicked");
				con = DBConnection.getConnection();
				query = "SELECT MAX(CUSTOMER_ID) FROM MEMBERS";
				stmt = null;		
				if(cusIDTxtField.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Sales ID Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					cusIDTxtField.requestFocus();
				}
				else if(dateTxtField.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Sales Date Empty", "Error", JOptionPane.ERROR_MESSAGE);
					dateTxtField.requestFocus();
				}
				else if(productIDTxtField.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Sales Product_ID Empty", "Error", JOptionPane.ERROR_MESSAGE);
					productIDTxtField.requestFocus();
				}
				else {
					date = new Date();
					//System.out.println(date);
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
					System.out.println("Date = " + sdf1.format(date) + "\n");
					SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
					System.out.println("Month = " + sdf2.format(date) + "\n");
			        SimpleDateFormat sdf3 = new SimpleDateFormat("YYYY");
					System.out.println("Year = " + sdf3.format(date) + "\n");
					String dateField = sdf3.format(date)+"-"+sdf2.format(date)+"-" +sdf1.format(date);
					salesObj.setCustomer_ID(cusIDTxtField.getText());
					salesObj.setOrder_Date(dateField);
					salesObj.setProduct_ID(productIDTxtField.getText());
				}
				try {
					ps = con.prepareStatement("INSERT INTO SALES (CUSTOMER_ID, ORDER_DATE, PRODUCT_ID)" + "VALUES(?, ?, ?)");
					ps.setString(1, salesObj.getCustomer_ID());
					ps.setString(2, salesObj.getOrder_Date());
					ps.setString(3, salesObj.getproduct_ID());
					count = ps.executeUpdate(); // Error
					if(count != 0) {
						JOptionPane.showMessageDialog(null,count + " New Sales Added...", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, " Record Insertion Failed", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		saveButton.setIcon(new ImageIcon("E:\\Games\\icons\\Saveicon.png"));
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		saveButton.setMnemonic('S');
		saveButton.setBounds(206, 383, 111, 35);
		contentPane.add(saveButton);
		
		JButton viewButton = new JButton("View");
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("View Button Cleicked");
				ViewSales viewSales = new ViewSales();
				viewSales.setVisible(true);
				dispose();
			}
		});
		viewButton.setIcon(new ImageIcon("E:\\Games\\icons\\View.png"));
		viewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		viewButton.setMnemonic('V');
		viewButton.setBounds(368, 383, 111, 35);
		contentPane.add(viewButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete Button Clicked");
				if(!productIDTxtField.getText().equals(" ")) {
					int confirmation = JOptionPane.showConfirmDialog(null, "Sales Confirm Delete ?","Delete Sales",JOptionPane.YES_NO_OPTION);
					if(confirmation == 0) {
						query = "DELETE FROM SALES WHERE CUSTOMER_ID=?";
						try {
							ps = con.prepareStatement(query);
							ps.setString(1, productIDTxtField.getText());
							count = ps.executeUpdate();
							if(count != 0) {
								JOptionPane.showMessageDialog(null, "Sales Deleted...","Delete Sales",JOptionPane.INFORMATION_MESSAGE);
							}
						}
						catch(SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		deleteButton.setIcon(new ImageIcon("E:\\Games\\icons\\Delete.png"));
		deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		deleteButton.setMnemonic('D');
		deleteButton.setBounds(516, 383, 119, 35);
		contentPane.add(deleteButton);
		
		JButton btnNewButton_4 = new JButton("Close");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_4.setIcon(new ImageIcon("E:\\Games\\icons\\Close1.png"));
		btnNewButton_4.setMnemonic('C');
		btnNewButton_4.setBounds(678, 383, 119, 35);
		contentPane.add(btnNewButton_4);
	}
	public List<Sales> getSalesDetails() {
		List<Sales> salesList = new ArrayList<Sales>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM SALES";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Sales salesObj = new Sales();
				salesObj.setCustomer_ID(rs.getString("CUSTOMER_ID"));
				salesObj.setOrder_Date(rs.getString("ORDER_DATE"));
				salesObj.setProduct_ID(rs.getString("PRODUCT_ID"));
				salesList.add(salesObj);
			}
		}
		catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return salesList;
	}
	public void getSalesDetailById(String customerId) {
		List<Sales> salesList = new ArrayList<Sales>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM SALES WHERE CUSTOMER_ID = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Sales salesObj = new Sales();
				salesObj.setCustomer_ID(rs.getString("CUSTOMER_ID"));
				salesObj.setOrder_Date(rs.getString("ORDER_DATE"));
				salesObj.setProduct_ID(rs.getString("PRODUCT_ID"));
				salesList.add(salesObj);
			}
			cusIDTxtField.setText(salesList.get(0).getCustomer_ID());
			dateTxtField.setText(salesList.get(0).getOrder_Date());
			productIDTxtField.setText(salesList.get(0).getproduct_ID());
		}
		catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	}
	private void resetControls() {
		dateTxtField.setText("");
		dateTxtField.requestFocus();
	}
}
