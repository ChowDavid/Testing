package com.david.mockito;

public class Stock {

	private String code;
	private String name;
	private int stock;
	public Stock(String code, String name, int stock) {
		super();
		this.code = code;
		this.name = name;
		this.stock = stock;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	

}
