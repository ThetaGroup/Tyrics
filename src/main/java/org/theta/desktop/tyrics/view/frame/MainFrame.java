package org.theta.desktop.tyrics.view.frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import org.theta.desktop.tyrics.bus.intf.PollConsumer;
import org.theta.desktop.tyrics.config.AppConfig;

/**
 *
 * @author Ranger 2015年8月31日
 */
public class MainFrame extends JFrame implements MouseMotionListener,
		MouseListener, PollConsumer {

	private static final long serialVersionUID = -7558695675589011956L;

	private static MainFrame mainFrame;

	private LayoutManager layout;

	private TLabelPool labelPool;

	private Point firstPoint = null;

	/**
	 * 
	 * @param AppConfig
	 */
	public static PollConsumer getInstance() {
		if (mainFrame == null) {
			mainFrame = new MainFrame();
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
				labelPool.receive(message);
			}
		}
	}

	/**
	 * 
	 * @param AppConfig
	 */
	private MainFrame() {
		super();
		this.loadConfig();
		this.loadComponent();
		this.loadListener();
	}

	private void loadConfig() {
		this.setResizable(false);
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);

		this.setTitle(AppConfig.getTitle());
		this.setSize(AppConfig.getWidth(), AppConfig.getHeight());
		this.setBackground(new Color(0, 0, 0, 0));
		this.setLocation(AppConfig.getLocationX(), AppConfig.getLocationY());

		this.setVisible(true);
	}

	private void loadComponent() {
		/** Layout init */
		layout = new GridLayout(AppConfig.getRows(), AppConfig.getCols());
		this.setLayout(layout);

		/** Label init */
		this.labelPool = new TLabelPool(this);
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
