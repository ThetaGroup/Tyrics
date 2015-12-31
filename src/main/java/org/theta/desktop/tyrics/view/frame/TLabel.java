package org.theta.desktop.tyrics.view.frame;

import java.awt.Color;
import java.util.Random;

import javax.swing.JLabel;

import org.theta.desktop.tyrics.config.AppConfig;

/**
 *
 * @author Ranger
 * @email ranger.ying@gmail.com
 * @date 2015年12月31日
 */
public class TLabel extends JLabel {

	private static final long serialVersionUID = 3625126128231441629L;

	private boolean active = true;

	private int oldWidth = -1;

	private int progress = 0;

	private int maxProgress = 15;

	private int oldX = Integer.MAX_VALUE;

	private int oldY = Integer.MAX_VALUE;

	private String labelName = "";

	public TLabel() {
		super();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void active(String message) {
		this.setLocation(-2000, oldY);
		this.setText(message);
		this.setActive(true);
		this.maxProgress = message.length();
		Random random = new Random();
		int colorNo = random.nextInt(6);
		Color color = Color.BLACK;
		switch (colorNo) {
		case 0:
			color = Color.BLACK;
			break;
		case 1:
			color = Color.RED;
			break;
		case 2:
			color = Color.BLUE;
			break;
		case 3:
			color = Color.WHITE;
			break;
		case 4:
			color = Color.RED;
			break;
		case 5:
			color = Color.BLUE;
			break;
		}
		this.setForeground(color);
	}

	public boolean pushProgress() {
		boolean finished = progress > maxProgress * 10
				&& progress > AppConfig.getMinMaxProgress() * 10;
		if (oldWidth == -1) {
			oldWidth = this.getWidth();
			oldX = this.getX();
			oldY = this.getY();
		}
		if (finished) {
			progress = 0;
			active = false;
			this.setText("");
		} else {
			progress++;
			this.setLocation(oldX + oldWidth - AppConfig.getMove() * progress,
					this.getY());
		}
		return finished;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
}
