package topic08;

public class PrintInteger {

	public static void printInteger(int n, int b) {
		if (n >= b) {
			printInteger(n / b, b);
		}
		System.out.print(Character.forDigit(n % b, b));
	}

	public static void main(String[] args) {
		printInteger(61, 2);
		System.out.println();
		
		printInteger(61, 16);
		System.out.println();
	}

}
