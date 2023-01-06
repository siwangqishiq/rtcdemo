package xyz.panyi.rtcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/signal")
@Component
public class WebSocketServer {
    public static final String TAG = "WebSocket";

    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        logger.info(TAG , "on open");
        try {
            session.getBasicRemote().sendText("hello from server");
        } catch (IOException e) {
            logger.error(TAG , e.toString());
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        logger.info(TAG , "on close");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info(TAG , "on onMessage : " + message);
    }


    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info(TAG , "on Error : " + error);
    }
}
