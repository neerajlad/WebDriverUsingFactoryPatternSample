package com.Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.WebAutomation.BrowserType;
import com.WebAutomation.BrowserFactory;
import com.WebAutomation.IBrowser;
import com.WebAutomation.Locator;

public class Test_01 {

	BrowserFactory browserFactory = new BrowserFactory();
	IBrowser browser;
	
	@Test
	public void Test1() {
		browser.findElement(Locator.NAME, "userName").sendKeys("testuser");
		browser.findElement(Locator.NAME, "password").sendKeys("testpassword");
		browser.findElement(Locator.NAME, "login").click();
	}

	@BeforeClass
	public void beforeClass() {
		browser = browserFactory.getBrowser(BrowserType.CHROME);	
		browser.openURL("http://newtours.demoaut.com");
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}

}
