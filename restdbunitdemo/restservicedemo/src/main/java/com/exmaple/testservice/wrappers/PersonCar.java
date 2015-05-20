package com.exmaple.testservice.wrappers;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;

@XmlRootElement
public class PersonCar {
	private Person person;
	private Car car;
	
	public PersonCar() {
	}
	
	public PersonCar(Person aPerson, Car aCar) {
		person = aPerson;
		car = aCar;
	}

	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
}
