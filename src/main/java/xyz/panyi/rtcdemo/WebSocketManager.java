package xyz.panyi.rtcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class WebSocketManager {
    private static Logger logger = LoggerFactory.getLogger(WebSocketManager.class);

    private Set<WebSocketChannel> channelSet = new HashSet<WebSocketChannel>();

    private WebSocketManager(){
    }

    private static WebSocketManager instance = new WebSocketManager();

    public static WebSocketManager getInstance(){
        return instance;
    }

    public void broadcastMessage(WebSocketChannel sourceChannel ,final String content){
        channelSet.forEach(ch ->{
            if(sourceChannel  != ch){
                ch.sendMessage(content);
            }
        });
    }

    public void addWebSocketChannel(WebSocketChannel channel){
        channelSet.add(channel);
        logger.info("addWebSocketChannel current channel count : " + channelSet.size());
    }

    public void removeWebSocketChannel(WebSocketChannel channel){
        channelSet.remove(channel);
        logger.info("removeWebSocketChannel current channel count : " + channelSet.size());
    }
}
