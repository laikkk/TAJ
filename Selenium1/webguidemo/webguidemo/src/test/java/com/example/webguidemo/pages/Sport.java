package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class Sport extends WebDriverPage{
	
	public Sport(WebDriverProvider driverProvider) {
		super(driverProvider);		
	}

	public void open() {
		get("http://www.teleman.pl/sport");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

}
