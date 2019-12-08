package com.myshop.order.domain;

import java.util.List;

public class OrderRequest {

	private String email;
	
	private List<Long> products;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Long> getProducts() {
		return products;
	}

	public void setProducts(List<Long> products) {
		this.products = products;
	}
	
	
}
