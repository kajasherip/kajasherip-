package com.gui;

import java.awt.EventQueue;
import com.model.Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class ViewMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchTxtField;
	private JTable menuTable;
	String menuId;
	MenuFrame menuFrameObj;
	DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMenu frame = new ViewMenu();
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
	public ViewMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Games\\login.png"));
		setTitle("View Menu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 506);
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
		searchTxtField.setBounds(31, 26, 615, 33);
		contentPane.add(searchTxtField);
		searchTxtField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 70, 615, 289);
		contentPane.add(scrollPane);
		
		menuTable = new JTable();
		menuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(menuTable);
		menuTable.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menuTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Product_ID", "Product_Name", "Price"
			}
		));
		menuTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		menuTable.getColumnModel().getColumn(2).setPreferredWidth(110);
		populateTable();
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTable();
			}
		});
		searchButton.setMnemonic('S');
		searchButton.setIcon(new ImageIcon("E:\\Games\\icons\\binoculars-icon.png"));
		searchButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		searchButton.setBounds(690, 26, 120, 33);
		contentPane.add(searchButton);
		
		JButton detailsButton = new JButton("Get Details");
		detailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(menuTable.getSelectedRow()!=-1 || menuTable.getSelectedColumn()!=-1) {
						menuId = (String)menuTable.getValueAt(menuTable.getSelectedRow(),0);        // Doubt ....
						System.out.println(menuId);
						menuFrameObj = new MenuFrame();
						menuFrameObj.getMenuDetailById(menuId);
						setVisible(false);
						menuFrameObj.setVisible(true);
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
		detailsButton.setMnemonic('D');
		detailsButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		detailsButton.setBounds(163, 389, 155, 35);
		contentPane.add(detailsButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Really Close?","Info",JOptionPane.YES_NO_OPTION);
				setVisible(false);
			}
		});
		closeButton.setIcon(new ImageIcon("E:\\Games\\icons\\Close1.png"));
		closeButton.setMnemonic('C');
		closeButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		closeButton.setBounds(382, 389, 128, 35);
		contentPane.add(closeButton);
	}
	void populateTable() {
		menuFrameObj = new MenuFrame();
		model = (DefaultTableModel) menuTable.getModel();
		List<Menu> menuList = menuFrameObj.getMenuDetails();
		Object rowData[] = new Object[menuTable.getColumnCount()];
		System.out.println("rowdata = "+rowData.length);
		model.setRowCount(0);
		/*Print ArrayList Size */
		System.out.println("Array List Size = " + menuList.size());
		for(int i=0; i<menuList.size(); i++) {
			rowData[0] = menuList.get(i).getProduct_ID();
			rowData[1] = menuList.get(i).getProduct_Name();
			rowData[2] = menuList.get(i).getPrice();
			System.out.println(rowData[0]+"\t"+rowData[1]+"\t"+rowData[2]);
			model.addRow(rowData);
		}
	}
	void filterTable() {
		model = (DefaultTableModel) menuTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		menuTable.setRowSorter(sorter);
		String text = searchTxtField.getText();
		try {
			sorter.setRowFilter(RowFilter.regexFilter(text));
		}
		catch(PatternSyntaxException pse) {
			System.err.println("Bad regex pattern");
		}
	}
}
