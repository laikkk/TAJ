package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import code.LiczbaRzymska;

@RunWith(Parameterized.class)
public class LiczbaRzymskaParameterizedTest {
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 1, "I" }, { 1000, "M" },
				{ 150, "CL" }, { 11, "XI" }, { 9, "IX" }, { 555, "DLV" } });
	}

	LiczbaRzymska liczbaRzymska;
	private int fInput;

	private String fExpected;

	public LiczbaRzymskaParameterizedTest(int input, String expected) {
		fInput = input;
		fExpected = expected;
	}

	@Test
	public void paramTest() {
		liczbaRzymska = new LiczbaRzymska(fInput);
		String actual = liczbaRzymska.toString();
		assertEquals(fExpected, actual);
	}
}
