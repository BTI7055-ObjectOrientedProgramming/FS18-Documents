import java.util.Arrays;
import java.util.Locale;

public class LocaleDemo {
	public static void main(String[] args) {
		Locale locale = Locale.getDefault();
		System.out.println(locale.getCountry());
		System.out.println(locale.getLanguage());
		System.out.println(locale.getVariant());

		// or lists of all known countries or languages
		String[] countries = locale.getISOCountries();
		String[] languages = locale.getISOLanguages();

		System.out.println(Arrays.toString(countries));
		System.out.println(Arrays.toString(languages));
	}
}
