package com.exmaple.jbehave.gra;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class GraSteps {

	private IPsikus psikus;
	private Integer liczba;

	@Given("a Psikus")
	public void messangerSetup() {
		psikus = new Psikus();
	}

	@When("set liczba to $liczba")
	public void setServer(Integer liczba) {
		this.liczba = liczba;
	}

	@Then("hultajchochla should throw NieduanyPsikusException")
	public void hultajchochlaShouldThrowNieduanyPsikusException() {
		try {
			psikus.hultajchochla(liczba);
			Assert.fail();
		} catch (NieduanyPsikusException e) {
			Assert.assertTrue(true);
		}
	}

	@Then("hultajchochla should return either $first $second $third")
	public void hultajchochlaShouldReturnEither(String first, String second,
			String third) throws NieduanyPsikusException {
		Integer result = psikus.hultajchochla(liczba);
		assertThat(result.toString(),
				either(is(first)).or(is(second)).or(is(third)));
	}

	@Then("hultajchochla should return $outcome")
	public void hultajchochlaShouldReturn(String outcome)
			throws NieduanyPsikusException {
		Integer result = psikus.hultajchochla(liczba);
		assertThat(result.toString(), is(outcome));
	}

	@Then("nieksztaltek should return $first")
	public void nieksztaltekShouldReturn(String first) {
		Integer result = psikus.nieksztaltek(liczba);
		assertThat(result.toString(), is(first));
	}

	@Then("nieksztaltek return either $first or $second")
	public void nieksztaltekShouldReturnEither(String first, String second) {
		Integer result = psikus.nieksztaltek(liczba);
		assertThat(result.toString(), either(is(first)).or(is(second)));
	}

	@Then("nieksztaltek return either $first or $second or $third")
	public void nieksztaltekShouldReturnEither(String first, String second,
			String third) {
		Integer result = psikus.nieksztaltek(liczba);
		assertThat(result.toString(),
				either(is(first)).or(is(second)).or(is(third)));
	}

	@Then("cyfrokrad should return NULL")
	public void cyfrokradShouldReturnNULL() throws NieduanyPsikusException {
		Integer result = psikus.cyfrokrad(liczba);
		assertNull(result);
	}

	@Then("cyfrokrad should return either $first $second $third")
	public void cyfrokradShouldReturnEither(String first, String second,
			String third) {
		Integer result = psikus.cyfrokrad(liczba);
		assertThat(
				result.toString(),
				either(containsString(first)).or(containsString(second)).or(
						containsString(third)));
	}

	@Then("cyfrokrad should return $first")
	public void cyfrokradShouldReturn(String first) {
		Integer result = psikus.cyfrokrad(liczba);
		assertEquals(first, result.toString());
	}

}
