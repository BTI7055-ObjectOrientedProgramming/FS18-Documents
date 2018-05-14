package topic09.ex1;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BankAccountSimulator {

	public static void main(String[] args) {

		int n = 3;
		int m = 10;
		Random random = new Random();

		List<BankAccount> bankAccounts = Stream.generate(BankAccount::new).limit(n).collect(Collectors.toList());

		List<Thread> threads = Stream.generate(() -> 
			new Thread(() -> {
				while (!Thread.interrupted()) {
					int i1 = random.nextInt(n);
					int i2;
					do {
						i2 = random.nextInt(n);
					} while (i1 == i2);
					bankAccounts.get(i1).randomTransfer1(bankAccounts.get(i2));
//					bankAccounts.get(i1).randomTransfer2(bankAccounts.get(i2));
//					bankAccounts.get(i1).randomTransfer3(bankAccounts.get(i2));
//					bankAccounts.get(i1).randomTransfer4(bankAccounts.get(i2));
//					bankAccounts.get(i1).randomTransfer5(bankAccounts.get(i2));
				}
			})).limit(m).collect(Collectors.toList());
		
		System.out.println(bankAccounts.stream().mapToInt(BankAccount::getBalance).peek(System.out::println).sum());

		threads.forEach(Thread::start);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		threads.forEach(Thread::interrupt);

		while (threads.stream().anyMatch(Thread::isAlive)) { // wait until threads terminate
		}

		System.out.println(bankAccounts.stream().mapToInt(BankAccount::getBalance).peek(System.out::println).sum());
	}
}
