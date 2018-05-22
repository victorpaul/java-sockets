package com.sukinsan.utils;

import com.sukinsan.sockets.SocketUtils;

public interface ActionUtil {

    void setMousePosition(int x, int y);

    void pressMouse(int keyCode);

    void releaseMouse(int keyCode);

    boolean setListener(SocketUtils server);
}
