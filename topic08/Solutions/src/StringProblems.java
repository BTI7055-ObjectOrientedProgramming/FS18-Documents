package topic08;

public class StringProblems {

	public static boolean isPrefix(String s1, String s2) {
		if (s2.length() == 0) {
			return true;
		}
		if (s1.length() == 0) {
			return false;
		}
		if (s1.charAt(0) != s2.charAt(0)) {
			return false;
		}
		return isPrefix(s1.substring(1), s2.substring(1));
	}

	public static boolean isSubString(String s1, String s2) {
		if (isPrefix(s1, s2)) {
			return true;
		}
		if (s1.length() == 0) {
			return false;
		}
		return isSubString(s1.substring(1), s2);
	}

	public static int count(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		if (isPrefix(s1, s2)) {
			return 1 + count(s1.substring(s2.length()), s2);
		} else {
			return count(s1.substring(1), s2);
		}
	}

	public static void main(String[] args) {
		// true
		System.out.println(isPrefix("abcdef", ""));
		System.out.println(isPrefix("abcdef", "a"));
		System.out.println(isPrefix("abcdef", "ab"));
		System.out.println(isPrefix("abcdef", "abc"));
		System.out.println(isPrefix("abcdef", "abcd"));
		System.out.println(isPrefix("abcdef", "abcde"));
		System.out.println(isPrefix("abcdef", "abcdef"));
		// false
		System.out.println(isPrefix("abcdef", "b"));
		System.out.println(isPrefix("abcdef", "g"));
		System.out.println(isPrefix("abcdef", "abcdefb"));
		System.out.println(isPrefix("abcdef", "abcdefg"));

		// true
		System.out.println(isSubString("abcdef", ""));
		System.out.println(isSubString("abcdef", "a"));
		System.out.println(isSubString("abcdef", "ab"));
		System.out.println(isSubString("abcdef", "abc"));
		System.out.println(isSubString("abcdef", "abcd"));
		System.out.println(isSubString("abcdef", "abcde"));
		System.out.println(isSubString("abcdef", "abcdef"));
		System.out.println(isSubString("abcdef", "b"));
		System.out.println(isSubString("abcdef", "bc"));
		System.out.println(isSubString("abcdef", "bcd"));
		System.out.println(isSubString("abcdef", "bcde"));
		System.out.println(isSubString("abcdef", "bcdef"));
		System.out.println(isSubString("abcdef", "c"));
		System.out.println(isSubString("abcdef", "cd"));
		System.out.println(isSubString("abcdef", "cde"));
		System.out.println(isSubString("abcdef", "cdef"));
		System.out.println(isSubString("abcdef", "d"));
		System.out.println(isSubString("abcdef", "de"));
		System.out.println(isSubString("abcdef", "def"));
		System.out.println(isSubString("abcdef", "e"));
		System.out.println(isSubString("abcdef", "ef"));
		System.out.println(isSubString("abcdef", "f"));

		// false
		System.out.println(isSubString("abcdef", "g"));
		System.out.println(isSubString("abcdef", "abcdefb"));
		System.out.println(isSubString("abcdef", "abcdefg"));

		// 0
		System.out.println(count("abcdefabc", "g"));
		System.out.println(count("abcdefabc", "gh"));

		// 1
		System.out.println(count("abcdefabc", "abcd"));
		System.out.println(count("abcdefabc", "bcd"));
		System.out.println(count("abcdefabc", "ef"));
		System.out.println(count("abcdefabc", "e"));

		// 2
		System.out.println(count("abcdefabc", "abc"));
		System.out.println(count("abcdefabc", "bc"));
		System.out.println(count("abcdefabc", "c"));
	}

}
