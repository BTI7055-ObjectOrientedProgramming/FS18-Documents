import java.text.Collator;
import java.util.Arrays;

public class CollatorDemo {
	public static void main(String[] args) {
		String[] words = { "AA", "ÄA", "ÄÄ", "Aa", "Aä", "Äa", "Ää", "aa", "ab", "aÄ", "aä", "ää"};
		System.out.println("Original  " + Arrays.toString(words));
		
		Collator collator = Collator.getInstance();
		
		collator.setStrength(Collator.PRIMARY);
		Arrays.sort(words, collator);
		System.out.println("PRIMARY   " + Arrays.toString(words));
		
		collator.setStrength(Collator.SECONDARY);
		Arrays.sort(words, collator);
		System.out.println("SECONDARY " + Arrays.toString(words));
		
		collator.setStrength(Collator.TERTIARY);
		Arrays.sort(words, collator);
		System.out.println("TERTIARY  " + Arrays.toString(words));
		
		collator.setStrength(Collator.IDENTICAL);
		Arrays.sort(words, collator);
		System.out.println("IDENTICAL " + Arrays.toString(words));
	}
}
