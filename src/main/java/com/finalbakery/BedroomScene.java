package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BedroomScene {
    private JPanel bedroomPanel;
    private SceneController sceneController;
    private InventoryBox inventoryBox;  // Reference to InventoryBox

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
                // Draw bedroom background image
                g.drawImage(new ImageIcon("data/bedroom.png").getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Add interactive image (door)
        JLabel interactiveImage = createInteractiveImage();
        bedroomPanel.add(interactiveImage);

        // Add the inventory box (ensuring it's on top of other components)
        bedroomPanel.add(inventoryBox.getPanel());
        
        // Ensure that the panel is properly updated
        bedroomPanel.revalidate();
        bedroomPanel.repaint();
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
