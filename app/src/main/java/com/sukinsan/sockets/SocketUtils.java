package com.sukinsan.sockets;

import com.sukinsan.entity.ConnectedSocket;

public interface SocketUtils {
    interface OnClientConnected {
        void onClientConnected(ConnectedSocket connectedSocket);
    }

    interface OnReadMessage {
        void onClientRead(SocketUtils su, ConnectedSocket connectedSocket, String message);
    }

    interface OnConnected {
        void onConnected(ConnectedSocket connectedSocket);
    }

    void joinServer(String host, int port, OnConnected onConnected);

    void startServer(int port, OnClientConnected onClientConnected);

    void sendMessage(ConnectedSocket connectedSocket, String message);

    void sendMessage(String message);

}