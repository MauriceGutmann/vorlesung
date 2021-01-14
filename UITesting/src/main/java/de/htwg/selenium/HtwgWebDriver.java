package de.htwg.selenium;

import org.openqa.selenium.WebDriver;


public class HtwgWebDriver {
	private WebDriver webDriver;
	
    public HtwgWebDriver() {
    	HtwgWebDriverInitializer wi = new HtwgWebDriverInitializer();
    	this.webDriver = wi.getWebDriver();
    }
    
    public HtwgWebDriver(String arguments) {
    	HtwgWebDriverInitializer wi = new HtwgWebDriverInitializer(arguments);
    	this.webDriver = wi.getWebDriver();    	
    }
	
    
	public void get(String url) {
    	webDriver.get(url);
    }
	
	public void close() {
		webDriver.manage().deleteAllCookies();
		webDriver.close();
	}
	
	public void sleep(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
