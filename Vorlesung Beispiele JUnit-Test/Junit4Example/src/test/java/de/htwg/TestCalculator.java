package de.htwg;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCalculator {
	
	private static Calculator cal;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cal = new Calculator();
	}
	@Test
	public void testAdd() {
		assertEquals(14,cal.add(5, 9),0);
	}
	@Test (expected = MyException.class)
	public void testDivWithDivByZero() throws MyException {
		cal.div(7,0);
	}
	@Test
	public void testDiv() throws MyException {
		assertEquals(2.1,cal.div(9, 4),0.20);
	}
}