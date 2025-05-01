package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.model.Product;
import com.service.ProductService;
import com.service.ProductServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProductForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField priceTxt;
	private JTextField pid;
	private JTable table;
	private JTextField searchNameTxt;
	private JComboBox companyCmb;
	private JTextField searchCompanyTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductForm frame = new ProductForm();
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
	public ProductForm() {
		setTitle("ProductForm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Prod ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 67, 113, 14);
		contentPane.add(lblNewLabel);
		
		pid = new JTextField();
		pid.setBounds(85, 66, 96, 20);
		contentPane.add(pid);
		pid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 98, 54, 14);
		contentPane.add(lblNewLabel_1);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(85, 97, 96, 20);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(30, 129, 73, 14);
		contentPane.add(lblNewLabel_2);
		
		priceTxt = new JTextField();
		priceTxt.setBounds(85, 128, 96, 20);
		contentPane.add(priceTxt);
		priceTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Company");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 173, 73, 20);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_3);
		
		companyCmb = new JComboBox();
		companyCmb.setBounds(97, 175, 84, 20);
		companyCmb.setModel(new DefaultComboBoxModel(new String[] {"Select", "Dell", "Lenovo", "Acer", "Apple", "HP"}));
		contentPane.add(companyCmb);
		
		JButton addBtn = new JButton("ADD PRODUCT");
		addBtn.setBounds(31, 223, 129, 23);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = nameTxt.getText();
				int price = Integer.parseInt(priceTxt.getText());
				String company = companyCmb.getSelectedItem().toString();
				
				Product p = new Product();
				
				p.setId(Integer.parseInt(pid.getText()));
				p.setName(name);
				p.setCompany(company);
				p.setPrice(price);
				
				ProductService service = new ProductServiceImpl();
				service.addProduct(p); 
				
				JOptionPane.showMessageDialog(null, "Added successfully!");
				
				//clear input form
				clearInputForm();
														   						
				
			}

		
		});
		contentPane.add(addBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(201, 78, 531, 295);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Company", "Price"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton deleteBtn = new JButton("DELETE");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please, select data.");
					return;
				}
				
				int srow = table.getSelectedRow();
				int pid = (int)table.getModel().getValueAt(srow, 0);
				
				ProductService service = new ProductServiceImpl();
				service.deleteProduct(pid);
				
				JOptionPane.showMessageDialog(null, "Deleted successfully!");
				
				//display all data after deleting the data
				displayData();
				
				
			}
		});
		deleteBtn.setBounds(326, 384, 89, 23);
		contentPane.add(deleteBtn);
		
		JButton btnNewButton_1 = new JButton("PRINT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					table.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(235, 384, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("EDIT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Select any row.");
					return;
				}
				
				int srow = table.getSelectedRow();
				
				pid.setText(table.getModel().getValueAt(srow, 0).toString());
				nameTxt.setText(table.getModel().getValueAt(srow, 1).toString());
				companyCmb.setSelectedItem(table.getModel().getValueAt(srow, 2).toString());
				priceTxt.setText(table.getModel().getValueAt(srow, 3).toString());
				
				
				pid.setEnabled(false);
				addBtn.setEnabled(false);
				
				
			}
		});
		btnNewButton_2.setBounds(420, 384, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_4 = new JLabel("Product Details");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(233, 21, 182, 35);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Search by product name:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(430, 11, 187, 20);
		contentPane.add(lblNewLabel_5);
		
		searchNameTxt = new JTextField();
		searchNameTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String sdata = searchNameTxt.getText().trim();
				ProductService service = new ProductServiceImpl();
				
				List<Product> plist = service.searchProduct(sdata);
				
				DefaultTableModel tmodel = (DefaultTableModel)table.getModel();
				tmodel.setRowCount(0); //reset table
				
				for(Product pd: plist) {
					tmodel.addRow(new Object[] {pd.getId(),pd.getName(),pd.getCompany(),pd.getPrice()});
					
				}									
			}
		});
		searchNameTxt.setBounds(617, 13, 96, 20);
		contentPane.add(searchNameTxt);
		searchNameTxt.setColumns(10);
		
		JButton updateBtn = new JButton("UPDATE");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Product p = new Product();
				
				p.setId(Integer.parseInt(pid.getText()));
				p.setName(nameTxt.getText());
				p.setPrice(Integer.parseInt(priceTxt.getText()));
				p.setCompany(companyCmb.getSelectedItem().toString());
				
				ProductService service = new ProductServiceImpl();
				service.updateProduct(p);
				
				JOptionPane.showMessageDialog(null, "Updated successfully!");
				displayData();
				
				updateBtn.setEnabled(false);
				addBtn.setEnabled(true);
				pid.setEnabled(true);
				clearInputForm();
			}
		});
		updateBtn.setBounds(34, 258, 89, 23);
		contentPane.add(updateBtn);
		
		JLabel lblSearchByProduct = new JLabel("Search by product company:");
		lblSearchByProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSearchByProduct.setBounds(409, 42, 208, 20);
		contentPane.add(lblSearchByProduct);
		
		searchCompanyTxt = new JTextField();
		searchCompanyTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String sdata = searchCompanyTxt.getText().trim();
				ProductService service = new ProductServiceImpl();
				
				List<Product> plist = service.searchCompany(sdata);
				
				DefaultTableModel tmodel = (DefaultTableModel)table.getModel();
				tmodel.setRowCount(0); //reset table
				
				for(Product pd: plist) {
					tmodel.addRow(new Object[] {pd.getId(),pd.getName(),pd.getCompany(),pd.getPrice()});
					
				}									

			}
		});
		searchCompanyTxt.setBounds(617, 47, 96, 20);
		contentPane.add(searchCompanyTxt);
		searchCompanyTxt.setColumns(10);
		
		displayData();
	}
	
	//display data in JTable
	private void displayData() {
		
		ProductService service = new ProductServiceImpl();
		List<Product> plist = service.getAllProducts();
		
		DefaultTableModel tmodel = (DefaultTableModel)table.getModel();
		tmodel.setRowCount(0); //reset table
		
		for(Product pd: plist) {
			tmodel.addRow(new Object[] {pd.getId(),pd.getName(),pd.getCompany(),pd.getPrice()});
			
		}
		
	}
	
	private void clearInputForm() {
		pid.setText("");
		nameTxt.setText("");
		priceTxt.setText("");
		companyCmb.setSelectedIndex(0);
	}
}