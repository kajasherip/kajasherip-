package com.gui;

import com.model.Menu;
import com.dbutil.DBConnection;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Toolkit;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField product_IDTxtField;
	private JTextField productNameTxtField;
	private JTextField priceTxtField;
	
	Connection con;
	Statement stmt;
	PreparedStatement ps;
	ResultSet rs;
	Menu menuObj;
	String query;
	private static int sequential_ID = 100;
	int count = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
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
	public MenuFrame() {
		setTitle("Menu Frame");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Games\\login.png"));
		setBackground(new Color(221, 160, 221));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 506);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel picLabel = new JLabel("");
		picLabel.setIcon(new ImageIcon("E:\\Games\\icons\\Pork1.png"));
		picLabel.setBounds(10, 96, 256, 197);
		contentPane.add(picLabel);
		
		product_IDTxtField = new JTextField();
		product_IDTxtField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		product_IDTxtField.setBounds(493, 96, 299, 33);
		contentPane.add(product_IDTxtField);
		product_IDTxtField.setColumns(10);
		
		productNameTxtField = new JTextField();
		productNameTxtField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		productNameTxtField.setBounds(493, 162, 299, 33);
		contentPane.add(productNameTxtField);
		productNameTxtField.setColumns(10);
		
		priceTxtField = new JTextField();
		priceTxtField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		priceTxtField.setBounds(493, 234, 299, 33);
		contentPane.add(priceTxtField);
		priceTxtField.setColumns(10);
		
		JLabel product_IDLabel = new JLabel("Product_ID");
		product_IDLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		product_IDLabel.setBounds(316, 96, 136, 33);
		contentPane.add(product_IDLabel);
		
		JLabel product_NameLabel = new JLabel("Product_Name");
		product_NameLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		product_NameLabel.setBounds(316, 161, 136, 34);
		contentPane.add(product_NameLabel);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		priceLabel.setBounds(316, 234, 136, 33);
		contentPane.add(priceLabel);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				     con = DBConnection.getConnection();
					// Query for setting Auto - Generated ID to the Customer....
					 query = "SELECT MAX(PRODUCT_ID) FROM MENU";
					 stmt = null;
					 menuObj = new Menu();
					 try {
						 stmt = con.createStatement();
						 rs = stmt.executeQuery(query);
						 rs.next();
						 if(rs.getString(1) == null) {
							 System.out.println("No Records");
							 menuObj.setProduct_ID("P#" + sequential_ID);
							 product_IDTxtField.setText(menuObj.getProduct_ID());
						 }
						 else {
							 sequential_ID = Integer.parseInt(rs.getString(1).substring(2, rs.getString(1).length()))+1;
							 menuObj.setProduct_ID("P#" + sequential_ID);
							 product_IDTxtField.setText(menuObj.getProduct_ID());
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
		addButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		addButton.setBounds(59, 379, 98, 33);
		contentPane.add(addButton);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = DBConnection.getConnection();
				query = "SELECT MAX(PRODUCT_ID) FROM MENU";
				stmt = null;
				menuObj = new Menu();
				if(product_IDTxtField.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Product ID cannot be empty", "Error", JOptionPane.YES_NO_OPTION);
					product_IDTxtField.requestFocus();
				}
				else if(productNameTxtField.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Product Name cannot empty", "Error", JOptionPane.YES_NO_OPTION);
					productNameTxtField.requestFocus();
				}
				else if(priceTxtField.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Price Value cannot be Empty", "Error", JOptionPane.YES_NO_OPTION);
					priceTxtField.requestFocus();
				}
				else {
					menuObj.setProduct_ID(product_IDTxtField.getText());
					menuObj.setProduct_Name(productNameTxtField.getText());
					menuObj.setPrice(Integer.parseInt(priceTxtField.getText())); /// This is error code ...
					try {
						ps = con.prepareStatement("INSERT INTO MENU(PRODUCT_ID, PRODUCT_NAME, PRICE)" + "VALUES(?, ?, ?)");
						ps.setString(1, menuObj.getProduct_ID());
						ps.setString(2, menuObj.getProduct_Name());
						ps.setInt(3, menuObj.getPrice());
						count = ps.executeUpdate();
						if(count != 0) {
							JOptionPane.showMessageDialog(null,count + " New Menu Added...", "Success", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, " Record Insertion Failed", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		saveButton.setIcon(new ImageIcon("E:\\Games\\icons\\Saveicon.png"));
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		saveButton.setMnemonic('S');
		saveButton.setBounds(218, 379, 104, 33);
		contentPane.add(saveButton);
		
		JButton viewButton = new JButton("View");
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("View Button Clicked");
				ViewMenu viewMenu = new ViewMenu();
				viewMenu.setVisible(true);
				dispose();
			}
		});
		viewButton.setMnemonic('V');
		viewButton.setIcon(new ImageIcon("E:\\Games\\icons\\binoculars-icon.png"));
		viewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		viewButton.setBounds(373, 379, 104, 33);
		contentPane.add(viewButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete Button Clicked");
				if(!product_IDTxtField.getText().equals(" ")) {
					int confirmation = JOptionPane.showConfirmDialog(null, "Customer Confirm Delete ?","Delete Customer",JOptionPane.YES_NO_OPTION);
					if(confirmation == 0) {
						query = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?";
						try {
							ps = con.prepareStatement(query);
							ps.setString(1, product_IDTxtField.getText());
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
		deleteButton.setIcon(new ImageIcon("E:\\Games\\icons\\Delete.png"));
		deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		deleteButton.setMnemonic('D');
		deleteButton.setBounds(522, 379, 114, 33);
		contentPane.add(deleteButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closeButton.setIcon(new ImageIcon("E:\\Games\\icons\\Close1.png"));
		closeButton.setMnemonic('C');
		closeButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		closeButton.setBounds(678, 379, 114, 33);
		contentPane.add(closeButton);
	}
	public List<Menu> getMenuDetails() {
		List<Menu> menuList = new ArrayList<Menu>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM MENU";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Menu menuObj = new Menu();
				menuObj.setProduct_ID(rs.getString("PRODUCT_ID"));
				menuObj.setProduct_Name(rs.getString("PRODUCT_NAME"));
				menuObj.setPrice(rs.getInt("PRICE"));
				menuList.add(menuObj);
			}
		}
		catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return menuList;
	}
	public void getMenuDetailById(String productId) {
		List<Menu> menuList = new ArrayList<Menu>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM MENU WHERE PRODUCT_ID = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, productId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Menu menuObj = new Menu();
				menuObj.setProduct_ID(rs.getString("PRODUCT_ID"));
				menuObj.setProduct_Name(rs.getString("PRODUCT_NAME"));
				menuObj.setPrice(rs.getInt("PRICE"));
				menuList.add(menuObj);
			}
			product_IDTxtField.setText(menuList.get(0).getProduct_ID());
			productNameTxtField.setText(menuList.get(0).getProduct_Name());
			priceTxtField.setText(Integer.toString(menuList.get(0).getPrice()));
		}
		catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	}
	private void resetControls() {
		productNameTxtField.setText("");
		priceTxtField.setText("");
		productNameTxtField.requestFocus();
	}
}
