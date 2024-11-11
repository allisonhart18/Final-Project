package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen implements Screen {
    private App app;
    private JPanel panel;
    private JButton startButton;
    private Image startScreenImage;

    public StartScreen(App app) {
        this.app = app;
        setup();
    }

    @Override
    public void setup() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(startScreenImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        startScreenImage = new ImageIcon("data/startScreenImage.png").getImage();

        startButton = new JButton(new ImageIcon("data/startButtonImage.png"));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.playMidiFiles();
                app.changeScreen(App.MAIN_GAME_SCREEN);
            }
        });

        panel.setLayout(new GridBagLayout());
        panel.add(startButton);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}
