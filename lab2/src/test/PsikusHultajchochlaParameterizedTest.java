package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import code.IPsikus;
import code.NieduanyPsikusException;
import code.Psikus;
@RunWith(Parameterized.class)
public class PsikusHultajchochlaParameterizedTest {
	IPsikus psikus = new Psikus();
	
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { 11, 11 }, { 25, 52 }, { 79, 97 }, { 31, 13 }  
           });
    }

       private Integer fInput;
    
        private Integer fExpected;
    
        public PsikusHultajchochlaParameterizedTest(Integer input, Integer expected) {
            fInput= input;
            fExpected= expected;
        }
    
        @Test
        public void test() throws NieduanyPsikusException {
            assertEquals(fExpected.intValue(), psikus.hultajchochla(fInput).intValue());
        }
}
