package com.finalbakery;

import javax.swing.*;
import java.awt.*;

public class MainGameScreen implements Screen {
    private JPanel panel;

    public MainGameScreen() {
        setup();
    }

    @Override
    public void setup() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawString("Welcome to the Main Game Screen!", 50, 50);
            }
        };
        
        // Set layout to BorderLayout for positioning components like inventoryBox
        panel.setLayout(new BorderLayout());
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}
