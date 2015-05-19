package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.* ;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.jayway.restassured.RestAssured;

public class CarServiceTest {
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo";
	}
	
	@Test
	public void getCar(){
		get("/cars/0").then().assertThat().body("model", equalTo("Corsa"));
		
		
		Car aCar = get("/cars/0").as(Car.class);
		assertThat(aCar.getMake(), equalToIgnoringCase("Opel"));
	}
	
	@Test
	public void addCar(){
		
		Car aCar = new Car(2, "Ford", "Fiesta", 2011);
		given().
		       contentType("application/json; charset=UTF-16").
		       body(aCar).
		when().	     
		post("/cars/").then().assertThat().statusCode(201).body(containsString("Car saved:"));
	}
	

}
