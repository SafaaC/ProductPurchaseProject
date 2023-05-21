package productPurchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PurchaseFuntionsImpl implements PurchaseFunctions {
	Scanner sc = new Scanner(System.in);

	// creating a Map for Storing Catalog
	private static Map<String, Integer> catalog = new HashMap<>();
	static {
		catalog.put("Product A", 20);
		catalog.put("Product B", 40);
		catalog.put("Product C", 50);
	}

	private static final int giftWrapFee = 1;
	private static final int shippingFeePerPackage = 5;
	private static final int unitsPerPackage = 10;

	// Creating a Map for Storing Product Objects with key as Product Name
	Map<String, Product> purchase = new LinkedHashMap<String, Product>();

	private double subTotal = 0;
	private double totalQuantity = 0;

	// Defining a Function for accepting the Product input from the user And add it
	// into purchase map
	public void addProduct() throws InputMismatchException {

		Set<String> prods = catalog.keySet();
		for (String product : prods) {
			System.out.println();
			System.out.println(product);
			System.out.println("--------");
			System.out.println("Enter Quantity:");
			int pQuantity = sc.nextInt();
			if (pQuantity == 0) {
				continue;
			}
			double pPrice = catalog.get(product);
			System.out.println("Gift Wrap Needed(Type - true/false) :");
			boolean pGiftWrap = sc.nextBoolean();
			double pTotal = pQuantity * pPrice;
			subTotal += pTotal;
			totalQuantity += pQuantity;
			Product prod = new Product(product, pQuantity, pPrice, pGiftWrap, pTotal);
			purchase.put(product, prod);

		}

	}

	// Function to find Didcount Details which returns Discount Name And Amount
	List discount(Map<String, Product> purchase, double totalQuantity, double subTotal) {
		double maxDiscount = 0;
		String discountApplied = "";
		List list = new ArrayList();

		if (subTotal > 200) {
			double discountAmount = 10;
			if (discountAmount > maxDiscount) {
				maxDiscount = discountAmount;
				discountApplied = "Flat_10_Discount";
			}

		}
		if (totalQuantity > 20) {
			double discountAmount = (10 / 100) * subTotal;
			if (discountAmount > maxDiscount) {
				maxDiscount = discountAmount;
				discountApplied = "Bulk_10_Discount";
			}

		}
		Set<String> keys = purchase.keySet();
		for (String key : keys) {
			Product p = purchase.get(key);
			if (p.getQuantity() > 10) {
				double discountAmount = (5 / 100) * p.getTotal();
				if (discountAmount > maxDiscount) {
					maxDiscount = discountAmount;
					discountApplied = "Bulk_5_Discount";
				}
			}
			if (totalQuantity > 30 && p.getQuantity() > 15) {
				int discountQuantity = p.getQuantity() - 15;
				double discountOnPrice = discountQuantity * p.getPrice();
				double discountAmount = 0.5 * discountOnPrice;
				if (discountAmount > maxDiscount) {
					maxDiscount = discountAmount;
					discountApplied = "tiered_5_Discount";
				}
			}
		}
		list.add(discountApplied);
		list.add(maxDiscount);
		return list;
	}

	// Function for displaying output
	public void display() {
		int subtotal = 0;
		int totalDiscount = 0;
		int shippingFee = 0;
		int giftWrapFeeAmount = 0;
		Set<String> keys = purchase.keySet();
		for (String key : keys) {
			System.out.println();
			System.out.println("Product name :" + key);
			System.out.println("Quantity " + purchase.get(key).getQuantity());
			System.out.println("Total amount of " + key + " is " + purchase.get(key).getTotal());
			System.out.println();
			if (purchase.get(key).getGiftWrap() == true) {
				// Calculate GiftWrap Amount for each product
				giftWrapFeeAmount += purchase.get(key).getQuantity() * giftWrapFee;
			}
		}
		// discount name applied and discount amount
		List discountDetails = discount(purchase, totalQuantity, subTotal);

		String discountName = (String) discountDetails.get(0);
		double discountAmount = (double) discountDetails.get(1);

		if (discountAmount != 0) {
			System.out.println("Discount Applied :" + discountName);
			System.out.println("Discount Amount :" + discountAmount);
		} else {
			System.out.println("Not Eligible For any Discount");
		}

		// Calculate shipping fee
		shippingFee = (int) Math.ceil((double) totalQuantity / unitsPerPackage) * shippingFeePerPackage;
		System.out.println("Shipping fee is :" + shippingFee);

		System.out.println("Gift Wrap fee is :" + giftWrapFeeAmount);

		// Calculate total
		double total = subTotal - discountAmount + shippingFee + giftWrapFeeAmount;

		System.out.println("\nTotal Amount is:" + total);
	}
}
