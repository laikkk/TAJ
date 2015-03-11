package code;

public class Calculator implements ICalculator {

	public int add(int a, int b) {
		return a + b;
	}

	public int sub(int a, int b) {
		return a - b;
	}

	public int multi(int a, int b) {
		return a * b;
	}

	public int div(int a, int b) {
		return a / b;
	}

	public boolean greater(int a, int b) {
		return a > b;
	}

}
