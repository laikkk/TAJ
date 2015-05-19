package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PersonsServiceTest {
	// http://www.jayway.com/2013/12/10/json-schema-validation-with-rest-assured/
	SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddhhmmss");
	private PersonManager pm = new PersonManager();
	Person person;

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo";
	}

	@Before
	public void createRandomPerson() {
		// Unique id
		long id = new SessionIdentifierGenerator().nextInt();
		String firstName = "TestUserName";
		int yob = new SessionIdentifierGenerator().nextInt();

		// Create and add
		person = new Person(id, firstName, yob);
		
		System.out.println("///////////////////////////");
		System.out.println("Created Person:");
		System.out.println("id:"+id);
		System.out.println("firstName:"+firstName);
		System.out.println("yob:"+yob);
		System.out.println("///////////////////////////");
		
		pm.addPerson(person);
		person = pm.getPerson(id);
	}

	@After
	public void remveTestDataPerson() {
		System.out.println("Deleting persons...");
		pm.clearPersons();
		System.out.println("Done!");
	}

	@Test
	public void getValidePerson_ShouldReturnCode200() {
		get("/persons/" + person.getId()).then().assertThat().statusCode(200);
	}

	@Test
	public void getValidePerson_ShouldCointainsPersonInBody() {
	    get("/persons/" + person.getId())
	    .then()
	    	.assertThat().body("firstName", equalTo(person.getFirstName()))
	    .and()
	    	.assertThat().body("yob", equalTo(person.getYob()+""))
	    .and()
	    	.assertThat().body("id", equalTo(person.getId()+""));
	}

	@Test
	public void getValidePerson_ShouldReturnResponseWithHeaders() {
		  get("/persons/" + person.getId())
		    .then()
		    	.assertThat().header("Content-Type", "application/json");
	}

	@Test
	public void addPerson() {
		Person tmpPerson = new Person(3, "Marcin", 2011);
		given().
			contentType("application/json").
			accept("application/json").
			body(tmpPerson).when().post("/persons/new/").
		then().
			assertThat().
				statusCode(201).
				body(containsString("Persons saved:")).
				header("Content-Type", "application/json");
	}

	@Test
	public void addWrongPerson() {
		Person tmpPerson = new Person(3, "Test", -213);
		given().
			contentType("application/json").
			accept("application/json").
			body(tmpPerson).
		when().
			post("/persons/new/").
		then().
			assertThat().
			statusCode(400).
			body(containsString("There was an error during creating new Person"));
	}

	@Test
	public void checkPersonSchemaAndCode() {
		InputStream xsd = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("person-car.xsd");

		get("/persons/person/3/cars/xml").
		then().
			assertThat().
			statusCode(200).
			body(matchesXsd(xsd));
	}
	
	@Test
	public void checkPersonSchemaValues() {
		InputStream xsd = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("person-car.xsd");

		get("/persons/person/3/cars/xml").
		then().
			assertThat().
			statusCode(200).
			body(matchesXsd(xsd)).
		body("person.firstName", equalTo("kacper")).
		body("id", equalTo("3")).
		body("yob", equalTo("2013"));
	}

	@Test
	public void checkPersonJSONSchemaAndCode() {
		get("/persons/person/3/cars").then().statusCode(200)
				.body(matchesJsonSchemaInClasspath("person-cars-schema.json"));
	}
	
	@Test
	public void checkPersonJSONSchemaValues() {
		get("/persons/person/3/cars").then().statusCode(200)
				.body(matchesJsonSchemaInClasspath("person-cars-schema.json")).
		body("firstName", equalTo("kacper")).
		body("id", equalTo("3")).
		body("yob", equalTo("2013"));
	}

}
