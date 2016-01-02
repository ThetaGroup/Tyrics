package org.theta.desktop.tyrics.config;

import java.awt.Color;

/**
 *
 * @author Ranger 2015年8月31日
 */
public class AppConfig {

	private String title = "Tyric";

	private int height = 200;

	private int width = 1920;

	private Color backgroundColor = Color.WHITE;

	private int locationX = 0;

	private int locationY = 150;

	private String pollingUrl = "http://thetawechat.sinaapp.com/polling.php";

	private int rows = 8;

	private int cols = 1;

	private String fontName = "黑体";

	private int fontStyle = 1;

	private int fontSize = 45;

	private int minMaxProgress = 50;

	private int move = 5;

	private static final AppConfig instance = new AppConfig();

	private AppConfig() {

	}

	public static String getFontName() {
		return instance.fontName;
	}

	public static int getFontStyle() {
		return instance.fontStyle;
	}

	public static int getFontSize() {
		return instance.fontSize;
	}

	public static String getPollingUrl() {
		return instance.pollingUrl;
	}

	public static int getLocationX() {
		return instance.locationX;
	}

	public static int getLocationY() {
		return instance.locationY;
	}

	public static String getTitle() {
		return instance.title;
	}

	public static int getHeight() {
		return instance.height;
	}

	public static int getWidth() {
		return instance.width;
	}

	public static Color getBackgroundColor() {
		return instance.backgroundColor;
	}

	public static int getRows() {
		return instance.rows;
	}

	public static int getCols() {
		return instance.cols;
	}

	public static int getMinMaxProgress() {
		return instance.minMaxProgress;
	}

	public static int getMove() {
		return instance.move;
	}

}
