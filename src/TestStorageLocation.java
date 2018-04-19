import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;

public class TestStorageLocation {
	Customer waSeattleCust1;
	String waSeattleDesignation = "WA02Seattle";
	String waSeattleCustName1 = "Test Name";
	String waSeattleCustPhone1 = "7778889999";
	
	StorageLocation waSeattle = new StorageLocation(waSeattleDesignation);

	@Test
	public void testStorageLocationConstructor() {

		assertEquals(waSeattleDesignation, waSeattle.getDesignation());
		assertEquals(0, waSeattle.getCustomerCount());
		assertEquals(Unit.UnitType.STANDARD, waSeattle.getUnit(0,  0).getUnitType());
		assertEquals(Unit.UnitType.TEMPERATURE_CONTROLLED, waSeattle.getUnit(7,  0).getUnitType());
		assertEquals(Unit.UnitType.HUMIDITY_CONTROLLED, waSeattle.getUnit(10,  0).getUnitType());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testStorageLocationInvalidDesignation() {
		String invalidDesignation = "Wa02Issaquah";
		
		new StorageLocation(invalidDesignation);
	}
	
	@Test
	public void testAddCustomer() {
		assertEquals(0, waSeattle.getCustomerCount());
		
		waSeattleCust1 = new Customer(waSeattleCustName1, waSeattleCustPhone1);
		waSeattle.addCustomer(waSeattleCust1);
		
		assertEquals(1, waSeattle.getCustomerCount());
	}
	
	@Test
	public void testGetEmptyUnitsByType() {
		int emptyLength;
		Unit unit1;
		
		waSeattleCust1 = new Customer(waSeattleCustName1, waSeattleCustPhone1);
		waSeattle.addCustomer(waSeattleCust1);
		
		emptyLength = waSeattle.getEmptyUnitsByType(Unit.UnitType.STANDARD).length;
		assertEquals(140, emptyLength);
		
		unit1 = waSeattle.getUnit(0,0);
		unit1.rent(waSeattleCust1, LocalDate.of(2018, 4, 10), unit1.getPrice());
		
		emptyLength = waSeattle.getEmptyUnitsByType(Unit.UnitType.STANDARD).length;
		assertEquals(139, emptyLength);			
	}
	
}
