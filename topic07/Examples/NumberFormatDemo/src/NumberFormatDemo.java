import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;

public class NumberFormatDemo {
	public static void main(String[] args) throws ParseException {
		NumberFormat format = NumberFormat.getInstance(new Locale("fr"));
		System.out.println(format.format(12.50));
		
		format = NumberFormat.getCurrencyInstance();
		System.out.println(format.format(12.50));
		
		format = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
		System.out.println(format.format(12.50));
		
		format = NumberFormat.getPercentInstance();
		System.out.println(format.format(12.50));
		
		format = new DecimalFormat("#0.0000E00");
		System.out.println(format.format(12.50));
		System.out.println(format.format(1234.5678));
		
		Number number = format.parse("1234.5678", new ParsePosition(2));
		System.out.println(number);
		
		number = format.parse("1234567890123456789012345678901234567890");
		System.out.println(number);
	}
}
