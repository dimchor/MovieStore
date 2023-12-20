package com.mycompany.moviestore;

import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public class MovieStoreAboutWindow extends MovieStoreWindow {
    private ImageIcon icon;

    private JLabel appNameLabel;
    private JLabel devNameLabel;
    private JLabel devRegLabel;
    private JLabel timePeriodLabel;
    private JLabel iconLabel;

    private JPanel panel;

    public MovieStoreAboutWindow() {
        super("About", 360, 360);
        getFrame().setLayout(new FlowLayout());

        icon = new ImageIcon("icon.png", "Icon");

        appNameLabel = new JLabel("MovieStore", JLabel.CENTER);
        devNameLabel = new JLabel("Developer: [REDUCTED]", JLabel.CENTER);
        devRegLabel = new JLabel("Developer's Registration Number: [REDUCTED]", JLabel.CENTER);
        timePeriodLabel = new JLabel("Development period: 03/06/22 - 07/06/22", JLabel.CENTER);
        iconLabel = new JLabel(icon, JLabel.CENTER);

        panel = new JPanel();
        panel.add(iconLabel);
        panel.add(appNameLabel);
        panel.add(devNameLabel);
        panel.add(devRegLabel);
        panel.add(timePeriodLabel);

        getFrame().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        getFrame().setVisible(true);
    }

    protected void onExit() {
        getFrame().dispose();
    }
}
