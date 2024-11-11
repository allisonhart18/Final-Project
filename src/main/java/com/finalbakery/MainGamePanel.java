package com.finalbakery;

import javax.swing.*;
import java.awt.*;

public class MainGamePanel extends JPanel {

    public MainGamePanel() {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Display placeholder text for the main game screen
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("Main Game Screen", getWidth() / 2 - 100, getHeight() / 2);
    }
}
