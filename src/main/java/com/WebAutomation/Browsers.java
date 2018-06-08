package com.WebAutomation;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browsers implements IBrowser {

	WebDriver driver;
	WebElement element;

	@Override
	public void createWebDriver(BrowserType browserType) {
		DesiredCapabilities caps;
		switch (browserType) {
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			caps = DesiredCapabilities.chrome();
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			caps.setCapability("pageLoadStrategy", "none");

			ChromeDriverService service = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File(System.getProperty("user.dir") + "/driver/chromedriver.exe"))
					.usingAnyFreePort().build();
			options.merge(caps);
			driver = new ChromeDriver(service, options);
			break;
		case EDGE:
			caps = DesiredCapabilities.internetExplorer();
			EdgeDriverService edgeService = new EdgeDriverService.Builder()
					.usingDriverExecutable(new File(System.getProperty("user.dir") + "/driver/MicrosoftWebDriver.exe"))
					.usingAnyFreePort().build();
			try {
				edgeService.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EdgeOptions option = new EdgeOptions();
			option.merge(caps);
			driver = new EdgeDriver(edgeService, option);
			break;
		case FIREFOX:
			caps = DesiredCapabilities.firefox();
			FirefoxProfile Profile = new FirefoxProfile();
			Profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/xml");
			driver = new FirefoxDriver(caps);	
			break;

		default:
			break;
		}

	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getElement() {
		return element;
	}

	public Browsers(BrowserType browserType) {
		createWebDriver(browserType);
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void setElement(WebElement element) {
		this.element = element;
	}

	@Override
	public WebElement findElement(Locator locator, String value) {
		switch (locator) {
		case CLASSNAME:
			element = driver.findElement(By.className(value));
			break;
		case CSS:
			element = driver.findElement(By.cssSelector(value));
			break;
		case ID:
			element = driver.findElement(By.id(value));
			break;
		case LINKTEXT:
			element = driver.findElement(By.linkText(value));
			break;
		case NAME:
			element = driver.findElement(By.name(value));
			break;
		case PARTIALLINKTEXT:
			element = driver.findElement(By.partialLinkText(value));
			break;
		case TAGNAME:
			element = driver.findElement(By.tagName(value));
			break;
		case XPATH:
			element = driver.findElement(By.xpath(value));
			break;
		default:
			break;
		}
		return element;
	}

	@Override
	public void type(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void click() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openURL(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public void quit() {
		driver.quit();
	}

}
