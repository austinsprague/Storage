import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		String waSeattleDesignation = "WA02Seattle";
		
		//	Initialize Storage Location with designation
		StorageLocation waSeattle = new StorageLocation(waSeattleDesignation);
		
		//	Create the new customer to add to the Customer Array
		String waSeattleCustName1 = "Jim Bo";
		String waSeattleCustPhone1 = "7778889999";
		
		//	Add the customer to the Customer Array at the Storage Location
		waSeattle.addCustomer(new Customer(waSeattleCustName1, waSeattleCustPhone1));
		System.out.println("CUSTOMER COUNT--> " + waSeattle.getCustomerCount());
		
		Unit.UnitType unitType2 = Unit.UnitType.STANDARD;
		System.out.println("EMPTY " + unitType2 + " UNITS COUNT Before adding Customer--> " + waSeattle.getEmptyUnitsByType(unitType2).length);
		
		//	Rent Unit to Customer
		Customer jim = waSeattle.getCustomer(0);
		Unit unit1 = waSeattle.getUnit(0,0);
		unit1.rent(jim, LocalDate.of(2018, 4, 10), unit1.getPrice());
		
		//	Charge Customer and Return the rent balance
		waSeattle.chargeCustomer();
		System.out.println("Jim's account balance--> " + jim.getAccountBalance());
		
		// GET UNITS BY CUSTOMER
		System.out.println("Jim's unit count--> " + waSeattle.getUnitsForCustomer(jim).length);
		
		//	PRINT EMPTY UNIT COUNT BY TYPE
		Unit.UnitType unitType1 = Unit.UnitType.STANDARD;
		System.out.println("EMPTY " + unitType1 + " UNITS COUNT--> " + waSeattle.getEmptyUnitsByType(unitType1).length);
	}

}
