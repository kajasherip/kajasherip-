package com.gui;

import java.awt.EventQueue;
import com.model.Sales;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class ViewSales extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchTxtField;
	private JTable table;
	String salesId;
	SalesFrame salesFrameObj;
	DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSales frame = new ViewSales();
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
	public ViewSales() {
		setTitle("View Sales");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Games\\login.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 505);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchTxtField = new JTextField();
		searchTxtField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filterTable();
			}
		});
		searchTxtField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		searchTxtField.setBounds(37, 26, 626, 30);
		contentPane.add(searchTxtField);
		searchTxtField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 67, 626, 267);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Times New Roman", Font.BOLD, 18));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Customer_ID", "Order_Date", "Product_ID"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		scrollPane.setViewportView(table);
		populateTable();
		
		JButton searchbutton = new JButton("Search");
		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTable();
			}
		});
		searchbutton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		searchbutton.setMnemonic('S');
		searchbutton.setIcon(new ImageIcon("E:\\Games\\icons\\binoculars-icon.png"));
		searchbutton.setBounds(697, 26, 116, 36);
		contentPane.add(searchbutton);
		
		JButton DetailsButton = new JButton("Get Details");
		DetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(table.getSelectedRow()!=-1 || table.getSelectedColumn()!=-1) {
						salesId = (String)table.getValueAt(table.getSelectedRow(),0);        // Doubt ....
						System.out.println(salesId);
						salesFrameObj = new SalesFrame();
						salesFrameObj.getSalesDetailById(salesId);
						setVisible(false);
						salesFrameObj.setVisible(true);
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
		DetailsButton.setMnemonic('D');
		DetailsButton.setIcon(new ImageIcon("E:\\Games\\icons\\details.png"));
		DetailsButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		DetailsButton.setBounds(164, 380, 156, 36);
		contentPane.add(DetailsButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Really Close?","Info",JOptionPane.YES_NO_OPTION);
				setVisible(false);
			}
		});
		closeButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		closeButton.setIcon(new ImageIcon("E:\\Games\\icons\\Close1.png"));
		closeButton.setMnemonic('C');
		closeButton.setBounds(377, 380, 116, 36);
		contentPane.add(closeButton);
	}
	void populateTable() {
		salesFrameObj = new SalesFrame();
		model = (DefaultTableModel) table.getModel();
		List<Sales> salesList = salesFrameObj.getSalesDetails();
		Object rowData[] = new Object[table.getColumnCount()];
		System.out.println("rowdata = "+rowData.length);
		model.setRowCount(0);
		/*Print ArrayList Size */
		System.out.println("Array List Size = " + salesList.size());
		for(int i=0; i<salesList.size(); i++) {
			rowData[0] = salesList.get(i).getCustomer_ID();
			rowData[1] = salesList.get(i).getOrder_Date();
			rowData[2] = salesList.get(i).getproduct_ID();
			System.out.println(rowData[0]+"\t"+rowData[1]+"\t"+rowData[2]);
			model.addRow(rowData);
		}
	}
	void filterTable() {
		model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(sorter);
		String text = searchTxtField.getText();
		try {
			sorter.setRowFilter(RowFilter.regexFilter(text));
		}
		catch(PatternSyntaxException pse) {
			System.err.println("Bad regex pattern");
		}
	}
}
