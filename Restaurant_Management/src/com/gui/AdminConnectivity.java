package com.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminConnectivity extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminConnectivity frame = new AdminConnectivity();
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
	public AdminConnectivity() {
		setTitle("Admin Activity ");
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Games\\icons\\Save.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 508);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\Games\\icons\\fast-food-icon.png"));
		lblNewLabel.setBounds(10, 83, 239, 225);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Customer ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerFrame customerFrame = new CustomerFrame();
				customerFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setBackground(new Color(173, 255, 47));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setIcon(new ImageIcon("E:\\Games\\icons\\Restaurant6.png"));
		btnNewButton.setMnemonic('C');
		btnNewButton.setBounds(334, 70, 191, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame menuFrame = new MenuFrame();
				menuFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("E:\\Games\\icons\\Pork-Chop-Set-icon.png"));
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.setBackground(new Color(173, 255, 47));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.setMnemonic('M');
		btnNewButton_1.setBounds(334, 156, 191, 37);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Members");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MembersFrame membersFrame = new MembersFrame();
				membersFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setForeground(new Color(255, 0, 0));
		btnNewButton_2.setIcon(new ImageIcon("E:\\Games\\icons\\brown-man-icon1.png"));
		btnNewButton_2.setMnemonic('M');
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2.setBackground(new Color(173, 255, 47));
		btnNewButton_2.setBounds(334, 249, 191, 37);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Sales");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesFrame salesFrame = new SalesFrame();
				salesFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("E:\\Games\\icons\\Money1.png"));
		btnNewButton_3.setForeground(new Color(255, 0, 0));
		btnNewButton_3.setBackground(new Color(173, 255, 47));
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_3.setMnemonic('S');
		btnNewButton_3.setBounds(334, 337, 191, 37);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Log Out");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_4.setForeground(new Color(255, 0, 0));
		btnNewButton_4.setBackground(new Color(173, 255, 47));
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_4.setIcon(new ImageIcon("E:\\Games\\icons\\Button-Log-Off-icon.png"));
		btnNewButton_4.setMnemonic('O');
		btnNewButton_4.setBounds(54, 380, 149, 37);
		contentPane.add(btnNewButton_4);
	}
}
