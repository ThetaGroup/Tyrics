package org.theta.desktop.tyrics;

import org.theta.desktop.tyrics.bus.impl.DefaultPollProvider;
import org.theta.desktop.tyrics.bus.intf.PollConsumer;
import org.theta.desktop.tyrics.bus.intf.PollProvider;
import org.theta.desktop.tyrics.config.AppConfig;
import org.theta.desktop.tyrics.view.frame.MainFrame;

/**
 * 
 * @author Ranger 2015年8月31日
 */
public class App {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        PollConsumer consumer = MainFrame.getInstance(appConfig);
        PollProvider provider = DefaultPollProvider.getInstance(consumer, appConfig);
        provider.start();
    }
}
