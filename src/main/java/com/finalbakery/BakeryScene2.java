/*
 * allison hart
 * 11/20
 * this class holds all of the functionality after the second 
 * animation plays
 * 
 */
package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BakeryScene2 extends AbstractScene {
    private String currentBackground = "data/batter.png"; // Default background image path
    private String gifPath = "data/oventime.gif"; // Path to the GIF file
    private String endBackground = "data/endscene.png"; // Path to the end background image
    private boolean isAnimationComplete = false;

    public BakeryScene2(SceneController sceneController) {
        super(sceneController); // pass scenecontroller super class
    }

    @Override
    protected String getCurrentBackgroundPath() {
        return currentBackground;
    }

    @Override
    protected void setup() {
        // Add small bounding box in the middle
        addSmallBox();

        // Force refresh
        scenePanel.revalidate();
        scenePanel.repaint();
    }

    private void addSmallBox() {
        // Define box properties
        int boxWidth = 300; // Updated width
        int boxHeight = 300; // Updated height
        int xPosition = (scenePanel.getWidth() - boxWidth) / 2; // Center the box horizontally
        int yPosition = (scenePanel.getHeight() - boxHeight) / 2; // Center the box vertically

        JLabel box = createInteractiveBox(xPosition, yPosition, boxWidth, boxHeight);
       
        box.setHorizontalAlignment(SwingConstants.CENTER);

        // Add mouse click listener for interaction
        box.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked on " + ((JLabel) e.getSource()).getText());
                showAnimation();
            }
        });

        scenePanel.add(box); // Add the box to the panel

        // Debugging: Print a message when the box is added
        System.out.println("Small box added to the bakery scene 2.");
    }

    //displays nimation and transitions to end background
    private void showAnimation() {
        JPanel animationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(gifPath).getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        animationPanel.setLayout(null);
        animationPanel.setSize(scenePanel.getSize());

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAnimationComplete = true;
                scenePanel.removeAll();
                currentBackground = endBackground; // Set to end background
                scenePanel.revalidate();
                scenePanel.repaint();
            }
        });
        timer.setRepeats(false); // timer only runs once
        timer.start();

        scenePanel.add(animationPanel);
        scenePanel.revalidate();
        scenePanel.repaint();
    }
}
