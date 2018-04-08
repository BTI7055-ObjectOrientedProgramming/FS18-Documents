import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class FormatDemo {
	public static void main(String[] args) {
		Locale.setDefault(new Locale("de"));
		
		ResourceBundle messages = ResourceBundle.getBundle("Messages");
		String message = messages.getString("message");
		
		double[] limits = new double[] {0, 1, 2};
		String[] formats = new String[] {
				messages.getString("hasBeenPlur"),
				messages.getString("hasBeenSing"),
				messages.getString("hasBeenPlur")
		};
		ChoiceFormat hasBeenFormat = new ChoiceFormat(limits, formats);
		
		formats = new String[] {
				messages.getString("connectionPlur"),
				messages.getString("connectionSing"),
				messages.getString("connectionPlur")
		};
		ChoiceFormat connectionFormat = new ChoiceFormat(limits, formats);
		
		MessageFormat messageFormat = new MessageFormat(message);
		messageFormat.setFormatByArgumentIndex(2, hasBeenFormat);
		messageFormat.setFormatByArgumentIndex(4, connectionFormat);
		
		Object[] arguments = {
				messages.getString("zurich"),
				Calendar.getInstance().getTime(),
				1,
				1,
				1
		};
		
		StringBuffer formattedMessage = messageFormat.format(arguments, new StringBuffer(), null);
		System.out.println(formattedMessage);
	}
}
