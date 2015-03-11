package test;

import static org.junit.Assert.*;

import org.junit.Test;

import code.LiczbaRzymska;
import static org.hamcrest.CoreMatchers.*;

public class LiczbaRzymskaTest {

	LiczbaRzymska liczbaRzymska;

	@Test(expected = ArithmeticException.class)
	public void toString_WhenInitWith0_ThrowArithmeticException() {
		liczbaRzymska = new LiczbaRzymska(0);
		liczbaRzymska.toString();
	}

	@Test(expected = ArithmeticException.class)
	public void toString_WhenInitWithMinus1_ThrowArithmeticException() {
		liczbaRzymska = new LiczbaRzymska(-1);
		liczbaRzymska.toString();
	}

	@Test
	public void toString_WhenInitWith1_ReturnI() {
		liczbaRzymska = new LiczbaRzymska(1);
		String actual = liczbaRzymska.toString();
		assertThat(actual, containsString("I"));
	}

	@Test
	public void toString_WhenInitWith5_ReturnV() {
		liczbaRzymska = new LiczbaRzymska(5);
		String actual = liczbaRzymska.toString();
		assertThat(actual, containsString("V"));
	}

	@Test
	public void toString_WhenInitWith10__ReturnX() {
		liczbaRzymska = new LiczbaRzymska(10);
		String actual = liczbaRzymska.toString();
		assertThat(actual, startsWith("X"));
	}

	@Test(timeout = 1000)
	public void toString_WhenInitWith11_ReturnXI() {
		liczbaRzymska = new LiczbaRzymska(11);
		String actual = liczbaRzymska.toString();
		assertThat(actual, either(containsString("X")).or(containsString("I")));
	}

	@Test
	public void toString_WhenInitWith100_ReturnC() {
		liczbaRzymska = new LiczbaRzymska(100);
		String actual = liczbaRzymska.toString();
		assertThat(actual, containsString("C"));
	}

}
