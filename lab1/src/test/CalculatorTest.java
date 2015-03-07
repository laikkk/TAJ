package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import code.Calculator;
import code.ICalculator;

public class CalculatorTest {
	
	ICalculator calculator;
	
	@Before
	public void init(){
		calculator = new Calculator();
	}

	@Test
	public void add_When0and0_Return0() {
		int result = calculator.add(0, 0);
		assertEquals(0,result);
	}
	
	@Test
	public void add_WhenGive5and3_Return8() {
		int result = calculator.add(5, 3);
		assertEquals(8,result);
	}
	
	@Test
	public void sub_When0and0_Return0() {
		int result = calculator.sub(0, 0);
		assertEquals(0,result);
	}
	
	@Test
	public void sub_WhenGive5and3_Return2() {
		int result = calculator.sub(5, 3);
		assertEquals(2,result);
	}
	
	@Test
	public void sub_WhenGiveMinus5and3_ReturnMinus8() {
		int result = calculator.sub(-5, 3);
		assertEquals(-8,result);
	}
	
	@Test
	public void multi_WhenGiveMinus5and3_ReturnMinus15() {
		int result = calculator.multi(-5, 3);
		assertEquals(-15,result);
	}
	
	@Test
	public void multi_WhenGive3and3_Return9() {
		int result = calculator.multi(3, 3);
		assertEquals(9,result);
	}
	
	@Test
	public void multi_WhenGive0and3_Return0() {
		int result = calculator.multi(0, 3);
		assertEquals(0,result);
	}
	
	@Test
	public void div_WhenGive0and3_Return0() {
		int result = calculator.div(0, 3);
		assertEquals(0,result);
	}
	
	@Test
	public void div_WhenGive9and3_Return3() {
		int result = calculator.div(9, 3);
		assertEquals(3,result);
	}
	
	@Test
	public void div_WhenGive9and4_Return2() {
		int result = calculator.div(9, 4);
		assertEquals(2,result);
	}
	
	@Test(expected= ArithmeticException.class) 
	public void div_WhenGive9and0_ThrownException() {
		calculator.div(9, 0);
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
		boolean result = calculator.greater(3, 10);
		assertFalse(result);
	}
}
