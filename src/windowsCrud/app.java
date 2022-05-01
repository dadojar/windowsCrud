package windowsCrud;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.SupplierController;
import models.Supplier;


import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

// http://cswire.blogspot.com/2017/11/make-simple-crud-java-ui-application.html

public class app {

	private JFrame frame;
	private JTextField nameSupplierTF;
	private JTextField addressSupplierTF;
	private JTextField telephoneSupplierTF;
	
	private SupplierController supplierController;
	private JTable table_1;
	private JTable table;
	private int indexSelectedRow;
	
	private DefaultTableModel tableModel;

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
		System.out.println("Start initialization GUI");
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.getContentPane().setLayout(null);
		
		// Start populate table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(UIManager.getBorder("CheckBoxMenuItem.border"));
		scrollPane.setBounds(50, 80, 857, 268);
		frame.getContentPane().add(scrollPane);
		
		String colSupplier[] = {"Name","Address","Telephone"};
		tableModel = new DefaultTableModel(colSupplier, 0);
		table = new JTable(tableModel);
		table.getTableHeader().setBackground(new Color (224,238,237));
	
		for(Supplier s: loadSupplierData ) {
			String[] curr = { s.getName(), s.getAddress(),s.getTelephone()+""}; 
			tableModel.addRow(curr);
		}
		scrollPane.setViewportView(table);
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// End populate table
		
		
		// Start field management
		nameSupplierTF = new JTextField();
		nameSupplierTF.setBounds(156, 415, 166, 19);
		frame.getContentPane().add(nameSupplierTF);
		nameSupplierTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(50, 414, 83, 16);
		frame.getContentPane().add(lblNewLabel);
		
		addressSupplierTF = new JTextField();
		addressSupplierTF.setBounds(156, 460, 166, 19);
		frame.getContentPane().add(addressSupplierTF);
		addressSupplierTF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telephone");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(50, 508, 83, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(50, 461, 83, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		telephoneSupplierTF = new JTextField();
		telephoneSupplierTF.setBounds(156, 502, 166, 19);
		frame.getContentPane().add(telephoneSupplierTF);
		telephoneSupplierTF.setColumns(10);
		// ENd field management
		
		// Button Add implementation
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new SupplierListener());
		btnNewButton.setBounds(50, 558, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		// Button Update implementation
		JButton btnButtonUpdate = new JButton("Update");
		btnButtonUpdate.addActionListener(new SupplierListener());
		btnButtonUpdate.setBounds(200, 558, 85, 21);
		frame.getContentPane().add(btnButtonUpdate);
		
		// Button Delete implementation
		JButton btnButtonDelete = new JButton("Delete");
		btnButtonDelete.addActionListener(new SupplierListener());
		btnButtonDelete.setBounds(350, 558, 85, 21);
		frame.getContentPane().add(btnButtonDelete);
		
		
		// Select a row in a table and populate the text field
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){ 
		@Override
		public void valueChanged(ListSelectionEvent e) {
			//with after a delete action the valueIsAdjusting is false, so not go into the if to modified data that are in removing step
			//from another thread locked
			if(e.getValueIsAdjusting()) {
			indexSelectedRow = table.getSelectedRow();
            nameSupplierTF.setText((String)tableModel.getValueAt(indexSelectedRow, 0));
            addressSupplierTF.setText((String)tableModel.getValueAt(indexSelectedRow, 1));
            telephoneSupplierTF.setText((String)tableModel.getValueAt(indexSelectedRow, 2));
			}
		}
      });
        
      
        
        
		
		JLabel lblNewLabel_3 = new JLabel("Supplier management");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(350, 10, 219, 44);
		frame.getContentPane().add(lblNewLabel_3);	
		
	}

	
	
	class SupplierListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String ac = e.getActionCommand();			
			switch(ac) {
			case "Add": 
				addSupplier();
			break;
			case "Update": 
				updateSupplier();
			break;
			case "Delete": 
				deleteSupplier();
			break;
			}
		}

		private void addSupplier() {
			Integer id = new Random().nextInt(100000);
			String sTelephone = telephoneSupplierTF.getText();
			int telephone = Integer.parseInt(sTelephone);
			Supplier supplier = new Supplier( id, nameSupplierTF.getText(), addressSupplierTF.getText(), telephone );			
			//add to table and clean text field
			Object[] dataToShow = new Object[]{ nameSupplierTF.getText(), addressSupplierTF.getText(), sTelephone};
			tableModel.addRow(dataToShow);
			supplierController.addSupplier(supplier);
			nameSupplierTF.setText("");
			addressSupplierTF.setText("");
			telephoneSupplierTF.setText("");
		}
		
		private void updateSupplier() {
			// get old supplier from table without modification
			Supplier oldSupplier = supplierController.getSupplier(tableModel.getValueAt(indexSelectedRow, 0).toString(),
					tableModel.getValueAt(indexSelectedRow, 1).toString(), 
					tableModel.getValueAt(indexSelectedRow, 2).toString());
			Supplier supplierWithNewData = supplierController.updateSupplier(oldSupplier.getId(),nameSupplierTF.getText(), addressSupplierTF.getText(), telephoneSupplierTF.getText());
			tableModel.setValueAt(supplierWithNewData.getName(), indexSelectedRow, 0);
			tableModel.setValueAt(supplierWithNewData.getAddress(), indexSelectedRow, 1);
			tableModel.setValueAt(supplierWithNewData.getTelephoneAsString(), indexSelectedRow, 2);

		}
		
		private void deleteSupplier() {	
			if(table.getSelectedRow() != -1) 
            {
			Supplier oldSupplier = supplierController.getSupplier(tableModel.getValueAt(indexSelectedRow, 0).toString(),
					tableModel.getValueAt(indexSelectedRow, 1).toString(), 
					tableModel.getValueAt(indexSelectedRow, 2).toString());
			supplierController.delete(oldSupplier.getId());
			tableModel.removeRow(table.getSelectedRow());
			JOptionPane.showMessageDialog(null, "Deleted successfully");
			
		}
		}
		
	}
	

	
		
}


