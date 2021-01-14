package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.htwg.selenium.HtwgWebDriver;

class GoogleTest {
	
	private static HtwgWebDriver htwgWebDriver;

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		htwgWebDriver = new HtwgWebDriver(); // "--headless"
	}

	@AfterEach
	void tearDownAfterClass() throws Exception {
		htwgWebDriver = null;
	}

	@Test
	void testGoogle() throws Exception {
		htwgWebDriver.get("https://www.google.de");
		htwgWebDriver.sleep(500);

		htwgWebDriver.close();
	}
}
