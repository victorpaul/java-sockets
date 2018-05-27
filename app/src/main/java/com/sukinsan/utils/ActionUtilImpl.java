package com.sukinsan.utils;

import com.sukinsan.sockets.SocketUtils;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ActionUtilImpl implements ActionUtil {

    public final static String actionMessageSeparator = ":>";

    private Robot robot;

    public ActionUtilImpl() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean setListener(SocketUtils su) {
        try {

            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
                @Override
                public void nativeKeyTyped(NativeKeyEvent e) {
                    sendAction(su, ActionType.KEYBOARD_TYPED, e);
                }

                @Override
                public void nativeKeyPressed(NativeKeyEvent e) {
                    sendAction(su, ActionType.KEYBOARD_PRESSED, e);
                    if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
                        try {
                            GlobalScreen.unregisterNativeHook();
                        } catch (NativeHookException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

                @Override
                public void nativeKeyReleased(NativeKeyEvent e) {
                    sendAction(su, ActionType.KEYBOARD_RELEASED, e);
                }
            });
            return true;
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public void sendAction(SocketUtils su, ActionType type, NativeKeyEvent e) {
        StringBuilder sb = new StringBuilder()
                .append(type.toString()).append(actionMessageSeparator)
                .append(e.getKeyCode()).append(actionMessageSeparator)
                .append(e.getRawCode());
        su.sendMessage(sb.toString());
    }

    @Override
    public void receiveAction(String message) {
        if (message.contains(actionMessageSeparator)) {
            System.out.println("message " + message);
            String[] args = message.split(actionMessageSeparator);
            ActionType actionType = ActionType.valueOf(args[0]);
            int code = Integer.valueOf(args[1]);

            System.out.println("actionType is " + actionType.toString());
            System.out.println("keycode is " + code);

            switch (actionType) {
                case KEYBOARD_PRESSED:
                    //robot.keyPress(code);
                    break;
                case KEYBOARD_RELEASED:
                    //robot.keyRelease(code);
                    break;
                case KEYBOARD_TYPED:
                case MOUSE_PRESSED:
                case MOUSE_RELEASED:

                    break;
                default:
                    System.out.println("unknown actionType");
                    break;
            }
        }
    }
}
