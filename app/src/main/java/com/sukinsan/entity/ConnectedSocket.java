package com.sukinsan.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectedSocket {
    private Socket socket;
    private boolean isClient;
    private PrintWriter out;
    private BufferedReader in;

    public ConnectedSocket(Socket socket, boolean isClient) {
        this.socket = socket;
        this.isClient = isClient;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHost(){
        return getSocket().getInetAddress().getHostName();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }
}
