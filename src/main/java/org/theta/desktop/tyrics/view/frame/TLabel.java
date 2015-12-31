package org.theta.desktop.tyrics.view.frame;

import javax.swing.JLabel;

/**
 *
 * @author Ranger
 * @email ranger.ying@gmail.com
 * @date 2015年12月31日
 */
public class TLabel extends JLabel {

	private static final long serialVersionUID = 3625126128231441629L;

	private static final int MAX_PROGRESS = 400;

	private boolean active = true;

	private int width = -1;

	private int progress = 0;

	private int oldX = Integer.MAX_VALUE;
	private int oldY = Integer.MAX_VALUE;

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
		this.setText(message);
		this.setActive(true);
	}

	public boolean pushProgress() {
		boolean finished = progress == MAX_PROGRESS;
		if (width == -1) {
			width = this.getWidth();
			oldX = this.getX();
			oldY = this.getY();
		}
		if (finished) {
			progress = 0;
			active = false;
			this.setText("");
			this.setLocation(oldX, oldY);
		} else {
			if (progress == 0) {
				this.setLocation(this.getX()+width, this.getY());
			}

			progress++;
			int move = (int) ((float) width * (1 / (float) MAX_PROGRESS));
			this.setLocation(this.getX() - move, this.getY());
		}
		return finished;
	}
}
