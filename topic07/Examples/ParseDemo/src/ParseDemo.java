import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class ParseDemo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String input = scanner.nextLine();
			if (input.isEmpty()) break;
			try {
			DateFormat format = DateFormat.getDateInstance(DateFormat.FULL, new Locale("de"));
			format.setLenient(true);
			System.out.println(format.parse(input));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		scanner.close();
	}
}
