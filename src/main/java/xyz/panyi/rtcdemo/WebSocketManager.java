package xyz.panyi.rtcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketManager {
    private static Logger logger = LoggerFactory.getLogger(WebSocketManager.class);

    private WebSocketManager(){
    }

    private static WebSocketManager instance = new WebSocketManager();

    public static WebSocketManager getInstance(){
        return instance;
    }

    public void test(){
        logger.info("hello " + this.hashCode());
    }
}
