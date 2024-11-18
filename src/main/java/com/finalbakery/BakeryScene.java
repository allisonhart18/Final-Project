package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BakeryScene {
    private JPanel bakeryPanel;
    private SceneController sceneController;
    private String currentBackground = "data/bakeryscene.png"; // Default background image path

    public BakeryScene(SceneController sceneController) {
        this.sceneController = sceneController;
        setup();
    }

    private void setup() {
        // Create the bakeryPanel with a null layout for absolute positioning
        bakeryPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the current background image
                g.drawImage(new ImageIcon(currentBackground).getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set the preferred size of the panel
        bakeryPanel.setPreferredSize(new Dimension(3240, 2160)); // Match the image's resolution

        // Add small bounding boxes
        addSmallBoxes();

        // Force refresh
        bakeryPanel.revalidate();
        bakeryPanel.repaint();
    }

    private void addSmallBoxes() {
        // Define box properties
        int boxWidth = 100;
        int boxHeight = 100;
        int gap = 50; // Gap between boxes
        int startX = 300; // Adjust starting X position
        int yPosition = 1800; // Y position near the bottom of the panel

        // Create three boxes
        for (int i = 0; i < 3; i++) {
            JLabel box = new JLabel("Box " + (i + 1), SwingConstants.CENTER);
            box.setBounds(startX + i * (boxWidth + gap), yPosition, boxWidth, boxHeight);
            box.setOpaque(true);
            box.setBackground(Color.LIGHT_GRAY); // Set visible color
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            // Add mouse click listener for interaction
            box.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Clicked on " + ((JLabel) e.getSource()).getText());
                    // Add additional logic for changing scene or behavior
                }
            });

            bakeryPanel.add(box); // Add the box to the panel
        }

        // Debugging: Print a message when boxes are added
        System.out.println("Three boxes added to the bakery scene.");
    }

    public JPanel getPanel() {
        return bakeryPanel;
    }
}
