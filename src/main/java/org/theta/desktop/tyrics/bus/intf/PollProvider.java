package org.theta.desktop.tyrics.bus.intf;

/**
 *
 * @author Ranger 2015年8月31日
 */
public interface PollProvider {

    /**
     * 
     * @param message
     */
    public void setConsumer(PollConsumer consumer);

    /**
     * 
     */
    public void start();

}
