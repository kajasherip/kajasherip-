package com.gui;
import com.dbutil.DBConnection;
import com.model.Customer;
import java.awt.EventQueue;
import java.lang.Integer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel txtArea;
	private JTextField txtCusID;
	private JTextField txtCusName;
	private JTextField txtEmail;
	private JTextField txtFieldMobile_No;
	private JRadioButton maleRadioButton, femaleRadioButton;
	private ButtonGroup radioMaleFemaleButton;
	//JButton
	JTextArea txtAreaAddress;
	Connection con;
	Statement stmt;
	PreparedStatement ps;
	ResultSet rs;
	// create Model Object ...
	Customer customerObj;
	String query;
	private static int sequential_ID = 1000;
	int count = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
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
	public CustomerFrame() {
		setResizable(false);
		setBackground(new Color(221, 160, 221));
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Games\\icons\\Restaurant4.png"));
		setTitle("Customer Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 505);
		txtArea = new JPanel();
		txtArea.setBackground(new Color(238, 130, 238));
		txtArea.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(txtArea);
		txtArea.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("E:\\Games\\icons\\Restaurant1.png"));
		lblNewLabel.setBounds(10, 49, 250, 248);
		txtArea.add(lblNewLabel);
		
		txtCusID = new JTextField(); // Customer_ID  --- TextField ....
		txtCusID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtCusID.setBounds(561, 35, 224, 28);
		txtArea.add(txtCusID);
		txtCusID.setColumns(10);
		
		txtCusName = new JTextField();
		txtCusName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtCusName.setBounds(561, 77, 224, 28);
		txtArea.add(txtCusName);
		txtCusName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Customer_ID");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(378, 33, 151, 28);
		txtArea.add(lblNewLabel_1);
		
		JLabel iblNewLabel_2 = new JLabel("Customer_Name");
		iblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		iblNewLabel_2.setBounds(378, 77, 151, 28);
		txtArea.add(iblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(378, 153, 131, 28);
		txtArea.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_4.setBounds(378, 208, 131, 28);
		txtArea.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setBounds(378, 260, 131, 28);
		txtArea.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Mobile_No");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_6.setBounds(378, 352, 133, 28);
		txtArea.add(lblNewLabel_6);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtEmail.setBounds(561, 208, 224, 28);
		txtArea.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtAreaAddress = new JTextArea();
		txtAreaAddress.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtAreaAddress.setBounds(561, 264, 224, 77);
		txtArea.add(txtAreaAddress);
		
		txtFieldMobile_No = new JTextField(); // Mobile Number KeyListener Interface Action ....
		txtFieldMobile_No.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if(((key < '0') || (key > '9')) && (key != KeyEvent.VK_BACK_SPACE) || (txtFieldMobile_No.getText().length() > 9)) {
					e.consume();                  // If it's not a number, ignore the event ...
				}
			}
		});
		txtFieldMobile_No.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtFieldMobile_No.setBounds(561, 352, 224, 26);
		txtArea.add(txtFieldMobile_No);
		txtFieldMobile_No.setColumns(10);
		
		JButton addCusButton = new JButton("Add Customer");
		addCusButton.addActionListener(new ActionListener() {    /// First JButton & Action Listener interface 
			public void actionPerformed(ActionEvent e) {
				        con = DBConnection.getConnection();
						// Query for setting Auto - Generated ID to the Customer....
						 query = "SELECT MAX(CUSTOMER_ID) FROM CUSTOMER";
						 stmt = null;
						 customerObj = new Customer();
						 try {
							 stmt = con.createStatement();
							 rs = stmt.executeQuery(query);
							 rs.next();
							 if(rs.getString(1) == null) {
								 System.out.println("No Record");
								 customerObj.setCustomer_ID("C#" + sequential_ID);
								 txtCusID.setText(customerObj.getCustomer_ID());
							 }
							 else {
								 sequential_ID = Integer.parseInt(rs.getString(1).substring(2, rs.getString(1).length())) + 1; // This is error code ...
								 customerObj.setCustomer_ID("C#" + sequential_ID);
								 txtCusID.setText(customerObj.getCustomer_ID());
							 }
						 }
						 catch(SQLException e1) {
							 e1.printStackTrace();
						 }
						 resetControls();
			}
		});
		addCusButton.setMnemonic('A');
		addCusButton.setIcon(new ImageIcon("E:\\Games\\icons\\Addcustomer.png"));
		addCusButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		addCusButton.setBounds(10, 405, 169, 28);
		txtArea.add(addCusButton);
		
		JButton saveButton = new JButton("Save"); // SAve button's & Action event Interface ...
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save Button Clicked");
				con = DBConnection.getConnection();
				query = "SELECT MAX(CUSTOMER_ID) FROM CUSTOMER";
				stmt = null;		
				customerObj = new Customer();
				if(txtCusID.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Customer ID Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					txtCusID.requestFocus();
				}
				else if(txtCusName.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Customer Name Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					txtCusName.requestFocus();
				}
				else if(txtEmail.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Email Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					txtEmail.requestFocus();
				}
				else if(txtAreaAddress.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Address  Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					txtAreaAddress.requestFocus();
				}
				else if(txtFieldMobile_No.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Mobile Number Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					txtFieldMobile_No.requestFocus();
				}
				else {
					customerObj.setCustomer_ID(txtCusID.getText());
					customerObj.setCustomerName(txtCusName.getText());
					customerObj.setGender(maleRadioButton.isSelected() ? "Male" : "Female");
					customerObj.setEmail(txtEmail.getText());
					customerObj.setAddress(txtAreaAddress.getText());
					customerObj.setMobile_Number(Long.parseLong(txtFieldMobile_No.getText()));  
					try {
						ps = con.prepareStatement("INSERT INTO CUSTOMER (CUSTOMER_ID,CUSTOMER_NAME,GENDER,EMAIL,ADDRESS,MOBILE_NUMBER)" + "VALUES(?,?,?,?,?,?)");
						ps.setString(1, customerObj.getCustomer_ID());
						ps.setString(2, customerObj.getCustomerName());
						ps.setString(3, customerObj.getGender());
						ps.setString(4, customerObj.getEmail());
						ps.setString(5, customerObj.getAddress());
						ps.setLong(6, customerObj.getMobile_Number());
						count = ps.executeUpdate();
						if(count != 0) {
							JOptionPane.showMessageDialog(null,count + " New Customer Added...", "Success", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, " Record Insertion Failed", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch(SQLException e1 ) {
						e1.printStackTrace();
					}
				}
			}
		});
		saveButton.setMnemonic('S');
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		saveButton.setIcon(new ImageIcon("E:\\Games\\icons\\Saveicon.png"));
		saveButton.setBounds(204, 406, 100, 28);
		txtArea.add(saveButton);
		
		JButton viewCusButton = new JButton("View Customer "); // View customer JButton Action Event class & interface...
		viewCusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("View Customer Clicked");
				ViewCustomer viewCustomer = new ViewCustomer();
				viewCustomer.setVisible(true);
				dispose();
			}
		});
		viewCusButton.setMnemonic('V');
		viewCusButton.setIcon(new ImageIcon("E:\\Games\\icons\\View.png"));
		viewCusButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		viewCusButton.setBounds(341, 405, 168, 28);
		txtArea.add(viewCusButton);
		 
		JButton DeleteButton = new JButton("Delete"); // Delete Button Action Listener 4 )
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete Button Clicked");
				if(!txtCusID.getText().equals(" ")) {
					int confirmation = JOptionPane.showConfirmDialog(null, "Customer Confirm Delete ?","Delete Customer",JOptionPane.YES_NO_OPTION);
					if(confirmation == 0) {
						query = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?";
						try {
							ps = con.prepareStatement(query);
							ps.setString(1, txtCusID.getText());
							count = ps.executeUpdate();
							if(count != 0) {
								JOptionPane.showMessageDialog(null, "Customer Deleted...","Delete Customer",JOptionPane.INFORMATION_MESSAGE);
							}
						}
						catch(SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		DeleteButton.setMnemonic('D');
		DeleteButton.setIcon(new ImageIcon("E:\\Games\\icons\\Delete.png"));
		DeleteButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		DeleteButton.setBounds(540, 405, 115, 28);
		txtArea.add(DeleteButton);
		
		JButton closeButton = new JButton("Close"); // Close Button Action Listener Interface & classes ... 5
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Close Button Clicked");
				dispose();
			}
		});
		closeButton.setMnemonic('C');
		closeButton.setIcon(new ImageIcon("E:\\Games\\icons\\Close1.png"));
		closeButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		closeButton.setBounds(670, 405, 115, 28);
		txtArea.add(closeButton);
		
		maleRadioButton = new JRadioButton("Male");
		maleRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		maleRadioButton.setBounds(563, 158, 92, 23);
		txtArea.add(maleRadioButton);
		
		femaleRadioButton = new JRadioButton("Female");
		femaleRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		femaleRadioButton.setBounds(685, 158, 100, 23);
		txtArea.add(femaleRadioButton);
		
	    radioMaleFemaleButton = new ButtonGroup(); // ButtonGroup ...
		radioMaleFemaleButton.add(maleRadioButton);
		radioMaleFemaleButton.add(femaleRadioButton);
		maleRadioButton.setSelected(true);
	}
		/*
		 * Retrieve All the Admin Details from the Database table & stored in the 
		 * Array list then return it ...
		 */
		public List<Customer> getCustomerDetails(){ // Customer Details  7)    .....
			List<Customer> customerList = new ArrayList<Customer>();
			try {
				con = DBConnection.getConnection();
				query = "SELECT * FROM CUSTOMER";
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Customer customerObj = new Customer();
					customerObj.setCustomer_ID(rs.getString("CUSTOMER_ID"));
					customerObj.setCustomerName(rs.getString("CUSTOMER_NAME"));
					customerObj.setGender(rs.getString("GENDER"));
					customerObj.setEmail(rs.getString("EMAIL"));
					customerObj.setAddress(rs.getString("ADDRESS"));
					customerObj.setMobile_Number(rs.getLong("MOBILE_NUMBER"));
					customerList.add(customerObj);
				}
			}
			catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
			return customerList;
		}
		public void getCustomerDetailById(String customerId) {
			List<Customer> customerList = new ArrayList<Customer>();
			try {
				con = DBConnection.getConnection();
				query = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
				ps = con.prepareStatement(query);
				ps.setString(1, customerId);
				rs = ps.executeQuery();
				while(rs.next()) {
					Customer customerObj = new Customer();
					customerObj.setCustomer_ID(rs.getString("CUSTOMER_ID"));
					customerObj.setCustomerName(rs.getString("CUSTOMER_NAME"));
					customerObj.setGender(rs.getString("GENDER"));
					customerObj.setEmail(rs.getString("EMAIL"));
					customerObj.setAddress(rs.getString("ADDRESS"));
					customerObj.setMobile_Number(rs.getLong("MOBILE_NUMBER"));
					customerList.add(customerObj);
				}
				/*Populate Fields to the corresponding controls */
				txtCusID.setText(customerList.get(0).getCustomer_ID());
				txtCusName.setText(customerList.get(0).getCustomerName());
				String gender = customerList.get(0).getGender();
				if(gender.equalsIgnoreCase("Male")) 
					maleRadioButton.setSelected(true);
				else 
					femaleRadioButton.setSelected(true);
				
				txtEmail.setText(customerList.get(0).getEmail());
				txtAreaAddress.setText(customerList.get(0).getAddress());
				txtFieldMobile_No.setText(Long.toString(customerList.get(0).getMobile_Number())); // This is error code ...
			}
			catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}
		private void resetControls() {
			txtCusName.setText("");
			maleRadioButton.setSelected(true);
			txtAreaAddress.setText("");
			txtFieldMobile_No.setText("");
			txtEmail.setText("");
			txtCusName.requestFocus();
		}
}
