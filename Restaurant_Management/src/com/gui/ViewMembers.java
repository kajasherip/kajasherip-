package com.gui;

import java.awt.EventQueue;
import com.model.Members;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.regex.PatternSyntaxException;
import java.util.List;
import javax.swing.ListSelectionModel;
import java.awt.Toolkit;

public class ViewMembers extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchTxtField;
	private JTable table;
	//private JTable viewCusTable;
	MembersFrame membersFrameObj;
	DefaultTableModel model;
	String membersId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMembers frame = new ViewMembers();
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
	public ViewMembers() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Games\\login.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 506);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchTxtField = new JTextField();
		searchTxtField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		searchTxtField.setBounds(40, 26, 612, 33);
		contentPane.add(searchTxtField);
		searchTxtField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 80, 612, 271);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Times New Roman", Font.BOLD, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Customer_ID", "Join_Date"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		populateTable();
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTable();
			}
		});
		searchButton.setIcon(new ImageIcon("E:\\Games\\icons\\binoculars-icon.png"));
		searchButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		searchButton.setBounds(702, 26, 114, 33);
		contentPane.add(searchButton);
		
		JButton detailsButton = new JButton("Get Details");
		detailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(table.getSelectedRow()!=-1|| table.getSelectedColumn()!=-1) {
						membersId = (String)table.getValueAt(table.getSelectedRow(),0);        // Doubt ....
						System.out.println(membersId);
						membersFrameObj = new MembersFrame();
						membersFrameObj.getMembersDetailById(membersId);
						setVisible(false);
						membersFrameObj.setVisible(true);
						}
					}
				catch(IndexOutOfBoundsException exception) {
					exception.printStackTrace();
				}
			}
		});
		detailsButton.setIcon(new ImageIcon("E:\\Games\\icons\\details.png"));
		detailsButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		detailsButton.setMnemonic('D');
		detailsButton.setBounds(170, 385, 153, 33);
		contentPane.add(detailsButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Really Close?","Info",JOptionPane.YES_NO_OPTION);
				setVisible(false);
			}
		});
		closeButton.setIcon(new ImageIcon("E:\\Games\\icons\\Close1.png"));
		closeButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		closeButton.setMnemonic('C');
		closeButton.setBounds(375, 385, 129, 33);
		contentPane.add(closeButton);
	}
	void populateTable() {
		membersFrameObj = new MembersFrame();
		model = (DefaultTableModel) table.getModel();
		List<Members> memberList = membersFrameObj.getMembersDetails();
		Object rowData[] = new Object[table.getColumnCount()];
		System.out.println("rowdata = "+rowData.length);
		model.setRowCount(0);
		/*Print ArrayList Size */
		System.out.println("Array List Size = " + memberList.size());
		for(int i=0; i<memberList.size(); i++) {
			rowData[0] = memberList.get(i).getCustomer_ID();
			rowData[1] = memberList.get(i).getJoin_Date();
			System.out.println(rowData[0]+"\t"+rowData[1]);
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
