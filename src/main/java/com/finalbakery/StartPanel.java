package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
    private App app;
    private JButton startButton;
    private Image startScreenImage;

    public StartPanel(App app) {
        this.app = app;

        // Load the start screen image (ensure the path is correct)
        startScreenImage = new ImageIcon("data/startScreenImage.png").getImage();

        // Set up the start button
        startButton = new JButton(new ImageIcon("data/startButtonImage.png"));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.startGame();  // Trigger the start of the game (animation)
            }
        });

        setLayout(new GridBagLayout());
        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image for the start screen
        g.drawImage(startScreenImage, 0, 0, getWidth(), getHeight(), this);
    }

    public JPanel getPanel() {
        return this;
    }
}
