package de.htwg;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCalculator {
	
	//private static Calculator cal;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//cal = new Calculator();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		Calculator cal = new Calculator();
		assertEquals(cal.add(5, 9),14,0);
		//fail("Not yet implemented");
	}
	
	@Test (expected = MyException.class)
	public void testDivWithDivByZero() throws MyException {
		Calculator cal = new Calculator();
		cal.div(7,0);
	}
	
	@Test
	public void testDiv() throws MyException {
		Calculator cal = new Calculator();
		assertEquals(cal.div(9, 4),2,0.25);
	}

}
