package org.theta.desktop.tyrics.bus.intf;

/**
 *
 * @author Ranger 2015年8月31日
 */
public interface PollConsumer {

    /**
     * 
     * @param message
     */
    public void receive(String message);

}
