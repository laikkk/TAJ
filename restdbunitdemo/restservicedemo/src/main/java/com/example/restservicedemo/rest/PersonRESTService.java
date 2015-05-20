package com.example.restservicedemo.rest;

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
import com.example.restservicedemo.service.PersonsCarsManager;
import com.exmaple.testservice.wrappers.PersonCar;

@Path("persons")
public class PersonRESTService {	
	
	private PersonsCarsManager pm = new PersonsCarsManager();
	
	@GET
	@Path("/{personId}")
	@Produces("application/json")
	public Person getPerson(@PathParam("personId") Long id){
		Person p = pm.getPerson(id);
		return p;
	}
	
	@GET
	@Path("/withcars/{personId}")
	@Produces("application/json")
	public Person getPersonWithCars(@PathParam("personId") Long id){
		Person p = pm.getPersonWithCar(id);
		return p;
	}
	
	@GET
	@Path("/addcar/{carId}/toperson/{personId}")
	@Produces("application/json")
	public Response addCarToPersonByIds(@PathParam("carId") Long carId, @PathParam("personId") Long personId){
		pm.addCarToPersonByIds(carId, personId);
		Person person =pm.getPersonWithCar(personId);
		return Response.status(201).entity(person).build(); 
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(Person person){
		System.out.println("Person" + person.getFirstName());
		pm.addPerson(person);
		return Response.status(201).entity(person).build(); 
	}
	
	@POST
	@Path("/addcartoperson/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCarToPerson(PersonCar personCar){
		Person person = personCar.getPerson();
		Car car = personCar.getCar();
		
		pm.addCarToPerson(person, car);
		person =pm.getPersonWithCar(person.getId());
		return Response.status(201).entity(person).build(); 
	}
	
	@GET
	@Path("/test")
	@Produces("text/html")
	public String test(){
		return "REST Service is running";
	}

}
