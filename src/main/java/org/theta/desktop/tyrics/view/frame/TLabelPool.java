package org.theta.desktop.tyrics.view.frame;

import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.theta.desktop.tyrics.bus.intf.PollConsumer;
import org.theta.desktop.tyrics.config.AppConfig;

/**
 *
 * @author Ranger
 * @email ranger.ying@gmail.com
 * @date 2015年12月31日
 */
public class TLabelPool implements PollConsumer {

	private List<TLabel> labelList;

	private Queue<String> messageQueue;

	public TLabelPool(MainFrame mainFrame) {
		labelList = new ArrayList<TLabel>();
		for (int i = 0; i < AppConfig.getRows(); i++) {
			TLabel label = new TLabel();
			label.setText("初始化文档，就是要这么长才能让你看清楚，要不然有什么用呢？");
			label.setFont(new Font(AppConfig.getFontName(), AppConfig
					.getFontStyle(), AppConfig.getFontSize()));
			label.setBounds(0, 0, AppConfig.getWidth(), 100);
			label.setLabelName(String.valueOf(i));
			mainFrame.add(label);
			labelList.add(label);
		}
		mainFrame.pack();

		messageQueue = new LinkedList<String>();

		this.beginRockAndRoll();
	}

	public void receive(String message) {
		messageQueue.add(message);
	}

	private void beginRockAndRoll() {
		final Thread actThread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					List<Integer> actives = findActive();
					for (Integer labelNo : actives) {
						labelList.get(labelNo).pushProgress();
					}
				}
			}
		};
		actThread.start();

		final Thread rnrThread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					List<Integer> inactives = findInActive();
					if (inactives.size() > 0 && messageQueue.size() > 0) {
						Random random = new Random(System.currentTimeMillis());
						int pos = random.nextInt(inactives.size());
						TLabel label = labelList.get(inactives.get(pos));
						label.active(messageQueue.poll());
					}
				}
			}
		};
		rnrThread.start();

	}

	private List<Integer> findActive() {
		List<Integer> actives = new ArrayList<Integer>();
		for (Integer i = 0; i < labelList.size(); i++)
			if (this.labelList.get(i).isActive())
				actives.add(i);
		return actives;
	}

	private List<Integer> findInActive() {
		List<Integer> inactives = new ArrayList<Integer>();
		for (Integer i = 0; i < labelList.size(); i++)
			if (!this.labelList.get(i).isActive())
				inactives.add(i);
		return inactives;
	}

}
