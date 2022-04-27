package windowsCrud;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.SupplierController;
import models.Supplier;


import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Vector;
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
		supplierController = new SupplierController();
		List<Supplier> loadSupplierData = loadSupplierData();
		initialize(loadSupplierData);
	}

	
	
	private List<Supplier> loadSupplierData(){
	  System.out.print("start load supplier data");
	  List<Supplier> allSupplierList = supplierController.getAllSupplier();
	  System.out.println("Start getall supplier size : " + allSupplierList.size());
	  return allSupplierList;
	}
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<Supplier> loadSupplierData) {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.getContentPane().setLayout(null);
		
		
		JFrame frame1 = new JFrame();
		// create a JTABLE to show prevoius object
		String colSupplier[] = {"Name","Address","Telephone"};
		DefaultTableModel tableModel = new DefaultTableModel(colSupplier, 0);
		JTable table = new JTable(tableModel);
	
		
		for(Supplier s: loadSupplierData ) {
			String[] curr = { s.getName(), s.getAddress(),s.getTelephone()+""}; 
			tableModel.addRow(curr);
		}
		
		
		 
		JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "ODI Rankings", TitledBorder.CENTER, TitledBorder.TOP));
     
        panel.add(new JScrollPane(table));
        frame1.add(panel);
        frame1.setSize(550, 400);
        frame1.setVisible(true);
		

		
		nameSupplierTF = new JTextField();
		nameSupplierTF.setBounds(156, 273, 166, 19);
		frame.getContentPane().add(nameSupplierTF);
		nameSupplierTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(51, 276, 83, 16);
		frame.getContentPane().add(lblNewLabel);
		
		addressSupplierTF = new JTextField();
		addressSupplierTF.setBounds(156, 311, 166, 19);
		frame.getContentPane().add(addressSupplierTF);
		addressSupplierTF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("telephone");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(51, 346, 66, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("address");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(51, 312, 83, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		telephoneSupplierTF = new JTextField();
		telephoneSupplierTF.setBounds(156, 345, 166, 19);
		frame.getContentPane().add(telephoneSupplierTF);
		telephoneSupplierTF.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new SupplierListener());
		btnNewButton.setBounds(47, 394, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Supplier management");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 22));
		lblNewLabel_3.setBounds(20, 10, 219, 44);
		frame.getContentPane().add(lblNewLabel_3);
		frame.setBounds(100, 100, 720, 494);
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


