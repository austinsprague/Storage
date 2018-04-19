import java.time.LocalDate;

/**
 * Represents the single Unit of the Storage Location
 * @author Sprague
 *
 */

public class Unit {
	private Customer customer;
	private UnitType unitType;
	private int unitWidth, unitLength, unitHeight;
	private double standardPrice, rentedPrice;
	private LocalDate startDate;
	
	/**
	 * Enum for the Unit Types
	 * @author Sprague
	 *
	 */
	public enum UnitType {
		STANDARD, HUMIDITY_CONTROLLED, TEMPERATURE_CONTROLLED
	}
	
	/**
	 * Constructor for Unit
	 * @param unitType UnitType enum based on the type of unit of the Storage Location
	 * @param unitWidth Integer Width of the unit
	 * @param unitLength Integer Length of the unit
	 * @param unitHeight Integer Height of the unit
	 * @param standardPrice Double of the standard price for the specified unit
	 */
	public Unit(UnitType unitType, int unitWidth, int unitLength, int unitHeight, double standardPrice) {
		if ( unitWidth < 0 || unitLength < 0 || unitHeight < 0 ) {
			throw new IllegalArgumentException("Invalid Unit Size");
		}
		
		this.unitType = unitType;
		this.unitWidth = unitWidth;
		this.unitLength = unitLength;
		this.unitHeight = unitHeight;
		this.standardPrice = standardPrice;
	}
	
	/**
	 * Get the Customer for the unit
	 * @return
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Get the Unit Type for the unit
	 * @return UnitType of the unit
	 */
	public UnitType getUnitType() {
		return unitType;
	}
	
	/**
	 * Get the width of the Unit
	 * @return Integer width of the unit.
	 */
	public int getUnitWidth() {
		return unitWidth;
	}
	
	/**
	 * Get the height of the Unit
	 * @return Integer height of the unit.
	 */
	public int getUnitHeight() {
		return unitHeight;
	}
	
	/**
	 * Get the length of the Unit
	 * @return Integer length of the unit.
	 */
	public int getUnitLength() {
		return unitLength;
	}
	
	/**
	 * Get the price of the unit
	 * @return Price of the unit, if rented, the rented price. If unrented, standard price
	 */
	public double getPrice() {
		if (customer != null) {
			return rentedPrice;
		}
		return standardPrice;
	}
	
	/**
	 * Get the rental start date of the Unit
	 * @return Date of rental started.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	
	/**
	 * Rent the unit to a customer
	 * @param customer Customer object to assign to the unit
	 * @param date LocalDate for the start date of the unit
	 * @param price Double of the price the unit is being rented
	 */
	public void rent(Customer customer, LocalDate date, double price) {
		if (price < 0) {
			throw new IllegalArgumentException("Invalid price of unit to rent");
		}
		this.customer = customer;
		this.startDate = date;
		this.rentedPrice = price;
	}
	
	/**
	 * Unrent the unit, remove all information about the customer, price and start date. 
	 */
	public void unRent() {
		this.customer = null;
		this.startDate = null;
		this.rentedPrice = 0;
	}
	
	/**
	 * Concat all the information about the Unit
	 */
	public String toString() {
		String str = "Unit Type: " + unitType + " Dimensions: " 
					+ unitWidth + "Wx" 
					+ unitLength + "Lx" 
					+ unitHeight + "H ";
		
		if (customer != null) {
			str = str + "Customer: " + customer.toString() 
			+ " Start Date: " + startDate +  " Price: " + rentedPrice;
		}
		else {
			str = str + " Price: " + standardPrice;
		}
		
		return "\n" + str;
	}
}
