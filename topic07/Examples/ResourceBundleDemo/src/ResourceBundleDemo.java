import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleDemo {
	public static void main(String[] args) {
		printUITexts();
		
		Locale.setDefault(Locale.GERMAN);
		printUITexts();
		
		Locale.setDefault(new Locale("de", "AT"));
		printUITexts();
		
		Locale.setDefault(Locale.FRENCH);
		printUITexts();
	}

	private static void printUITexts() {
		Locale locale = Locale.getDefault();
		System.out.println(locale);
		ResourceBundle uiTexts = ResourceBundle.getBundle("UITexts");
		System.out.println(uiTexts.getString("prompt"));
		System.out.println(uiTexts.getString("hello"));
		System.out.println(uiTexts.getString("bye"));
		try {
			System.out.println(uiTexts.getString("hoppla"));
		} catch(MissingResourceException e) {
			System.out.println("!hoppla!");
		}
		System.out.println();
	}
}
