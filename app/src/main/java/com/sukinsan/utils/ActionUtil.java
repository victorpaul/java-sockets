package com.sukinsan.utils;

import com.sukinsan.sockets.SocketUtils;
import org.jnativehook.keyboard.NativeKeyEvent;

public interface ActionUtil {
    enum ActionType {
        KEYBOARD_TYPED,
        KEYBOARD_PRESSED,
        KEYBOARD_RELEASED,
        MOUSE_PRESSED,
        MOUSE_RELEASED
    }

    boolean setListener(SocketUtils su);

    void sendAction(SocketUtils su,ActionUtilImpl.ActionType type, NativeKeyEvent e);

    void receiveAction(String message);
}
