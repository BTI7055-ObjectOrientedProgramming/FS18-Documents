package digitalClockpush;

import java.util.Observable;

public class Time extends Observable implements Runnable {
	private long time;

	public Time() {
		this.time = System.currentTimeMillis();
		// Start clock
		Thread t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				this.increaseTime();
			} catch (InterruptedException e) {
			}
		}
	}

	private void increaseTime() {
		this.time = System.currentTimeMillis();
				
		// Important:
		// Call setChanged() before calling notifyObservers()
		this.setChanged();
		this.notifyObservers(new TimeObject(this.time));
	}

}