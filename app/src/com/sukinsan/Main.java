package com.sukinsan;

import com.sukinsan.sockets.SocketUtils;
import com.sukinsan.sockets.SocketUtilsImpl;

public class Main {

    public static void main(String[] args) {
        SocketUtils server = new SocketUtilsImpl((su, cs, message) -> {
            System.out.println("Got message " + message + " from " + cs.getHost());
            if (message.equals("ping")) {
                su.sendMessage("pong from server");
            }
        });

        server.startServer(4444, connectedSocket -> {
            server.sendMessage(connectedSocket, "hello client, happy to see you here");
            server.sendMessage("ping");
        });
    }
}