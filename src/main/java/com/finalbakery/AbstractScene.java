/*
 * Allison Hart
 * 11/19
 * 
 * This class is an abstract class that is in charge of switching backgrounds
 * when needed 
 * 
 */

package com.finalbakery;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractScene {
    //visual elements
    protected final JPanel scenePanel;
    protected final SceneController sceneController;
   
// constants for boxes
    protected static final int SMALL_BOX_WIDTH = 100;
    protected static final int SMALL_BOX_HEIGHT = 100;
    protected static final int SMALL_BOX_SPACING = 50;

    //constructor
    protected AbstractScene(SceneController sceneController) {
        this.sceneController = sceneController;
        this.scenePanel = createScenePanel();
        setup();
    }
//displays background image
    protected JPanel createScenePanel() {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String imagePath = getCurrentBackgroundPath();
                Image image = new ImageIcon(imagePath).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                // Debugging: Print the image path being drawn
                System.out.println("Drawing background: " + imagePath);
            }
        };

        panel.setPreferredSize(new Dimension(3240, 2160));
        return panel;
    }
// Abstract method to get the current background image path (implemented by subclasses)
    protected abstract String getCurrentBackgroundPath();
    // Abstract method to set up specific scene elements (implemented by subclasses)
    protected abstract void setup();

    protected JLabel createInteractiveBox(int x, int y, int width, int height) {
        JLabel box = new JLabel();
        box.setBounds(x, y, width, height);
        return box;
    }

    public JPanel getPanel() {
        return scenePanel;
    }

  
}
