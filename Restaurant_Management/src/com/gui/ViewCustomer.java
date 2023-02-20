package com.gui;

import java.awt.EventQueue;
import com.model.Customer;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

public class ViewCustomer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchTxtField;
	//private JTable table;
	private JTable viewCusTable;
	CustomerFrame customerFrameObj;
	DefaultTableModel model;
	String customerId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCustomer frame = new ViewCustomer();
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
	public ViewCustomer() {
		setTitle("View Customer ");
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Games\\login.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 504);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchTxtField = new JTextField();
		searchTxtField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		searchTxtField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filterTable();
			}
		});
		searchTxtField.setBounds(29, 11, 661, 32);
		contentPane.add(searchTxtField);
		searchTxtField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTable();
			}
		});
		searchButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		searchButton.setIcon(new ImageIcon("E:\\Games\\icons\\binoculars-icon.png"));
		searchButton.setBounds(720, 11, 114, 32);
		contentPane.add(searchButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		
		
		JButton detailsButton = new JButton("Get Details");
		detailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(viewCusTable.getSelectedRow()!=-1|| viewCusTable.getSelectedColumn()!=-1) {
						customerId = (String)viewCusTable.getValueAt(viewCusTable.getSelectedRow(),0);        // Doubt ....
						System.out.println(customerId);
						customerFrameObj = new CustomerFrame();
						customerFrameObj.getCustomerDetailById(customerId);
						setVisible(false);
						customerFrameObj.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Please Select the Row...","Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(IndexOutOfBoundsException exception) {
					exception.printStackTrace();
				}
			}
		});
		detailsButton.setIcon(new ImageIcon("E:\\Games\\icons\\details.png"));
		detailsButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		detailsButton.setBounds(205, 403, 142, 32);
		contentPane.add(detailsButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Really Close?","Info",JOptionPane.YES_NO_OPTION);
				setVisible(false);
			}
		});
		closeButton.setIcon(new ImageIcon("E:\\Games\\icons\\close.png"));
		closeButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		closeButton.setBounds(422, 403, 99, 32);
		contentPane.add(closeButton);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 54, 661, 307);
		contentPane.add(scrollPane);
		
		viewCusTable = new JTable();
		viewCusTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(viewCusTable);
		viewCusTable.setFont(new Font("Times New Roman", Font.BOLD, 12));
		viewCusTable.setModel(new DefaultTableModel(
				new Object[][] {null, null, null, null, null, null},
				new String[] {"Customer_ID", "Customer_Name", "Gender", "Email", "Address", "Mobile_Number"}
				));
		viewCusTable.getColumnModel().getColumn(1).setPreferredWidth(90);
		viewCusTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		viewCusTable.getColumnModel().getColumn(4).setPreferredWidth(90);
		viewCusTable.getColumnModel().getColumn(5).setPreferredWidth(90);
		populateTable();
		
	//	table = new JTable();
	}
	/************************Populate DataBase into the Swing Table**************************/
	void populateTable() {
		customerFrameObj = new CustomerFrame();
		model = (DefaultTableModel) viewCusTable.getModel();
		List<Customer> customerList = customerFrameObj.getCustomerDetails();
		Object rowData[] = new Object[viewCusTable.getColumnCount()];
		System.out.println("rowdata = "+rowData.length);
		model.setRowCount(0);
		/*Print ArrayList Size */
		System.out.println("Array List Size = " + customerList.size());
		for(int i=0; i<customerList.size(); i++) {
			rowData[0] = customerList.get(i).getCustomer_ID();
			rowData[1] = customerList.get(i).getCustomerName();
			rowData[2] = customerList.get(i).getGender();
			rowData[3] = customerList.get(i).getEmail();
			rowData[4] = customerList.get(i).getAddress();
			rowData[5] = customerList.get(i).getMobile_Number();
			System.out.println(rowData[0]+"\t"+rowData[1]+"\t"+rowData[2]+"\t"+rowData[3]+"\t"+rowData[4]+"\t"+rowData[5]);
			model.addRow(rowData);
		}
	}
	void filterTable() {
		model = (DefaultTableModel) viewCusTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		viewCusTable.setRowSorter(sorter);
		String text = searchTxtField.getText();
		try {
			sorter.setRowFilter(RowFilter.regexFilter(text));
		}
		catch(PatternSyntaxException pse) {
			System.err.println("Bad regex pattern");
		}
	}
}
