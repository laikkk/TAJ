package test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.junit.Assert.*;

import org.junit.Test;

import code.IPsikus;
import code.Psikus;

public class PsikusCyfrokradTest {
	
	IPsikus psikus = new Psikus();

	@Test
	public void cyfrokrad_WhenGive0_ReturnNull(){
		Integer result = psikus.cyfrokrad(0);
		assertNull(result);
	}
	
	@Test
	public void cyfrokrad_WhenGiveMinus5_ReturnNull(){
		Integer result = psikus.cyfrokrad(-5);
		assertNull(result);
	}
	
	@Test
	public void cyfrokrad_WhenGiveMinus9_ReturnNull(){
		Integer result = psikus.cyfrokrad(-9);
		assertNull(result);
	}
	
	@Test
	public void cyfrokrad_WhenGive9_ReturnNull(){
		Integer result = psikus.cyfrokrad(9);
		assertNull(result);
	}
	
	@Test
	public void cyfrokrad_WhenGive100_Return10(){
		Integer result = psikus.cyfrokrad(100);
		assertEquals(10, result.intValue());
	}
	
	@Test
	public void cyfrokrad_WhenGive843_Return84or83or43(){
		Integer result = psikus.cyfrokrad(843);
		assertThat(result.toString(), either(containsString("84")).or(containsString("83")).or(containsString("43")));
	}
	
	@Test
	public void cyfrokrad_WhenGive4070_Return407or400or470(){
		Integer result = psikus.cyfrokrad(4070);
		assertThat(result.toString(), either(containsString("407")).or(containsString("400")).or(containsString("470")));
	}
	
	@Test
	public void cyfrokrad_WhenGive10_Return1or0() {
		Integer result = psikus.cyfrokrad(10);
		assertThat(result.toString(), either(containsString("1")).or(containsString("0")));
	}
	
	@Test
	public void cyfrokrad_WhenGive1000_return100() {
		Integer result = psikus.cyfrokrad(1000);
		assertEquals("100", result.toString());
	}

}
