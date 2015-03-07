package code;

public class Calculator implements ICalculator {

	@Override
	public int add(int a, int b) {		
		return a+b;
	}

	@Override
	public int sub(int a, int b) {
		return a-b;
	}

	@Override
	public int multi(int a, int b) {
		return a*b;
	}

	@Override
	public int div(int a, int b) {
		return a/b;
	}

	@Override
	public boolean greater(int a, int b) {
		return a>b;
	}

}
