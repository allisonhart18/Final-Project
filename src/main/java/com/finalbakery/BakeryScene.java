/*
 * Allison Hart
 * 11/19
 * 
 * manages the BakeryScene in a game, allowing background changes, 
 * interactions with clickable boxes, and transitioning to other scenes with animations.
 * 
 */
package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BakeryScene extends AbstractScene {
    private String currentBackground = "data/bakeryscene.png"; // Default background image path
    private String gifPath = "data/cakescene.gif"; // Path to the GIF file
    private String bakery1Background = "data/bakery1.png"; // Path to the bakery1 background image
    private String bakery2Background = "data/bakery2.png"; // Path to the bakery2 background image
    private String bakery3Background = "data/bakery3.png"; // Path to the bakery3 background image
    private String endBakeryBackground = "data/endbakerybackground.png"; // Path to the end bakery background image
    private boolean isAnimationComplete = false;

    public BakeryScene(SceneController sceneController) {
        super(sceneController);
    }

    @Override
    protected String getCurrentBackgroundPath() {
        return currentBackground; // returns current background image path
    }

    @Override
    protected void setup() {
        // Add small bounding boxes
        addSmallBoxes();

        // Force refresh
        scenePanel.revalidate();
        scenePanel.repaint();
    }

    private void addSmallBoxes() {
        // Define box properties
        int boxWidth = SMALL_BOX_WIDTH;
        int boxHeight = SMALL_BOX_HEIGHT;
        int gap = SMALL_BOX_SPACING; // Gap between boxes
        int startX = 300; // Adjust starting X position
        int yPosition = 1800; // Y position near the bottom of the panel

        // Create three boxes
        for (int i = 0; i < 3; i++) {
            final int index = i; // Capture the value of i in a final variable
            JLabel box = createInteractiveBox(startX + index * (boxWidth + gap), yPosition, boxWidth, boxHeight);
            box.setText("Box " + (index + 1));
            box.setHorizontalAlignment(SwingConstants.CENTER);

            // Set the background color of the middle box to blue
            if (index == 1) {
                box.setOpaque(true);
               // box.setBackground(Color.BLUE);
            }

            // Add mouse click listener for interaction
            box.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Clicked on " + ((JLabel) e.getSource()).getText());
                    switch (index) {
                        case 0:
                            currentBackground = bakery1Background;
                            break;
                        case 1:
                            currentBackground = bakery3Background;
                            break;
                        case 2:
                            showAnimation();
                            break;
                    }
                    scenePanel.revalidate();
                    scenePanel.repaint();
                }
            });

            scenePanel.add(box); // Add the box to the panel
        }

        // Debugging: Print a message when boxes are added
        System.out.println("Three boxes added to the bakery scene.");
    }

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

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAnimationComplete = true;
                scenePanel.removeAll();
                currentBackground = "data/batter.png"; // Set to batter background
                scenePanel.revalidate();
                scenePanel.repaint();

                // Transition to BakeryScene2
                sceneController.switchToBakeryScene2();
            }
        });
        timer.setRepeats(false);
        timer.start();

        scenePanel.add(animationPanel);
        scenePanel.revalidate();
        scenePanel.repaint();
    }
}
