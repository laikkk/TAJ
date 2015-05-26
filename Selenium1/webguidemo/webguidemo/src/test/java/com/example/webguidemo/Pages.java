package com.example.webguidemo;

import org.jbehave.web.selenium.WebDriverProvider;

import com.example.webguidemo.pages.Sport;
import com.example.webguidemo.pages.Home;

public class Pages {

	private WebDriverProvider driverProvider;
	
	//Pages
	private Home home;
	private Sport sport;
	// ...

	public Pages(WebDriverProvider driverProvider) {
		super();
		this.driverProvider = driverProvider;
	}

	public Home home() {
		if (home == null) {
			home = new Home(driverProvider);
		}
		return home;
	}
	
	public Sport sport(){
		if (sport == null) {
			sport = new Sport(driverProvider);
		}
		return sport;
	}
}
