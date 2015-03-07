package code;

public class CalculatorDouble implements ICalculatorDouble {

	@Override
	public double add(double a, double b) {
		return a+b;
	}

	@Override
	public double sub(double a, double b) {
		return a-b;
	}

	@Override
	public double multi(double a, double b) {
		return a*b;
	}

	@Override
	public double div(double a, double b) {
		return a/b;
	}

	@Override
	public boolean greater(double a, double b) {
		return a>b;
	}
}
