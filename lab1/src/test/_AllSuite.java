package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  CalculatorTest.class,
  CalculatorDoubleTest.class,
  LiczbaRzymskaTest.class,
  LiczbaRzymskaParameterizedTest.class
})

public class _AllSuite {
}
