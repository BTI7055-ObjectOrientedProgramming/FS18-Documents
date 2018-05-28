package topic11;

import java.util.LinkedList;
import java.util.List;

public class LargeLinkedList {

	public static void main(String[] args) {

		List<Long> ll = new LinkedList<>();
		
		long max = ((long) Integer.MAX_VALUE) + 2;
		System.out.println(max);
		
		for (long i=0; i<= max; i++) {
			ll.add(0,i);
			if (i % 1000000 == 0) {
				System.out.println(i);
			}
		}
		System.out.println(ll.size());
		
	}

}
