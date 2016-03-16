package org.py4j.smallbench;

import py4j.GatewayServer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SmallBenchApplication {

	private BlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(1000);

	private List<BenchListener> listeners = new ArrayList<BenchListener>();

	public BlockingQueue<Object> getQueue() {
		return queue;
	}

	public void registerBenchListener(BenchListener listener) {
		listeners.add(listener);
	}

	public void notifyAllListeners() {
		try {
			Object object = queue.take();
			for (BenchListener listener : listeners) {
				listener.notify(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startProducers(int count) {
		for (int i = 0; i < count; i++) {
			EventProducer.startProducer(this);
		}
	}

	public static void main(String[] args) {
		SmallBenchApplication application = new SmallBenchApplication();

		GatewayServer server = new GatewayServer(application);
		server.start();
	}

}
