package topic08;

public class Hanoi {

	public static void hanoi(int n) {
		recHanoi(n, 1, 3);
	}

	public static void recHanoi(int n, int from, int to) {
		if (n == 1) {
			System.out.println(from + "=>" + to);
		} else {
			int other = 6-from-to;
			recHanoi(n-1, from, other);
			recHanoi(1, from, to);
			recHanoi(n-1, other, to);
		}
	}
	
	public static void main(String[] args) {
		hanoi(5);
	}

}
