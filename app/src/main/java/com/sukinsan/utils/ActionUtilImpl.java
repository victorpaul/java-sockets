package com.sukinsan.utils;

import com.sukinsan.sockets.SocketUtils;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;

public class ActionUtilImpl implements ActionUtil {

    @Override
    public boolean setListener(SocketUtils su) {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
                @Override
                public void nativeKeyTyped(NativeKeyEvent e) {
                    System.out.println("Key code: " + e.getKeyCode());
                    System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
                    su.sendMessage("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
                }

                @Override
                public void nativeKeyPressed(NativeKeyEvent e) {
                    System.out.println("Key code: " + e.getKeyCode());
                    System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
                    su.sendMessage("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
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
                    System.out.println("Key code: " + e.getKeyCode());
                    System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
                    su.sendMessage("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
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
    public void setMousePosition(int x, int y) {
        try {
            Robot robot = new Robot();

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pressMouse(int keyCode) {

    }

    @Override
    public void releaseMouse(int keyCode) {

    }
}
