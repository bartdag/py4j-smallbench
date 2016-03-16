package org.py4j.smallbench;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class EventProducer implements Runnable {

	private SmallBenchApplication application;

	private BlockingQueue<Object> queue;

	public EventProducer(SmallBenchApplication application) {
		this.application = application;
		this.queue = application.getQueue();
	}

	public static void startProducer(SmallBenchApplication application) {

		EventProducer producer = new EventProducer(application);

		Thread t = new Thread(producer);
		t.start();

	}

	public void run() {
		while(true) {
			try {
				queue.add("Testing from " + Thread.currentThread().getName());
				application.notifyAllListeners();
				Thread.currentThread().sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
