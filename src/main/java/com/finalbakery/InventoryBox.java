package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import javax.swing.ImageIcon;


public class InventoryBox {
    private JPanel panel;
    private LinkedList<InventoryItem> items;  // LinkedList to hold items

    public InventoryBox() {
        this.items = new LinkedList<>();  // Initialize LinkedList
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the inventory box background
                g.drawImage(new ImageIcon("data/box.png").getImage(), 0, 0, getWidth(), getHeight(), this);

                // Display the items from the LinkedList
                for (int i = 0; i < items.size(); i++) {
                    // Example of drawing the item image in a grid-like layout
                    InventoryItem item = items.get(i);
                    g.drawImage(item.getImage().getImage(), 10 + i * 50, 10, 40, 40, this);  // Adjust positioning
                }
            }
        };
        panel.setLayout(null);  // Allow free placement of items
        panel.setBounds(2840, 1860, 200, 200);  // Positioning it at the bottom-right corner
    }

    public JPanel getPanel() {
        return panel;
    }

    // Method to add an item to the inventory
    public void addItem(InventoryItem item) {
        items.add(item);
        panel.repaint();  // Redraw the inventory box when an item is added
    }

    // Method to remove an item from the inventory
    public void removeItem(InventoryItem item) {
        items.remove(item);
        panel.repaint();  // Redraw the inventory box when an item is removed
    }
}
