package test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import code.IPsikus;
import code.NieduanyPsikusException;
import code.Psikus;

public class PsikusTest {
	
	IPsikus psikus = new Psikus();
	
	//cyfrokrad
	
	//W pliku PsikusCyfrokradTest.java
	
	//hultajchochla
	
	@Test(expected= NieduanyPsikusException.class) 
	public void hultajchochla_WhenGive9_throwException() throws NieduanyPsikusException {
		psikus.hultajchochla(9);
	}
	
	@Test(expected= NieduanyPsikusException.class) 
	public void hultajchochla_WhenGiveMinus9_throwException() throws NieduanyPsikusException {
		psikus.hultajchochla(-9);
	}
	
	@Test
	public void hultajchochla_WhenGiveMinus10_Return1() throws NieduanyPsikusException {
		Integer result = psikus.hultajchochla(10);
		assertThat(result.toString(), is("1"));
	}
	
	@Test
	public void hultajchochla_WhenGiveMinus100_ReturnMinus100orMinus10orMinus1() throws NieduanyPsikusException {
		Integer result = psikus.hultajchochla(-100);
		assertThat(result.toString(), either(is("-10")).or(is("-1")).or(is("-100")));
	}
	
	@Test
	public void hultajchochla_WhenGiveMinus83_ReturnMinus38() throws NieduanyPsikusException {
		Integer result = psikus.hultajchochla(-83);
		assertThat(result.toString(), is("-38"));
	}
	
	@Test
	public void hultajchochla_WhenGive345_Return435or354or543() throws NieduanyPsikusException {
		Integer result = psikus.hultajchochla(345);
		assertThat(result.toString(), either(is("435")).or(is("354")).or(is("543")));
	}
	
	//nieksztaltek
	
	@Test
	public void nieksztaltek_WhenGive347_Return847or341() throws NieduanyPsikusException {
		Integer result = psikus.nieksztaltek(347);
		assertThat(result.toString(), either(is("847")).or(is("341")));
	}
	
	@Test
	public void nieksztaltek_WhenGive125_Return125() throws NieduanyPsikusException {
		Integer result = psikus.nieksztaltek(125);
		assertThat(result.toString(), is("125"));
	}

	@Test
	public void nieksztaltek_WhenGive333_Return833or383or338() throws NieduanyPsikusException {
		Integer result = psikus.nieksztaltek(333);
		assertThat(result.toString(), either(is("833")).or(is("383")).or(is("338")));
	}
	
	@Test
	public void nieksztaltek_WhenGive376_Return876or316or379() throws NieduanyPsikusException {
		Integer result = psikus.nieksztaltek(376);
		assertThat(result.toString(), either(is("876")).or(is("316")).or(is("379")));
	}
}
