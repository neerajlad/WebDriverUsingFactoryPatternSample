package com.WebAutomation;

public class BrowserFactory {

	public IBrowser getBrowser(BrowserType browserType) {
		return new Browsers(browserType);
	}
}
