package org.theta.desktop.tyrics.bus.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.theta.desktop.tyrics.bus.intf.PollConsumer;
import org.theta.desktop.tyrics.bus.intf.PollProvider;
import org.theta.desktop.tyrics.config.AppConfig;

/**
 *
 * @author Ranger 2015年8月31日
 */
public class DefaultPollProvider implements PollProvider {

	private static DefaultPollProvider defaultProvider;

	private PollConsumer consumer;

	private Queue<String> queue = new ConcurrentLinkedQueue<String>();

	/**
	 * 
	 * @return
	 */
	public static PollProvider getInstance(PollConsumer consumer) {
		if (defaultProvider == null) {
			defaultProvider = new DefaultPollProvider();
			defaultProvider.setConsumer(consumer);
		}
		return defaultProvider;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void setConsumer(PollConsumer consumer) {
		this.consumer = consumer;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void start() {
		Thread pollThread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					String pollingData = pollData();
					String showMessage = handleQueue(pollingData);
					if (showMessage != null && !"".equals(showMessage))
						consumer.receive(showMessage);
				}
			}
		};
		pollThread.start();
	}

	/**
	 * 
	 * @param pollingData
	 * @return
	 */
	private String handleQueue(String pollingData) {
		String[] messages = pollingData.split("&&&");
		System.out.print("Splited messages: ");
		for (String message : messages) {
			System.out.print(message + ",");
		}
		System.out.println();
		for (String message : messages) {
			if (message != null && message.length() > 0) {
				this.queue.add(message);
			}
		}

		String showMessage = this.queue.poll();
		System.out.print("Current queue: ");
		for (String ele : this.queue) {
			System.out.print(ele + ",");
		}
		System.out.println();
		System.out.println("Next show message(" + showMessage + ")...");
		return showMessage == null ? "" : showMessage;
	}

	/**
	 * 
	 * @return
	 */
	private String pollData() {
		String data = "";
		try {
			URL url = new URL(AppConfig.getPollingUrl());
			URLConnection connection = url.openConnection();
			System.out.println("Connecting Url(" + AppConfig.getPollingUrl()
					+ ")...");
			connection.connect();
			System.out.println("Connect success,polling datas...");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				data += line;
			}
			System.out.println("Poll success,Data(" + data + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(data);
		return data;
	}

}
