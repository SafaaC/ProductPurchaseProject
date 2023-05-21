package productPurchase;

public class Product {
	private String productName;
	private int quantity;
	private double price;
	private boolean giftWrap;
	private double total;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean getGiftWrap() {
		return giftWrap;
	}

	public void setGiftWrap(boolean giftWrap) {
		this.giftWrap = giftWrap;
	}

	public Product(String productName, int quantity, double price, boolean giftWrap, double total) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.giftWrap = giftWrap;
		this.total = total;
	}

}
