package digitalClock;

import java.util.Observable;

/**
 * This class contains the current time (updated every second) and  represents the observable
 * @author lua1
 */
public class Time extends Observable implements Runnable {
	private long time;

	public Time() {
		this.time = System.currentTimeMillis();
		// Start clock
		Thread t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				this.increaseTime();
				System.out.println("x");
			} catch (InterruptedException e) {
			}
		}
	}

	private void increaseTime() {
		this.time = System.currentTimeMillis();

		// Important:
		// Call setChanged() before calling notifyObservers()
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * the methods represent the getData() method of the observer pattern
	 * @return current time
	 */
	public long getTime() {
		return this.time;
	}

}