package org.theta.desktop.tyrics.view.frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.theta.desktop.tyrics.bus.intf.PollConsumer;
import org.theta.desktop.tyrics.config.AppConfig;

/**
 *
 * @author Ranger 2015年8月31日
 */
public class MainFrame extends JFrame implements MouseMotionListener,
		MouseListener, PollConsumer {

	private static final long serialVersionUID = -7558695675589011956L;

	private AppConfig appConfig;

	private static MainFrame mainFrame;

	private LayoutManager layout;

	private JLabel label;

	private Point firstPoint = null;

	/**
	 * 
	 * @param appConfig
	 */
	public static PollConsumer getInstance(AppConfig appConfig) {
		if (mainFrame == null) {
			mainFrame = new MainFrame(appConfig);
		}
		return mainFrame;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void receive(String message) {
		synchronized (MainFrame.class) {
			if (mainFrame != null) {
				mainFrame.label.setText(message);
			}
		}
	}

	/**
	 * 
	 * @param appConfig
	 */
	private MainFrame(AppConfig appConfig) {
		super();
		this.appConfig = appConfig;
		this.loadConfig();
		this.loadComponent();
		this.loadListener();
	}

	private void loadConfig() {
		this.setResizable(false);
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);

		if (appConfig != null) {
			this.setTitle(appConfig.getTitle());
			this.setSize(appConfig.getWidth(), appConfig.getHeight());
			this.setBackground(new Color(0, 0, 0, 0));
			this.setLocation(appConfig.getLocationX(), appConfig.getLocationY());
		}

		this.setVisible(true);
	}

	private void loadComponent() {
		/** Layout init */
		layout = new FlowLayout();
		this.setLayout(layout);

		/** Label init */
		label = new JLabel();
		label.setText("初始化文档，就是要这么长才能让你看清楚，要不然有什么用呢？");
		label.setFont(new Font("黑体", 1, 45));
		label.setBounds(0, 0, 100, 100);
		this.add(label);

		this.pack();
	}

	private void loadListener() {
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {
		this.firstPoint = e.getPoint();
	}

	public void mouseDragged(MouseEvent e) {
		Point location = this.getLocation();
		this.setLocation(location.x + (e.getX() - firstPoint.x), location.y
				+ (e.getY() - firstPoint.y));
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
