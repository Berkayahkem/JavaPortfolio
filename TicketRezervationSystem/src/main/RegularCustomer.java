
public class RegularCustomer implements Customer {
	int id;
	String name;
	String email;
	
	public RegularCustomer(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public int getCustomerID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public double calculateDiscountedPrice(double totalCost) {
		return totalCost;
	}
	
	public void displayInfo() {
		System.out.println("Customer ID: " + this.id);
		System.out.println("Name: " + this.name);
		System.out.println("Email: " + this.email);
		System.out.println("Type: Regular Customer");
	}
}
