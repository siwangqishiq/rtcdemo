package xyz.panyi.rtcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@ServerEndpoint(value = "/signal")
public class WebSocketChannel {
    private static Logger logger = LoggerFactory.getLogger(WebSocketChannel.class);

    private Session mSession;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        logger.info("on open session : " + session);
        mSession = session;

        sendMessage("hello from server");
        WebSocketManager.getInstance().addWebSocketChannel(this);
    }

    public void sendMessage(final String msg){
        try {
            mSession.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        logger.info("on close");
        WebSocketManager.getInstance().removeWebSocketChannel(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("on onMessage : " + message);
        logger.info("on onMessage session : " + session);
        WebSocketManager.getInstance().broadcastMessage(this , message);
    }


    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("on Error : " + error +"  session :" + session);
    }
}
