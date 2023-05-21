package productPurchase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to our shop");
		System.out.println("Menu");
		System.out.println("------------------------------------");
		System.out.printf("%15s%10s", "PRODUCT NAME", "PRICE\n");
		System.out.printf("%15s%10s", "Product A", "$20\n");
		System.out.printf("%15s%10s", "Product B", "$40\n");
		System.out.printf("%15s%10s", "Product C", "$50\n");
		int choice = 1;
		while (choice == 1) {
			// implementing Abstraction by Assigning Implementation class object to Interface reference
			PurchaseFunctions pf = new PurchaseFuntionsImpl();

			// Function to Read Input from the User
			try {
				pf.addProduct();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Invalid Input !!!");
			}
			// Function To Display Output
			pf.display();

			System.out.println("If you want to Continue Shopping press 1");
			choice = sc.nextInt();
		}
		if (choice != 1) {
			System.out.println("Thank You For Shopping \nHave a Good Day");
		}

	}

}
