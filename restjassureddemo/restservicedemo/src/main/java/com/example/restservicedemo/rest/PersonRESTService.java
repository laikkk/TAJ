package com.example.restservicedemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

@Path("persons")
public class PersonRESTService {

	private PersonManager pm = new PersonManager();

	@GET
	@Path("/{personId}")
	@Produces("application/json")
	public Person getPerson(@PathParam("personId") Long id) {
		Person p = pm.getPerson(id);
		return p;
	}

	@GET
	@Path("/test")
	@Produces("text/html")
	public String test() {
		return "REST Service is running";
	}

	@GET
	@Path("/")
	@Produces("application/json")
	public List<Person> getPersons() {
		return pm.getAllPersons();
	}
	
	@GET
	@Path("/person/{persionId}/cars")
	@Produces("application/json")
	public Person getPersonWithCar(@PathParam("personId") Long id) {
		List<Car> cars = new ArrayList<Car>();
		cars.add(new Car(1, "Opel", "Astra", 2013));
		cars.add(new Car(2, "Porshe", "911", 2016));
		Person pa = new Person(3, "kacper", 2013);
		pa.setCars(cars);
		pm.addPerson(pa);
		return pa;
	}

	@GET
	@Path("/person/{persionId}/cars/xml")
	@Produces("application/xml")
	public Person getPersonWithCarAsXML(@PathParam("personId") Long id) {
		List<Car> cars = new ArrayList<Car>();
		cars.add(new Car(1, "test1", "test2", 2003));
		cars.add(new Car(2, "test1", "test2", 2003));
		Person pa = new Person(3, "kacper", 2013);
		pa.setCars(cars);
		pm.addPerson(pa);
		return pa;
	}

	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Person person) {
		if (person.getYob() > 0)
			if (pm.addPerson(person) == 1) {
				String result = "Persons saved: " + person;
				return Response.status(201).entity(result).build();
			}
		return Response.status(400)
				.entity("There was an error during creating new Person")
				.build();
	}

}
