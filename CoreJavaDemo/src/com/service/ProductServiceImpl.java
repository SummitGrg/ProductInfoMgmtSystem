package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.model.Product;

public class ProductServiceImpl implements ProductService {

	static List<Product> plist = new ArrayList<>();
	
	@Override
	public void addProduct(Product p) {
	
		String jdbcURL = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "truck99";
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(jdbcURL,username,password);
			
			String sql = "insert into product(id,name,price,company)values('"+p.getId()+"','"+p.getName()+"','"+p.getPrice()+"','"+p.getCompany()+"')";
			Statement stm = connection.createStatement();
			stm.execute(sql);
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteProduct(int id) {
		String jdbcURL = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "truck99";
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(jdbcURL,username,password);
			
			String sql = "delete from product where id = " + id;
			Statement stm = connection.createStatement();
			stm.execute(sql);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Product> getAllProducts() {
		
		List<Product> productList = new ArrayList<>();
		
		String jdbcURL = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "truck99";
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(jdbcURL,username,password);
			
			String sql = "select * from product";
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setCompany(rs.getString("company"));
				
				productList.add(p);
				
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productList;
	}

	@Override
	public List<Product> searchProduct(String sdata) {
		
		List<Product> productList = new ArrayList<>();
		
		String jdbcURL = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "truck99";
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(jdbcURL,username,password);
			
			String sql = "select * from product where name like '"+sdata+"%'";
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setCompany(rs.getString("company"));
				
				productList.add(p);
				
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productList;
	}
	
	@Override
	public List<Product> searchCompany(String sdata) {
		
		List<Product> productList = new ArrayList<>();
		
		String jdbcURL = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "truck99";
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(jdbcURL,username,password);
			
			String sql = "select * from product where company like '"+sdata+"%'";
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setCompany(rs.getString("company"));
				
				productList.add(p);
				
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productList;
	}


	@Override
	public void updateProduct(Product p) {
		String jdbcURL = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "truck99";
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(jdbcURL,username,password);
			
			String sql = "update product set name='"+p.getName()+"',price='"+p.getPrice()+"',company='"+p.getCompany()+"' where id="+p.getId()+"";
			Statement stm = connection.createStatement();
			stm.execute(sql);
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}