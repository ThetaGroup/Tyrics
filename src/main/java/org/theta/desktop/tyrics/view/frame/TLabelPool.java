package org.theta.desktop.tyrics.view.frame;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javax.swing.SwingConstants;

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

	public TLabelPool(AppConfig appConfig, MainFrame mainFrame) {
		labelList = new ArrayList<TLabel>();
		for (int i = 0; i < appConfig.getRows(); i++) {
			TLabel label = new TLabel();
			label.setText("初始化文档，就是要这么长才能让你看清楚，要不然有什么用呢？");
			// label.setText("初始化文档");
			label.setFont(new Font(appConfig.getFontName(), appConfig
					.getFontStyle(), appConfig.getFontSize()));
			label.setBounds(0, 0, appConfig.getWidth(), 100);
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
		Thread rnrThread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (findInActive().size() > 0 && messageQueue.size() > 0) {
						activeOne(messageQueue.poll());
					}
				}
			}
		};
		rnrThread.start();

		Thread actThread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (TLabel label : findActive()) {
						label.pushProgress();
					}
				}
			}
		};
		actThread.start();
	}

	private void activeOne(String message) {
		List<TLabel> inActives = this.findInActive();
		if (inActives.size() > 0) {
			Random random = new Random(System.currentTimeMillis());
			int pos = random.nextInt(inActives.size());
			TLabel label = inActives.get(pos);
			label.active(message);
		}
	}

	private List<TLabel> findActive() {
		List<TLabel> actives = new ArrayList<TLabel>();
		for (TLabel label : labelList)
			if (label.isActive())
				actives.add(label);
		return actives;
	}

	private List<TLabel> findInActive() {
		List<TLabel> actives = new ArrayList<TLabel>();
		for (TLabel label : labelList)
			if (!label.isActive())
				actives.add(label);
		return actives;
	}

}
