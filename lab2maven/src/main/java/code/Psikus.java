package code;

import java.util.Random;

public class Psikus implements IPsikus {
	private static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public Integer cyfrokrad(Integer liczba) {
		if (liczba < 10 || liczba < -10 || liczba == null) {
			return null;
		}

		// Check if liczba is negative if so, take nubmer other than first eg
		// -xxxxx
		String liczbaAsAString = liczba.toString();
		String stringDoPrzetworzenia;
		boolean minusowaLiczba = false;
		boolean acceptedResult = false;
		String result;
		StringBuilder nowystring;
		if (liczba < 0) {
			// pomin pierwszy znak w stringu
			stringDoPrzetworzenia = liczbaAsAString.substring(1,
					liczbaAsAString.length());
			minusowaLiczba = true;
		} else {
			stringDoPrzetworzenia = liczbaAsAString;
		}

		nowystring = new StringBuilder(stringDoPrzetworzenia);
		while (!acceptedResult) {
			int lenghtOfstring = stringDoPrzetworzenia.length();
			nowystring = new StringBuilder(stringDoPrzetworzenia);

			// wyolosuj miejsce do usuniecia cyfry
			int numerOfCharToDelete = randInt(0, lenghtOfstring - 1);

			nowystring.deleteCharAt(numerOfCharToDelete);
			Integer tmp = Integer.parseInt(nowystring.toString());
			if (lenghtOfstring == tmp.toString().length() + 1) {
				acceptedResult = true;
			}
		}

		result = nowystring.toString();
		if (minusowaLiczba) {
			result = "-" + nowystring;
		}

		return Integer.parseInt(result);
	}

	public Integer hultajchochla(Integer liczba) throws NieduanyPsikusException {
		if (liczba > -10 && liczba < 10 || liczba == null) {
			throw new NieduanyPsikusException();
		}

		String liczbaAsAString = liczba.toString();
		String stringDoPrzetworzenia;
		boolean minusowaLiczba = false;
		if (liczba < 0) {
			// pomin pierwszy znak w stringu
			stringDoPrzetworzenia = liczbaAsAString.substring(1,
					liczbaAsAString.length());
			minusowaLiczba = true;
		} else {
			stringDoPrzetworzenia = liczbaAsAString;
		}

		StringBuilder nowystring = new StringBuilder(stringDoPrzetworzenia);

		int positionOfFirstChar = randInt(0, nowystring.length() - 1);
		int positionOfSecondChar = randInt(0, nowystring.length() - 1);

		System.out.println("positionOfFirstChar " + positionOfFirstChar + ", positionOfSecondChar "+positionOfSecondChar );
		
		while (positionOfFirstChar == positionOfSecondChar) {
			positionOfSecondChar = randInt(0, nowystring.length() - 1);
		}

		char firstChar = nowystring.charAt(positionOfFirstChar);
		char secondChar = nowystring.charAt(positionOfSecondChar);

		nowystring.setCharAt(positionOfFirstChar, secondChar);
		nowystring.setCharAt(positionOfSecondChar, firstChar);

		String result = nowystring.toString();
		if (minusowaLiczba) {
			result = "-" + nowystring;
		}

		return Integer.parseInt(result);

	}

	public Integer nieksztaltek(Integer liczba) {
		String liczbaAsAString = liczba.toString();

		if (!(liczbaAsAString.contains("3") || liczbaAsAString.contains("7") || liczbaAsAString
				.contains("6"))) {
			return liczba;
		}

		String stringDoPrzetworzenia;
		boolean minusowaLiczba = false;
		if (liczba < 0) {
			// pomin pierwszy znak w stringu
			stringDoPrzetworzenia = liczbaAsAString.substring(1,
					liczbaAsAString.length());
			minusowaLiczba = true;
		} else {
			stringDoPrzetworzenia = liczbaAsAString;
		}

		// Losuj tak dlugo dowolny znak az to bedzie ktorys z pasujacych
		StringBuilder nowystring = new StringBuilder(stringDoPrzetworzenia);

		int positionOfChar = randInt(0, nowystring.length() - 1);
		char choosenChar = nowystring.charAt(positionOfChar);
		while (choosenChar != '3' && choosenChar != '7' && choosenChar != '6') {
			positionOfChar = randInt(0, nowystring.length() - 1);
			choosenChar = nowystring.charAt(positionOfChar);
		}

		switch (choosenChar) {
		case '3':
			nowystring.setCharAt(positionOfChar, '8');
			break;

		case '7':
			nowystring.setCharAt(positionOfChar, '1');
			break;
		case '6':
			nowystring.setCharAt(positionOfChar, '9');
			break;
		default:
			break;
		}
		
		String result = nowystring.toString();
		if (minusowaLiczba) {
			result = "-" + nowystring;
		}

		return Integer.parseInt(result);
	}

}
