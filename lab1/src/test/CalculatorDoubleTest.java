package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import code.CalculatorDouble;
import code.ICalculatorDouble;

public class CalculatorDoubleTest {

	ICalculatorDouble calculator;
	private static final double DELTA = 1e-15;

	@Before
	public void init() {
		calculator = new CalculatorDouble();
	}

	@Test
	public void add_When0and0_Return0() {
		double result = calculator.add(0.0, 0.0);
		assertEquals(0.0, result, DELTA);
	}

	@Test
	public void add_WhenGive5and3_Return8() {
		double result = calculator.add(5.0, 3.0);
		assertEquals(8, result, DELTA);
	}

	@Test
	public void sub_When0and0_Return0() {
		double result = calculator.sub(0.0, 0.0);
		assertEquals(0.0, result, DELTA);
	}

	@Test
	public void sub_WhenGive5and3_Return2() {
		double result = calculator.sub(5.0, 3.0);
		assertEquals(2.0, result, DELTA);
	}

	@Test
	public void sub_WhenGiveMinus5and3_ReturnMinus8() {
		double result = calculator.sub(-5.0, 3.0);
		assertEquals(-8.0, result, DELTA);
	}

	@Test
	public void multi_WhenGiveMinus5and3_ReturnMinus15() {
		double result = calculator.multi(-5, 3.0);
		assertEquals(-15.0, result, DELTA);
	}

	@Test
	public void multi_WhenGive3and3_Return9() {
		double result = calculator.multi(3.0, 3.0);
		assertEquals(9.0, result, DELTA);
	}

	@Test
	public void multi_WhenGive0and3_Return0() {
		double result = calculator.multi(0, 3.0);
		assertEquals(0.0, result, DELTA);
	}

	@Test
	public void div_WhenGive0and3_Return0() {
		double result = calculator.div(0, 3.0);
		assertEquals(0.0, result, DELTA);
	}

	@Test
	public void div_WhenGive9and3_Return3() {
		double result = calculator.div(9, 3.0);
		assertEquals(3.0, result, DELTA);
	}

	@Test
	public void div_WhenGive9and4_Return2Point25() {
		double result = calculator.div(9, 4);
		assertEquals(2.25, result, DELTA);
	}

	@Test
	public void div_WhenGive10and3_Return2() {
		double result = calculator.div(10, 3);
		assertEquals(3.33, result, 0.01);
	}

	public void div_WhenGive9and0_ThrownException() {
		calculator.div(9.0, 0);
	}

	@Test
	public void greater_WhenGive9and4_ReturnTrue() {
		boolean result = calculator.greater(9, 4);
		assertTrue(result);
	}

	@Test
	public void greater_WhenGive5and5_ReturnFalse() {
		boolean result = calculator.greater(5, 5);
		assertFalse(result);
	}

	@Test
	public void greater_WhenGive3and10_ReturnFalse() {
		boolean result = calculator.greater(3.0, 10);
		assertFalse(result);
	}
}
