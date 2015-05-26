package com.example.restservicedemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;

public class PersonsCarsManager {

	private Connection connection;

	private static final String URL = "jdbc:hsqldb:hsql://localhost/workdb";
	private static final String CREATE_TABLE_PERSON = "CREATE TABLE Person(id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, name varchar(20), yob integer)";
	private static final String CREATE_TABLE_CAR = "CREATE TABLE Car(id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, make varchar(20), model varchar(20), yop integer)";
	private static final String CREATE_TABLE_PERSONS_CARS = "CREATE TABLE Persons_Cars(id bigint GENERATED BY DEFAULT AS IDENTITY, person_id int, car_id int, FOREIGN KEY (person_id) REFERENCES Person(Id), FOREIGN KEY (car_id) REFERENCES Car(Id))";

	private PreparedStatement addPersonStmt;
	private PreparedStatement deleteAllPersonsStmt;
	private PreparedStatement getAllPersonsStmt;
	private PreparedStatement getPersonByIdStmt;
	private PreparedStatement getCarByIdStmt;
	private PreparedStatement addCarStmt;
	private PreparedStatement addCarToPersonStmt;
	private PreparedStatement getIdsOfPersonsCars;
	private Statement statement;

	public PersonsCarsManager() {
		try {
			connection = DriverManager.getConnection(URL);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean persontableExists = false;
			boolean cartableExists = false;
			boolean personscarstableExists = false;
			while (rs.next()) {
				String tablename =rs.getString("TABLE_NAME");
				if ("Person".equalsIgnoreCase(tablename)) {
					persontableExists = true;
				}
				if ("Car".equalsIgnoreCase(tablename)) {
					cartableExists = true;
				}
				if ("Persons_Cars".equalsIgnoreCase(tablename)) {
					personscarstableExists = true;
				}
			}

			if (!persontableExists)
				statement.executeUpdate(CREATE_TABLE_PERSON);
			if (!cartableExists)
				statement.executeUpdate(CREATE_TABLE_CAR);
			if (!personscarstableExists)
				statement.executeUpdate(CREATE_TABLE_PERSONS_CARS);

			addPersonStmt = connection
					.prepareStatement("INSERT INTO Person (name, yob) VALUES (?, ?)");
			deleteAllPersonsStmt = connection
					.prepareStatement("DELETE FROM Person");
			getAllPersonsStmt = connection
					.prepareStatement("SELECT id, name, yob FROM Person");
			getPersonByIdStmt = connection
					.prepareStatement("SELECT id, name, yob FROM Person where id = ?");
			
			addCarStmt = connection
					.prepareStatement("INSERT INTO Car (make, model, yop) VALUES (?, ?, ?)");
			getCarByIdStmt = connection
					.prepareStatement("SELECT id, make, model, yop FROM Car where id = ?");
			
			addCarToPersonStmt = connection
					.prepareStatement("INSERT INTO Persons_Cars (person_id, car_id) VALUES (?, ?)");
			getIdsOfPersonsCars= connection
					.prepareStatement("SELECT car_id FROM Persons_Cars where person_id = ?");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}
	
	// PERSONS methods
	void clearPersons() {
		try {
			deleteAllPersonsStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addPerson(Person person) {
		int count = 0;
		try {
			addPersonStmt.setString(1, person.getFirstName());
			addPersonStmt.setInt(2, person.getYob());

			count = addPersonStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public Person getPerson(Long id) {
		Person p =null;
		try {
			getPersonByIdStmt.setLong(1, id);
			ResultSet rs = getPersonByIdStmt.executeQuery();

			while (rs.next()) {
				p =new Person();
				p.setId(rs.getInt("id"));
				p.setFirstName(rs.getString("name"));
				p.setYob(rs.getInt("yob"));
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<Person>();

		try {
			ResultSet rs = getAllPersonsStmt.executeQuery();

			while (rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
				p.setFirstName(rs.getString("name"));
				p.setYob(rs.getInt("yob"));
				persons.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	// CARS methods
	public Car getCar(Long id) {
		Car c =null;
		
		try {
			getCarByIdStmt.setLong(1, id);
			ResultSet rs = getCarByIdStmt.executeQuery();

			while (rs.next()) {
				c = new Car();
				c.setId(rs.getInt("id"));
				c.setMake(rs.getString("make"));
				c.setModel(rs.getString("model"));
				c.setYop(rs.getInt("yop"));
				break;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}

	public int addCar(Car car) {
		int count = 0;
		try {
			addCarStmt.setString(1, car.getMake());
			addCarStmt.setString(2, car.getModel());
			addCarStmt.setInt(3, car.getYop());
			count = addCarStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;	
	}
	
	// PERSONS_CARS methods
	public int addCarToPersonByIds(Long carId, Long personId) {	
		Car car = getCar(carId);
		Person person = getPerson(personId);
		
		if(car == null || person == null)
			throw new NullPointerException();
		
		int count = 0;
		try {
			addCarToPersonStmt.setLong(1, personId);
			addCarToPersonStmt.setLong(2, carId);

			count = addCarToPersonStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int addCarToPerson(Person person, Car car) {
		person = getPerson(person.getId());
		car = getCar(car.getId());
		
		int count = 0;
		try {
			addCarToPersonStmt.setLong(1, person.getId());
			addCarToPersonStmt.setLong(2, car.getId());

			count = addCarToPersonStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public Person getPersonWithCar(Long id) {
		Person p = new Person();
		try {
			// get person
			getPersonByIdStmt.setLong(1, id);
			ResultSet rs = getPersonByIdStmt.executeQuery();

			// set person object
			while (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setFirstName(rs.getString("name"));
				p.setYob(rs.getInt("yob"));
				break;
			}
			
			// find ids of person's cars
			getIdsOfPersonsCars.setLong(1, p.getId());
			rs = getIdsOfPersonsCars.executeQuery();
			List<Long> idsOfPersonsCars = new ArrayList<Long>();
			while (rs.next()) {
				idsOfPersonsCars.add(rs.getLong("car_id"));
			}
			
			// get cars			
			List<Car> personCars = new ArrayList<Car>();
			for (Long idCar : idsOfPersonsCars) {
				personCars.add(getCar(idCar));
			}
			
			// attach them to person
			p.setCars(personCars);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

}
