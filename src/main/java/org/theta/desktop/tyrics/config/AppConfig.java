package org.theta.desktop.tyrics.config;

import java.awt.Color;

/**
 *
 * @author Ranger 2015年8月31日
 */
public class AppConfig {

	private String title = "Tyric";

	private int height = 200;

	private int width = 200;

	private Color backgroundColor = Color.WHITE;

	private int locationX = 300;

	private int locationY = 300;

	private String pollingUrl = "http://thetawechat.sinaapp.com/polling.php";

	private int rows = 10;

	private int cols = 1;

	private String fontName = "黑体";

	private int fontStyle = 1;

	private int fontSize = 45;

	public String getFontName() {
		return fontName;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public int getFontSize() {
		return fontSize;
	}

	public String getPollingUrl() {
		return pollingUrl;
	}

	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public String getTitle() {
		return title;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

}
