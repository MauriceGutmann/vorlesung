package de.htwg.selenium;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HtwgWebDriverInitializer {
	
	private WebDriver webDriver;
		
	public HtwgWebDriverInitializer() {
		ChromeOptions chromeOptions = setOptions("");
		this.webDriver = new ChromeDriver(chromeOptions);
	}
	
	public HtwgWebDriverInitializer(String arguments) {
		ChromeOptions chromeOptions = setOptions(arguments);
		this.webDriver = new ChromeDriver(chromeOptions);
	}
	
	private ChromeOptions setOptions(String arguments) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
    	List<String> argumentList = new ArrayList<>();
        argumentList.add("start-maximized");

        if (arguments != null && !"".equalsIgnoreCase(arguments)) {
            String[] argArray = arguments.split(";");
            for (String argument : argArray) {
                argumentList.add(argument);
            }
        }
        chromeOptions.addArguments(argumentList); // e.g.: --headless or --disable-extensions
        return chromeOptions;
	}
	
	public WebDriver getWebDriver() {
	    	return webDriver;
	}
	    
	protected void finalize() {
	    try {
	    	webDriver.quit();
	    }catch(Exception e) {
	    	//do nothing
	    }
	}
}
