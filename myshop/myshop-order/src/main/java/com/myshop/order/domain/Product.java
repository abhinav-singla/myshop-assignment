package com.myshop.order.domain;

import java.math.BigDecimal;

public class Product {

	private Long id;
	
	private String name;
	
	private String description;
	
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

}
