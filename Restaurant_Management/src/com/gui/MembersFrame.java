package com.gui;

import java.awt.EventQueue;
import com.dbutil.DBConnection;
import com.model.Customer;
import com.model.Members;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class MembersFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cusIdTxtField;
	private JTextField dateTxtField;
	
	CustomerFrame customerFrame;
	Connection con;
	Statement stmt;
	Statement stmt1;
	PreparedStatement ps;
	ResultSet rs;
	ResultSet rs1;
	Members membersObj;
	Customer customerObj;
	String query;
	Date date;
	
	private String sequential_ID ;
	//private static int sequential_ID = 1000;
	int count = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MembersFrame frame = new MembersFrame();
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
	public MembersFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Games\\login.png"));
		setTitle("Members Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 506);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel picLabel = new JLabel("");
		picLabel.setIcon(new ImageIcon("E:\\Games\\icons\\brown-man-icon.png"));
		picLabel.setBounds(10, 67, 244, 256);
		contentPane.add(picLabel);
		
		JLabel cusIDLabel = new JLabel("Customer_ID");
		cusIDLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		cusIDLabel.setBounds(336, 135, 165, 39);
		contentPane.add(cusIDLabel);
		
		JLabel join_DateLabel = new JLabel("Join_Date");
		join_DateLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		join_DateLabel.setBounds(336, 226, 165, 39);
		contentPane.add(join_DateLabel);
		
		cusIdTxtField = new JTextField();
		cusIdTxtField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cusIdTxtField.setBounds(544, 141, 244, 28);
		contentPane.add(cusIdTxtField);
		cusIdTxtField.setColumns(10);
		
		dateTxtField = new JTextField();
		dateTxtField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		dateTxtField.setBounds(544, 226, 244, 33);
		contentPane.add(dateTxtField);
		dateTxtField.setColumns(10);
		
		JButton baddButton = new JButton("Add");
		baddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    con = DBConnection.getConnection();
					 query = "SELECT MAX(CUSTOMER_ID) FROM MEMBERS";
					 stmt = null;
					 membersObj = new Members();
					 try {
						 stmt = con.createStatement();
						 rs = stmt.executeQuery(query);
						 rs.next();
						 if(rs.getString(1)==null) {
							 System.out.println("No records");
							 Scanner sc = new Scanner(System.in);
							 System.out.println("Value = ");
							 sequential_ID = sc.next();
							membersObj.setCustomer_ID("C#" +  sequential_ID);
							 cusIdTxtField.setText(membersObj.getCustomer_ID());
						 }
						 else{
							 Scanner sc = new Scanner(System.in);
							 System.out.println("Value = ");
							 sequential_ID = sc.next();
							 membersObj.setCustomer_ID("C#" + sequential_ID);
							 cusIdTxtField.setText(membersObj.getCustomer_ID());
						 }
					 }
					 catch(SQLException e1) {
						 e1.printStackTrace();
					 }
					 resetControls();
			}
		});
		baddButton.setIcon(new ImageIcon("E:\\Games\\icons\\Addcustomer.png"));
		baddButton.setMnemonic('A');
		baddButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		baddButton.setBounds(29, 384, 109, 33);
		contentPane.add(baddButton);
		
		JButton bsaveButton = new JButton("Save");
		bsaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save Button Clicked");
				con = DBConnection.getConnection();
				query = "SELECT MAX(CUSTOMER_ID) FROM MEMBERS";
				stmt = null;		
				if(cusIdTxtField.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Members ID Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					cusIdTxtField.requestFocus();
				}
				else if(dateTxtField.getText().equals(" ")) {
					JOptionPane.showMessageDialog(null, "Members Date Empty", "Error", JOptionPane.ERROR_MESSAGE);
					dateTxtField.requestFocus();
				}
				else {
					/*Date date = new Date();
					//System.out.println(date);
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
					System.out.println("Date = " + sdf1.format(date) + "\n");
					SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
					System.out.println("Month = " + sdf2.format(date) + "\n");
			        SimpleDateFormat sdf3 = new SimpleDateFormat("YYYY");
					System.out.println("Year = " + sdf3.format(date) + "\n");
					String dateField = sdf3.format(date)+"-"+sdf2.format(date)+"-" +sdf1.format(date);*/
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					
					membersObj.setCustomer_ID(cusIdTxtField.getText());
					String timedate = String.valueOf(timestamp);
					membersObj.setJoin_Date(timedate);
					try {
						ps = con.prepareStatement("INSERT INTO MEMBERS (CUSTOMER_ID, JOIN_DATE)" + "VALUES(?, ?)");
						ps.setString(1, membersObj.getCustomer_ID());
						ps.setString(2, membersObj.getJoin_Date());
						count = ps.executeUpdate();
						if(count != 0) {
							JOptionPane.showMessageDialog(null,count + " New Member Added...", "Success", JOptionPane.INFORMATION_MESSAGE);
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
		bsaveButton.setIcon(new ImageIcon("E:\\Games\\icons\\Saveicon.png"));
		bsaveButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bsaveButton.setMnemonic('S');
		bsaveButton.setBounds(192, 384, 109, 33);
		contentPane.add(bsaveButton);
		
		JButton viewButton = new JButton("View");
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("View Button Clicked");
				ViewMembers viewMembers = new ViewMembers();
				viewMembers.setVisible(true);
				dispose();
			}
		});
		viewButton.setIcon(new ImageIcon("E:\\Games\\icons\\View.png"));
		viewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		viewButton.setMnemonic('V');
		viewButton.setBounds(351, 384, 109, 33);
		contentPane.add(viewButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete Button Clicked");
				if(!cusIdTxtField.getText().equals(" ")) {
					int confirmation = JOptionPane.showConfirmDialog(null, "Members Confirm Delete ?","Delete Members",JOptionPane.YES_NO_OPTION);
					if(confirmation == 0) {
						query = "DELETE FROM MEMBERS WHERE CUSTOMER_ID=?";
						try {
							ps = con.prepareStatement(query);
							ps.setString(1, cusIdTxtField.getText());
							count = ps.executeUpdate();
							if(count != 0) {
								JOptionPane.showMessageDialog(null, "Members Deleted...","Delete Members",JOptionPane.INFORMATION_MESSAGE);
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
		deleteButton.setBounds(507, 384, 115, 33);
		contentPane.add(deleteButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closeButton.setIcon(new ImageIcon("E:\\Games\\icons\\Close1.png"));
		closeButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		closeButton.setMnemonic('C');
		closeButton.setBounds(664, 384, 124, 33);
		contentPane.add(closeButton);
	}
	public List<Members> getMembersDetails(){
		List<Members> membersList = new ArrayList<Members>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM MEMBERS";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Members membersObj = new Members();
				membersObj.setCustomer_ID(rs.getString("CUSTOMER_ID"));
			    membersObj.setJoin_Date(rs.getString("JOIN_DATE"));
				membersList.add(membersObj);
			}
		}
		catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return membersList;
	}
	public void getMembersDetailById(String customerId) {
		List<Members> membersList = new ArrayList<Members>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM MEMBERS WHERE CUSTOMER_ID=?";
			ps = con.prepareStatement(query);
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Members membersObj = new Members();
				membersObj.setCustomer_ID(rs.getString("CUSTOMER_ID"));
				membersObj.setJoin_Date("JOIN_DATE");
				membersList.add(membersObj);
			}
			cusIdTxtField.setText(membersList.get(0).getCustomer_ID());
			dateTxtField.setText(membersList.get(0).getJoin_Date());
		}
		catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	}
	private void resetControls() {
		dateTxtField.setText("");
	}
}
