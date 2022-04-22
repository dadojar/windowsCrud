package windowsCrud;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.SupplierController;
import models.Supplier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

// http://cswire.blogspot.com/2017/11/make-simple-crud-java-ui-application.html

public class app {

	private JFrame frame;
	private JTextField nameSupplierTF;
	private JTextField addressSupplierTF;
	private JTextField telephoneSupplierTF;
	
	private SupplierController supplierController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app window = new app();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public app() {
		initialize();
		supplierController = new SupplierController();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.getContentPane().setLayout(null);
		
		nameSupplierTF = new JTextField();
		nameSupplierTF.setBounds(225, 85, 169, 19);
		frame.getContentPane().add(nameSupplierTF);
		nameSupplierTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(94, 86, 83, 16);
		frame.getContentPane().add(lblNewLabel);
		
		addressSupplierTF = new JTextField();
		addressSupplierTF.setBounds(225, 140, 166, 19);
		frame.getContentPane().add(addressSupplierTF);
		addressSupplierTF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("telephone");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(93, 199, 66, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("address");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(94, 141, 83, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		telephoneSupplierTF = new JTextField();
		telephoneSupplierTF.setBounds(225, 193, 166, 19);
		frame.getContentPane().add(telephoneSupplierTF);
		telephoneSupplierTF.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new SupplierListener());
		btnNewButton.setBounds(94, 254, 85, 21);
		frame.getContentPane().add(btnNewButton);
		frame.setBounds(100, 100, 591, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	class SupplierListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("2 - Action Performed Intercepted the add function!");
			String ac = e.getActionCommand();			
			switch(ac) {
			case "Add": addSupplier();
			break;
			}
		}

		private void addSupplier() {
			Integer id = new Random().nextInt(100000);
			String sTelephone = telephoneSupplierTF.getText();
			int telephone = Integer.parseInt(sTelephone);
			Supplier supplier = new Supplier( id, nameSupplierTF.getText(), addressSupplierTF.getText(), telephone );
			supplierController.addSupplier(supplier);
			
		}
		
	}
}


