package com.sukinsan.utils;

import java.awt.*;

public class ActionUtilImpl implements ActionUtil {

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
