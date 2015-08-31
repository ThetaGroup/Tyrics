package org.theta.desktop.tyrics.config;

import java.awt.Color;

/**
 *
 * @author Ranger 2015年8月31日
 */
public class AppConfig {

    private String title           = "Test Title";

    private int    height          = 200;

    private int    width           = 200;

    private Color  backgroundColor = Color.WHITE;

    private int    locationX       = 300;

    private int    locationY       = 300;

    private String pollingUrl      = "http://thetawechat.sinaapp.com/polling.php";

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

}
