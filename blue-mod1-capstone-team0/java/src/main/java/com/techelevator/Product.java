package com.techelevator;

public class Product {
	
	private String productCode;
	private String name;
	private Double price;
	private String type;
	private int quantity;
	private String sound;
	
	
	
	public Product (String productCode, String name, Double price,
			String type, int quantity) {
		this.productCode = productCode;
		this.name = name;
		this.price = price;
		this.type = type;
		this.quantity = quantity;
		sound = "";
		
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

//	public abstract String getSound(String type){
//  	if(type == Drink){
//		  String sound = "Klug Klug ,Yum!;
//		}

	
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSound() {
		if(type.contains("Gum")) {
			sound = "Chew Chew, Yum!";
		} else if (type.contains("Drink")) {
			sound = "Glug Glug, Yum!";
		} else if (type.contains("Chip")) {
			sound = "Crunch Crunch, Yum!";
			} else {
				sound = "Munch Munch, Yum!";
			}
		return sound;
	
	
	}

	public int getQuantity() {
		return quantity;
	}
	
	public int decrementQuantity() {
		return quantity--;
	}
}
