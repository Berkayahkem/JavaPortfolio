
public class CorporateClient implements Customer {
	int id;
	String name;
	String email;
	
	public CorporateClient(int id, String name, String email) {
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
		if(totalCost > 500)
			return (totalCost * 0.85);
		return totalCost;
	}
	
	public void displayInfo() {
		System.out.println("Customer ID: " + this.id);
		System.out.println("Name: " + this.name);
		System.out.println("Email: " + this.email);
		System.out.println("Type: Corporate Client");
	}
}
