package com.WebAutomation;

import org.openqa.selenium.WebElement;

public interface IBrowser {

	public void createWebDriver(BrowserType browserType);
	public WebElement findElement(Locator locator,String locatorValue);
	public void type(String value);
	public void click();
	public void openURL(String url);
	public void quit();
}
