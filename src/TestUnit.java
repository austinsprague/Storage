import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;

public class TestUnit {
	int width = 5;
	int length = 10;
	int height = 10;
	double price = 50.0;
	
	Unit unit1 = new Unit(Unit.UnitType.STANDARD, width, length, height, price);
	
	String name = "Test name";
	String phoneNum = "1234567890";
	String waSeattleDesignation = "WA02Seattle";
	
	Customer customer = new Customer(name, phoneNum);
	
	StorageLocation waSeattle = new StorageLocation(waSeattleDesignation);
	
	@Test
	public void testUnitContstructor() {
		assertEquals(50.0, unit1.getPrice(), 0.00001);
		assertEquals(10, unit1.getUnitLength());
		assertEquals(10, unit1.getUnitHeight());
	}
	
	@Test
	public void testRentUnitToCustomer() {
		unit1 = waSeattle.getUnit(0,0);
		assertEquals(null, unit1.getCustomer());
		assertEquals(50.0, unit1.getPrice(), 0.00001);
		
		unit1.rent(customer, LocalDate.of(2018, 4, 10), 60.0);
		assertEquals(60.0, unit1.getPrice(), 0.00001);
		
		assertEquals(customer, unit1.getCustomer());
		assertEquals(LocalDate.of(2018, 4, 10), unit1.getStartDate());
		assertEquals(Unit.UnitType.STANDARD, unit1.getUnitType());

	}
	
	@Test
	public void testUnrentUnitToCustomer(){
		testRentUnitToCustomer();
		assertEquals(name, unit1.getCustomer().getName());
		unit1.unRent();
		assertEquals(null, unit1.getCustomer());
	}
}
