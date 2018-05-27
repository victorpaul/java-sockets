package com.sukinsan.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;

public class ServerSwing implements Runnable {

    JFrame mainFrame;
    JLabel label;

    public void run() {
        mainFrame = new JFrame("BasicApp");
        label = new JLabel("Hello, world!");
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(false);
                // Perform any other operations you might need
                // before exit.
                System.exit(0);
            }
        });


        mainFrame.setSize(200, 200);
        mainFrame.add(label);
        mainFrame.pack();
        mainFrame.setVisible(true);

        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_TYPED) {
                    System.out.println(e);
                }
                // Pass the KeyEvent to the next KeyEventDispatcher in the chain
                return false;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);


    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        Runnable app = new ServerSwing();
        SwingUtilities.invokeAndWait(app);
        Thread.sleep(10000);
        System.exit(-1);

    }
}