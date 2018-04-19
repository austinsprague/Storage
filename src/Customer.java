/**
 * Represents the Customer Object for the Storage Location
 * @author Sprague
 *
 */

public class Customer {
	private String name;
	private String phoneNum;
	private double accountBalance;
	
	/**
	 * Customer constructor
	 * @param name The string of the Name of the Customer
	 * @param phoneNum The string of the phone number of the Customer 
	 */
	public Customer(String name, String phoneNum) {
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name input is invalid");
		}
		
		if (!phoneNum.matches("[0-9]{10}")) {
		    throw new IllegalArgumentException("Phone numbers must be ten digits");
		}
		
		this.name = name;
		this.phoneNum = phoneNum;
		this.accountBalance = 0.0;
	}
	
	/**
	 * Get the name of the Customer
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the Phone Number of the Customer
	 * @return
	 */
	public String getPhone() {
		return phoneNum;
	}
	
	/**
	 * Get the Account Balance of the Customer
	 * @return
	 */
	public double getAccountBalance() {
		return accountBalance;
	}
	
	/**
	 * Set the name of the Customer
	 * @param name String of the Customer's name, must not be empty
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set the phone number of the customer
	 * @param phoneNum String of the phone number for the customer.
	 */
	public void setPhoneNum(String phoneNum) {
		if (!phoneNum.matches("[0-9]{10}")) {
		    throw new IllegalArgumentException("Phone numbers must be ten digits");
		}
		
		this.phoneNum = phoneNum;
	}
	
	/**
	 * Charge the Customer's Account
	 * @param priceOfUnit The Double that is used to increase the balance of the Customer
	 * @return Account Balance after charging customer
	 */
	public double chargeAccountBalance(double priceOfUnit) {
		if(priceOfUnit < 0) {
			throw new IllegalArgumentException("Invalid Price of Unit to charge");
		}
		
		this.accountBalance += priceOfUnit;
		return accountBalance;
	}
	
	/**
	 * Credit the Customer's Account
	 * @param priceOfUnit The Double that is used to decrease the balance of the Customer
	 * @return Account balance of the Customer after credited amount
	 */
	public double creditAccountBalance(double priceOfUnit) {
		if(priceOfUnit < 0) {
			throw new IllegalArgumentException("Invalid Price of Unit to credit");
		}
		
		this.accountBalance -= priceOfUnit;
		return accountBalance;
	}
	
	/**
	 * Concats all the information from the Customer Object
	 */
	public String toString() {
		String str = "Name: " + name + ", Phone Number: " + phoneNum + ", Account Balance: " + accountBalance;
		return str;
	}
}
