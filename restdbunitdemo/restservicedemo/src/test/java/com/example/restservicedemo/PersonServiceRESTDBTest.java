package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.exmaple.testservice.wrappers.PersonCar;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;

public class PersonServiceRESTDBTest {
	private String BASE_URI = "http://localhost";
	private int PORT = 8080;
	private String BASE_PATH = "/restservicedemo";

	private String connectionString = "jdbc:hsqldb:hsql://localhost/workdb";
	private String dbUser = "sa";
	private String dbPass = "";
	private static IDatabaseConnection connection;
	private static IDatabaseTester databaseTester;

	private String pathToDBPreparationXMLFile = "src/test/resources/PrepareDB.xml";
	private String pathToExpectedPersonDBXMLFile = "src/test/resources/expectedPersonsDB.xml";
	private String pathToExpectedCarDBXMLFile = "src/test/resources/expectedCarsDB.xml";
	private String pathToExpectedPersonsCarsDBXMLFile = "src/test/resources/expectedPersons_CarsDb.xml";

	private IDataSet dataSet;
	private IDataSet dbDataSet;
	private IDataSet expectedDataSet;

	private ITable actualTable;
	private ITable filteredTable;
	private ITable expectedTable;

	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = BASE_URI;
		RestAssured.port = PORT;
		RestAssured.basePath = BASE_PATH;

		Connection jdbcConnection;
		jdbcConnection = DriverManager.getConnection(connectionString, dbUser,
				dbPass);
		connection = new DatabaseConnection(jdbcConnection);

		databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver",
				connectionString, dbUser, dbPass);
		dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(
				new File(pathToDBPreparationXMLFile)));
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	@Test
	public void addPeson() throws Exception {
		Person aPerson = new Person("Ziutek", 2010);

		given().
			contentType("application/json; charset=UTF-16").
			body(aPerson).
		when().
			post("/persons/").
		then().
			assertThat().
			statusCode(201);

		dbDataSet = connection.createDataSet();
		actualTable = dbDataSet.getTable("PERSON");
		filteredTable = DefaultColumnFilter.excludedColumnsTable(actualTable,
				new String[] { "ID" });

		expectedDataSet = new FlatXmlDataSetBuilder().build(new File(
				pathToExpectedPersonDBXMLFile));
		expectedTable = expectedDataSet.getTable("PERSON");

		Assertion.assertEquals(expectedTable, filteredTable);
	}

	@Test
	public void addCar() throws Exception {
		Car aCar = new Car("Opel", "Corsa", 2005);

		given().
			contentType("application/json; charset=UTF-16").
			body(aCar).
		when().
			post("/cars/").
		then().
			assertThat().
			statusCode(201);

		dbDataSet = connection.createDataSet();
		actualTable = dbDataSet.getTable("CAR");
		filteredTable = DefaultColumnFilter.excludedColumnsTable(actualTable,
				new String[] { "ID" });

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(
				pathToExpectedCarDBXMLFile));
		ITable expectedTable = expectedDataSet.getTable("CAR");

		Assertion.assertEquals(expectedTable, filteredTable);
	}

	@Test
	public void addCarToPersonByIds() throws Exception {
		Car aCar = get("/cars/2").as(Car.class);
		Person aPerson = get("/persons/2").as(Person.class);

		given().
			contentType("application/json; charset=UTF-16").
		when().
			get("persons/addcar/" + aCar.getId() + "/toperson/"	+ aPerson.getId()).
		then().
			assertThat().
			statusCode(201);

		dbDataSet = connection.createDataSet();
		actualTable = dbDataSet.getTable("PERSONS_CARS");
		filteredTable = DefaultColumnFilter.excludedColumnsTable(
				actualTable, new String[] { "ID" });

		expectedDataSet = new FlatXmlDataSetBuilder().build(new File(
				pathToExpectedPersonsCarsDBXMLFile));
		expectedTable = expectedDataSet.getTable("PERSONS_CARS");

		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	public void addCarToPersonByObj(){
		Car aCar = get("/cars/2").as(Car.class);
		Person aPerson = get("/persons/2").as(Person.class);
		PersonCar personCar = new PersonCar(aPerson,aCar);

		Gson gson = new Gson();
		String jsonPersonCar= gson.toJson(personCar);
		
		given().
			contentType("application/json; charset=UTF-16").
			accept("application/json").
			body(jsonPersonCar).
		when().
			post("/persons/addcartoperson/").
		then().
			assertThat().
			statusCode(201).
			header("Content-Type", "application/json");
	}

	@After
	public void tearDown() throws Exception {
		databaseTester.onTearDown();
	}

}
