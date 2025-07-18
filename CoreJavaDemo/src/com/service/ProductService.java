package com.service;

import java.util.List;

import com.model.Product;

public interface ProductService {
	
	void addProduct(Product p);
	void deleteProduct(int index);
	List<Product> getAllProducts();	
	List<Product> searchProduct(String sdata);
	List<Product> searchCompany(String sdata);
	void updateProduct(Product p);
}
