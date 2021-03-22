package com.techelevator;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit"; 
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
	
//	our added constant finals 
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};  

	
	
	Scanner cliScanner = new Scanner(System.in);
	private Menu menu;
	VendingMachine vendingMachine = new VendingMachine();
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	// make a run purchase method and then plug that method into line 29, 
	public void run() throws FileNotFoundException {
	boolean done = false;
		
		while (!done) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
//				vendingMachine.createInventory();
				vendingMachine.showInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				// do purchase - call purchase method in here - all of the menu methods will get called in here , at the end when exit is chosen
//				then set boolean variable to false once user selects exit 
				runPurchaseMenu();
			}else if(choice.equals(MAIN_MENU_OPTION_EXIT)){
				done = true;
			}
			
		}
	}
	public void runPurchaseMenu() {
		boolean done = false;

			while(!done) {
				String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if(choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					
					System.out.println("Please Feed In Money ex. 1, 2, 5, 10 ");
//					unsure about the line below 
					System.out.println(System.lineSeparator() + "Please choose an option >>> ");
					String moneyFed = cliScanner.nextLine();
					vendingMachine.feedMoney(moneyFed);
					String currentBalance = String.format("%.2f", vendingMachine.getBalance());
					System.out.println("Current balance is: $" + currentBalance);

				}else if(choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
					vendingMachine.showInventory();
					System.out.println("\n" + "Select Item by Slot Code: ");
					String productCode = cliScanner.nextLine();
					vendingMachine.selectProduct(productCode);
//					below copy and pasted from above 
					String currentBalance = String.format("%.2f", vendingMachine.getBalance());
					System.out.println("\n" + "Current balance is: $" + currentBalance);
				}else if(choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
					vendingMachine.makeChange();
					String currentBalance = String.format("%.2f", vendingMachine.getBalance());
					System.out.println("\n" + "Current balance is: $" + currentBalance);
					
//					changed to false 
					done = true;
				}
			} 
	}
	

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
//		instantiate vending Machine here
//		cli.run();
		try {
			cli.run();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
