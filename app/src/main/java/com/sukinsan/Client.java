package com.sukinsan;

import com.sukinsan.entity.ConnectedSocket;
import com.sukinsan.sockets.SocketUtils;
import com.sukinsan.sockets.SocketUtilsImpl;
import com.sukinsan.utils.ActionUtil;
import com.sukinsan.utils.ActionUtilImpl;

public class Client {
    public static void main(String[] args) {

        ActionUtil au = new ActionUtilImpl();

        SocketUtils client = new SocketUtilsImpl((su, cs, message) -> {
            au.receiveAction(message);
            if (message.equals("ping")) {
                su.sendMessage(cs, "pong from client");
            }
        });

        client.joinServer("127.0.0.1", 4444, new SocketUtils.OnConnected() {
            @Override
            public void onConnected(ConnectedSocket connectedSocket) {
                client.sendMessage(connectedSocket, "hello server");
                client.sendMessage(connectedSocket, "ping");
            }
        });
    }
}
