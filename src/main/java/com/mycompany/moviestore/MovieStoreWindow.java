package com.mycompany.moviestore;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public abstract class MovieStoreWindow {
    private JFrame frame;

    public MovieStoreWindow(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing (WindowEvent e) {
                onExit();
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }

    abstract protected void onExit();
}
