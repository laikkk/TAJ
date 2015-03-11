package code;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class LiczbaRzymska {
	private int liczba;
	private Map<Integer, String> Digits = new TreeMap<Integer, String>();

	public LiczbaRzymska(int liczba) {
		Digits.put(1000, "M");
		Digits.put(900, "CM");
		Digits.put(500, "D");
		Digits.put(400, "CD");
		Digits.put(100, "C");
		Digits.put(90, "XC");
		Digits.put(50, "L");
		Digits.put(40, "XL");
		Digits.put(10, "X");
		Digits.put(9, "IX");
		Digits.put(5, "V");
		Digits.put(4, "IV");
		Digits.put(1, "I");
		this.liczba = liczba;
	}

	@Override
	public String toString() {
		String roman = "";
		if ((liczba < 1) || (liczba > 3999)) {
			throw new ArithmeticException();
		}

		ArrayList<Integer> keys = new ArrayList<Integer>(Digits.keySet());
		for (int i = keys.size() - 1; i >= 0; i--) {
			if (liczba >= keys.get(i)) {
				roman += Digits.get(keys.get(i));
				liczba -= keys.get(i);
			}
		}

		return roman;
	}
}
