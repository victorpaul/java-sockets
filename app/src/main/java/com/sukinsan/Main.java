package com.sukinsan;

import com.sukinsan.sockets.SocketUtils;
import com.sukinsan.sockets.SocketUtilsImpl;
import com.sukinsan.utils.ActionUtil;
import com.sukinsan.utils.ActionUtilImpl;

public class Main {

    public static void main(String[] args) {
        ActionUtil actionUtil = new ActionUtilImpl();


        SocketUtils server = new SocketUtilsImpl((su, cs, message) -> {
            System.out.println("Got message " + message + " from " + cs.getHost());
            if (message.equals("ping")) {
                su.sendMessage("pong from server");
            }
        });

        actionUtil.setListener(server);

        server.startServer(4444, connectedSocket -> {
            server.sendMessage(connectedSocket, "hello client, happy to see you here");
            server.sendMessage("ping");
        });
    }
}