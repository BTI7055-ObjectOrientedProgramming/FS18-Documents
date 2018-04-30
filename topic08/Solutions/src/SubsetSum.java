package topic08;

public class SubsetSum {

	public static boolean hasSubset(int[] integers, int target) {
		return recHasSubset(0, integers, target);
	}

	public static boolean recHasSubset(int start, int[] integers, int target) {
		if (target == 0) {
			return true;
		}
		if (start == integers.length) {
			return false;
		}
		return  recHasSubset(start + 1, integers, target) || 
				recHasSubset(start + 1, integers, target - integers[start]);
	}

	public static void main(String[] args) {
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 0));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 2));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 4));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 7));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 6));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 9));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 11));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 13));
		
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 1));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 3));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 5));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 8));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 10));
		System.out.println(hasSubset(new int[] { 2, 4, 7 }, 12));
		System.out.println(hasSubset(new int[] { -2, 4, -7 }, -9));

	}

}
