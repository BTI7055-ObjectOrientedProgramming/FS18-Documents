import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatDemo {
	public static void main(String[] args) {
		Date now = Calendar.getInstance().getTime();
		
		DateFormat format = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, new Locale("de"));
		System.out.println(format.format(now));
		
		format = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, new Locale("fr"));
		System.out.println(format.format(now));
		
		format = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, new Locale("en", "GB"));
		System.out.println(format.format(now));
		
		format = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, new Locale("en", "US"));
		System.out.println(format.format(now));
		
	}
}
