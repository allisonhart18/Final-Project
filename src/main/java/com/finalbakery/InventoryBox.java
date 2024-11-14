package com.finalbakery;

import javax.swing.*;
import java.awt.*;

public class InventoryBox {
    private JPanel panel;

    public InventoryBox() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the inventory box image
                g.drawImage(new ImageIcon("data/box.png").getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);  // Null layout to manage components manually
        panel.setBounds(2840, 1860, 200, 200);  // Positioning it at the bottom-right corner
    }

    public JPanel getPanel() {
        return panel;
    }
}
