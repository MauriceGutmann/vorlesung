package de.htwg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestCalculator {
	
	private static Calculator cal;

	@BeforeAll
	public static void setUpBeforeAll() throws Exception {
		cal = new Calculator();
	}
	@Test
	public void testAdd() {
		assertEquals(14,cal.add(5, 9),0);
	}
	@Test
	public void testDivWithDivByZero() throws MyException {
		Assertions.assertThrows(MyException.class, () -> {
		cal.div(7,0);
	    });
	}
	@Test
	public void testDiv() throws MyException {
		assertEquals(2.1,cal.div(9, 4),0.2);
	}
}