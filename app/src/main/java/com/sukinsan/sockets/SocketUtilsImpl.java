package com.sukinsan.sockets;

import com.sukinsan.entity.ConnectedSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketUtilsImpl implements SocketUtils {

    private boolean working = true;
    private List<ConnectedSocket> sockets = new ArrayList<>();
    private OnReadMessage onClientRead;

    public SocketUtilsImpl(OnReadMessage onClientRead) {
        this.onClientRead = onClientRead;
        startListener();
    }

    @Override
    public void startServer(int port, OnClientConnected onClientConnected) {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while (working) {
                    System.out.println("Open new connection");
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Got new client " + clientSocket.getInetAddress().toString());
                    ConnectedSocket connectedSocket = new ConnectedSocket(clientSocket, true);
                    sockets.add(connectedSocket);
                    onClientConnected.onClientConnected(connectedSocket);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void joinServer(String host, int port, OnConnected onConnected) {
        new Thread(() -> {
            working = true;
            try {
                Socket echoSocket = new Socket(host, port);
                ConnectedSocket connectedSocket = new ConnectedSocket(echoSocket, false);
                sockets.add(connectedSocket);
                onConnected.onConnected(connectedSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void startListener() {
        new Thread(() -> {
            try {
                while (working) {
                    for (ConnectedSocket connectedSocket : sockets) {
                        String inputLine = connectedSocket.getIn().readLine();
                        if (inputLine != null) {
                            onClientRead.onClientRead(SocketUtilsImpl.this, connectedSocket, inputLine);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void sendMessage(ConnectedSocket connectedSocket, String message) {
        connectedSocket.getOut().println(message);
    }

    @Override
    public void sendMessage(String message) {
        for (ConnectedSocket cs : sockets) {
            sendMessage(cs, message);
        }
    }
}
