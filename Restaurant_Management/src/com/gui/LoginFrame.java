package com.gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserid;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("LOGIN ");
		setBackground(new Color(216, 191, 216));
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Games\\login.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 506);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PICK & MOVE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(334, 22, 230, 31);
		contentPane.add(lblNewLabel);
		
		txtUserid = new JTextField();
		txtUserid.setBounds(624, 155, 162, 31);
		contentPane.add(txtUserid);
		txtUserid.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(624, 213, 162, 31);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("User_ID  :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(494, 155, 97, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(494, 213, 76, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("E:\\Games\\icons\\login.jpg"));
		lblNewLabel_3.setBounds(84, 94, 263, 256);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Login ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUserid.getText().equals("root")&&textField_1.getText().equals("KAJAsherif7021@")) {
						JOptionPane.showMessageDialog(null, "Wellcome to Restaurant","Login",JOptionPane.INFORMATION_MESSAGE);
						AdminConnectivity adminConnectivity = new AdminConnectivity();
						adminConnectivity.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null,"Invalid Username or Password","Login",JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		btnNewButton.setIcon(new ImageIcon("E:\\Games\\icons\\Login2.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(496, 293, 116, 51);
		contentPane.add(btnNewButton);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon("E:\\Games\\icons\\Close1.png"));
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClose.setBounds(670, 293, 116, 51);
		contentPane.add(btnClose);
	}
}
