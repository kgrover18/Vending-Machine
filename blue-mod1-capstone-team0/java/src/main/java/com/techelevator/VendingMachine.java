package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import java.util.Scanner;

import com.techelevator.view.Menu;


public class VendingMachine {
	private static final int MAX_ITEMS = 5;
	private static final int SLOT_ID_FIELD = 0;
	private static final int ITEM_NAME = 1;
	private static final int DATA_TYPE = 3;
	
	List<Product> inventoryList = new ArrayList<>();
	private double balance = 0;
	
	public VendingMachine () {
		createInventory();
	}
	
	Logger auditLog = new Logger();
	
	
	public List createInventory() {
		File inputFile = new File("./vendingmachine.csv");
		
		try(Scanner dataInput = new Scanner(inputFile)){
		while(dataInput.hasNext()) {
			String lineOfInput = dataInput.nextLine();
			String [] data = lineOfInput.split("\\|");
			Double price = Double.parseDouble(data[2]);
			Product product = new Product(data[SLOT_ID_FIELD], data[ITEM_NAME], price, data[DATA_TYPE], MAX_ITEMS);
			
			inventoryList.add(product);
		} } catch (FileNotFoundException e) {
			System.out.println("Error: File not found." + e.getMessage());
		}
		return inventoryList;
		
	}
	
	
	public void feedMoney(String amountToAdd) {

		

		if(amountToAdd.equals("1")) {
			balance = getBalance() + 1.00;
		}else if(amountToAdd.equals("2")) {
			balance = getBalance() + 2.00;
		}else if(amountToAdd.equals("5")) {
			balance = getBalance() + 5.00;
		}else if(amountToAdd.equals("10")) {
			balance = getBalance() + 10.00;
		}else {
			System.out.println("This amount is not valid!");
		}
		try {
		auditLog.addToLog("FEED MONEY: $" + amountToAdd 
				+ " new balance: $" + balance);
		}catch (IOException e) {
			e.printStackTrace();
			
		}
		
		
	}
	
	public void selectProduct(String productCode) {
		for (int i = 0; i < inventoryList.size(); i++) {
			if (inventoryList.get(i).getProductCode().equals(productCode)) {
				if (balance >= inventoryList.get(i).getPrice() && inventoryList.get(i).getQuantity() >= 1) {
					
					System.out.println(inventoryList.get(i).getSound() + "\n" + inventoryList.get(i).getName() 
							+ "\n" + inventoryList.get(i).getPrice());
					
					double balanceBeforePuchase = balance;
					balance = balance - inventoryList.get(i).getPrice();
					String updatedBalance = String.format("%.2f", balance);
					inventoryList.get(i).decrementQuantity();
					
					try {
						auditLog.addToLog(inventoryList.get(i).getName() + " " + inventoryList.get(i).getProductCode()
								+ " $" + balanceBeforePuchase + " $" + updatedBalance );
						}catch (IOException e) {
							e.printStackTrace();
							
						}
					
					
				} else {
					if (balance < inventoryList.get(i).getPrice()) {
						System.out.println("Insufficient Funds");
					} else {
						if (inventoryList.get(i).getQuantity() == 0) {
							System.out.println("Item Sold Out, Choose Again.");
						}
					}
				}
			}
		}
		
		
	}
	
	public void makeChange() {
		double remainingBalanceToChange = balance * 100;
		double remainingBalance = balance;
		
		int quarters = (int)(remainingBalanceToChange / 25);
		remainingBalanceToChange = remainingBalanceToChange % 25;
		int dimes = (int) (remainingBalanceToChange / 10);
		remainingBalanceToChange = (int) (remainingBalanceToChange % 10);
		int nickels = (int) (remainingBalanceToChange / 5);
		 
		balance = 0;
		System.out.println("Your change is " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels. ");
		
		try {
			auditLog.addToLog("GIVE CHANGE: " + remainingBalance + " " + balance);
			}catch (IOException e) {
				e.printStackTrace();
				
			}
	}
	
	
	
	
	public void showInventory() {
		

		for (Product curProduct : inventoryList) {
			String currentPrice = String.format("%.2f", curProduct.getPrice());
			System.out.println(curProduct.getProductCode() + " " + curProduct.getName() + 
					" $" + currentPrice + " Quantity: " + curProduct.getQuantity());
		}
	}
	



	public List<Product> getInventoryList() {
		return inventoryList;
	}

	public double getBalance() {
		return balance;
	}
	
	
	
}


//for(String currentLine : 
//displayInventory()   for: each to iterate through the list in a  method to show the list 
//purchase(); - in the purchase method we handle everything to do with purchase 
//
	
//	
//    work on doing the math calculations in this class
//	delete the individual classes for each product, we dont need them
//	flesh out the rest of functionality in this vending machine class and we have  the bulk of it done!!!