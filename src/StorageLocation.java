/**
 * 
 */

/**
 * Represents a storage location facility that holds storage units and rents to customers.
 * @author Sprague
 *
 */
public class StorageLocation {

	/**
	 * @param args 
	 */
	private String designation;
	private Unit[][] units;
	private Customer[] customers;
	private int numOfCustomers;
	
	/**
	 * Constructor for Storage Location
	 * @param designation The String must fit the specified pattern format.
	 * Two upper-case letters indicating the state, 
	 * followed by two digits representing the location number within the state, 
	 * followed by the city name.
	 * i.e. “WA23Issaquah” and “OR02Ashland”.
	 */
	public StorageLocation(String designation) {
		if(!designation.matches("[A-Z]{2}[0-9]{2}[A-Z][A-Za-z]+")) { //IF DESIGNATION DOES NOT HAVE TWO LETTERS, followed by two numbers and rest are all letters
			 throw new IllegalArgumentException(
					 "Designation doesn't match required format"
					 );	
		}
		
		this.designation = designation;
		numOfCustomers = 0;
		units = new Unit[12][20];
		customers = new Customer[100];
		
		for(int row =0; row < units.length; row++) {
			// SET ATTRIBUTES FOR STANDARD UNITS
			if (row <= 6 ) {
				int width = 8;
				int height = 10;
				int len = 10; 
				double price = 50.0;
				
				for(int col = 0; col<units[row].length; col++) {
					units[row][col]= new Unit( Unit.UnitType.STANDARD, width, len, height, price );
				}
			}
			
			// SET ATTRIBUTES FOR TEMPERATURE CONTROLLED UNITS
			if (row >= 7 && row <= 9  ) {
				int width = 8;
				int height = 10;
				int len = 5; 
				double price = 70.0;
				
				for(int col = 0; col<units[row].length; col++) {
					units[row][col]= new Unit(Unit.UnitType.TEMPERATURE_CONTROLLED, width, len, height, price);
				}
			}
			
			// SET ATTRIBUTES FOR HUMIDITY CONTROLLED UNITS
			if (row >= 10 ) {
				int width = 8;
				int height = 10;
				int len = 5; 
				double price = 80.0;
				
				for(int col = 0; col<units[row].length; col++) {
					units[row][col]= new Unit(Unit.UnitType.HUMIDITY_CONTROLLED, width, len, height, price);
				}
			}

		}
	}
	
	/**
	 * Get the Storage Location designation.
	 * @return The designation String of Storage Location.
	 */
	public String getDesignation(){
		return designation;
	}
	
	/**
	 * Retrieves the unit at a specified row and column.
	 * @param row The specified row.
	 * @param col The specified column.
	 * @return The unit at the specified row and column.
	 */
	public Unit getUnit(int row, int col) {
		return units[row][col];
	}
	
	/**
	 * Adds a customer to the StorageLocation.
	 * @param customer The Customer object to be added.
	 */
	public void addCustomer(Customer customer) {
		customers[numOfCustomers] = customer;
		numOfCustomers ++;
	}
	
	/**
	 * Get the Customer by index of Customer Array
	 * @param index Integer index of the Customer array 
	 * @return Customer Object
	 */
	public Customer getCustomer(int index) {
		return customers[index];
	}
	
	/**
	 * Get the number of Customers at the Storage Location
	 * @return Integer number of customers
	 */
	public int getCustomerCount() {
		return numOfCustomers;
	}
	
	/**
	 * Get all the units that are rented by specified Customer.
	 * @param customer Customer Object that needs the  
	 * @return Array of Units that are rented by Customer.
	 */
	public Unit[] getUnitsForCustomer(Customer customer) {
		int numOfUnits = 0;

		//		COUNT IF CUSTOMER MATCHES THE UNIT, THEN COUNT
		for( int row=0; row < units.length; row ++ ) {
			for( int col=0; col<units[row].length; col++ ) {
				Customer cstmr = units[row][col].getCustomer();
				if (cstmr == customer) {
					numOfUnits++;
				}
			}
		}
		
		//		Iterate through the units array again, check if customer matches unit, and add the unit to a new array
		Unit[] customerUnits; 
		customerUnits = new Unit[numOfUnits];
		int unitIndex = 0;
		
		for( int row=0; row < units.length; row ++ ) {
			for( int col=0; col<units[row].length; col++ ) {
				Customer cstmr = units[row][col].getCustomer();
				if (cstmr == customer) {
					customerUnits[unitIndex] = units[row][col];
					unitIndex++;
				}
			}
		}
		return customerUnits;
	}
	
	/**
	 * Get an array of all the empty units for the Storage Location
	 * @return Array of Units that are empty.
	 */
	public Unit[] getEmptyUnits() {
		return getUnitsForCustomer(null);
	}
	
	/**
	 * Get all the empty units that are by the specified Unit Type
	 * @param unitType Storage unit type
	 * @return Array of Units by specified Unit Type. 
	 */
	public Unit[] getEmptyUnitsByType(Unit.UnitType unitType) {
		int numOfUnits = 0;

		//		COUNT IF CUSTOMER MATCHES THE UNIT && UNITTYPE MATCHES, THEN COUNT
		for( int row=0; row < units.length; row ++ ) {
			for( int col=0; col<units[row].length; col++ ) {
				Customer cstmr = units[row][col].getCustomer();
				Unit.UnitType type = units[row][col].getUnitType();
				if (cstmr == null && type == unitType) {
					numOfUnits++;
				}
			}
		}
		
		//		Iterate through the units array again, check if customer is null and matches unit type, and add the unit to a new array
		Unit[] customerUnits; 
		customerUnits = new Unit[numOfUnits];
		int unitIndex = 0;
		
		for( int row=0; row < units.length; row ++ ) {
			for( int col=0; col<units[row].length; col++ ) {
				Customer cstmr = units[row][col].getCustomer();
				Unit.UnitType type = units[row][col].getUnitType();
				
				if (cstmr == null && type == unitType) {
					customerUnits[unitIndex] = units[row][col];
					unitIndex++;
				}
			}
		}
		return customerUnits;
	}
	
	/**
	 * Charge all customers at Storage Location by the Unit they are renting. 
	 */
	public void chargeCustomer() {
		
		for( int row=0; row < units.length; row ++ ) {
			for( int col=0; col<units[row].length; col++ ) {
				Unit unit = units[row][col];
				Customer cstmr = unit.getCustomer();
				
				if (cstmr != null) {
					double amt = unit.getPrice();
					cstmr.chargeAccountBalance(amt);
				}
			}
		}
	}
	
	/**
	 * Build a string of all information for Storage Location Class
	 */
	public String toString() {
		String str = "Designation: " + designation + "\nUnits: ";
		
		//		Iterate and concat each Unit object		
		for ( int row = 0; row < units.length; row++ ) {
			str = str + "\nUnit Row: " + row;
			for ( int col = 0; col < units[row].length; col++ ) {
				str = str + " Col: " + col;
				str = str + "\n" + units[row][col].toString();
			}
		}
		
		//		Iterate and concat each Customer Object
		str = str + "\nNumber of Customers: " + numOfCustomers;
		for ( int i = 0; i < customers.length; i++ ) {
			Customer cstmr = customers[i];
			
			str = str + "\nCustomer: " + i;
			str = str + "\n" + cstmr.toString();
		}
		return str;
	}

}
