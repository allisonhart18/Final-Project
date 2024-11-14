package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BedroomScene {
    private JPanel bedroomPanel;
    private SceneController sceneController;
    private InventoryBox inventoryBox;  // Reference to InventoryBox
    private boolean isBedroom2 = false;  // Flag to toggle between images

    public BedroomScene(SceneController sceneController) {
        this.sceneController = sceneController;
        this.inventoryBox = new InventoryBox();  // Initialize InventoryBox
        setup();
    }

    private void setup() {
        // Create the bedroomPanel with a null layout to manage custom placement of components
        bedroomPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw bedroom background image based on isBedroom2 flag
                String imagePath = isBedroom2 ? "data/bedroompt2.png" : "data/bedroom.png";
                g.drawImage(new ImageIcon(imagePath).getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Add interactive image (door)
        JLabel interactiveImage = createInteractiveImage();
        bedroomPanel.add(interactiveImage);

        // Create the bounding box and add it to the panel
        createBoundingBox();

        // Add the inventory box (ensuring it's on top of other components)
        bedroomPanel.add(inventoryBox.getPanel());

        // Ensure that the panel is properly updated
        bedroomPanel.revalidate();
        bedroomPanel.repaint();
    }

    private void createBoundingBox() {
        // Create a rectangular bounding box in the center of the screen
        JLabel boundingBox = new JLabel();
        boundingBox.setBounds(1200, 470, 200, 200); // Position and size of the bounding box
      //  boundingBox.setBorder(BorderFactory.createLineBorder(Color.RED, 5)); // Red border to indicate clickable area
        
        boundingBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Toggle the image between bedroom.png and bedroompt2.png
                isBedroom2 = !isBedroom2;
                bedroomPanel.repaint();  // Redraw the panel with the new image
            }
        });

        bedroomPanel.add(boundingBox); // Add bounding box to the panel
    }

    private JLabel createInteractiveImage() {
        // Create the door interactive image
        JLabel interactiveImage = new JLabel(new ImageIcon("data/door.png"));
        interactiveImage.setBounds(2400, 800, 200, 200);
        
        interactiveImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sceneController.switchToMainGameScreen();  // Switch scene on click
            }
        });
        
        return interactiveImage;
    }

    public JPanel getPanel() {
        return bedroomPanel;
    }
}
