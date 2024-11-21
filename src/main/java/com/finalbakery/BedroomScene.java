/*
 * Allison Hart
 * 11/19
 * 
 * this class holds all of the functionality that occurs when the player
 * is in the bedroom of the game
 * 
 */
package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BedroomScene {
    // Constants for scene states and dimensions
    private static final String BEDROOM_IMAGE = "data/bedroom.png";
    private static final String BEDROOM2_IMAGE = "data/bedroompt2.png";
    private static final String BAKERY_IMAGE = "data/bakeryscene.png";
    private static final String BAKERY2_IMAGE = "data/bakery2.png";
    private static final String BAKERY3_IMAGE = "data/bakery3.png"; // Added BAKERY3_IMAGE
    private static final String END_BAKERY_IMAGE = "data/endbakerybackground.png";
    private static final String GIF_PATH = "data/cakescene.gif";

    private static final int SMALL_BOX_WIDTH = 100;
    private static final int SMALL_BOX_HEIGHT = 100;
    private static final int SMALL_BOX_SPACING = 100; // Updated spacing
    private static final int SMALL_BOX_Y_OFFSET = 300;
    private static final int SMALL_BOX_Y_ADJUSTMENT = 100; // Updated Y adjustment

    // Enum to represent different scene states
    private enum SceneState {
        BEDROOM,
        BEDROOM2,
        BAKERY,
        BAKERY2,
        BAKERY3 // Added BAKERY3 state
    }

    private SceneState currentState = SceneState.BEDROOM; // tracks current state
    private final JPanel bedroomPanel;
    private final SceneController sceneController; // manages scene controlling
    private final JLabel[] smallBoundingBoxes;
    private boolean smallBoxesVisible = false;
    private final LinkedListImage linkedListImage;

    public BedroomScene(SceneController sceneController) {
        this.sceneController = sceneController;
        this.bedroomPanel = createBedroomPanel();
        this.smallBoundingBoxes = new JLabel[3];
        this.linkedListImage = new LinkedListImage();
        setup();
    }
//bedroom panel
    private JPanel createBedroomPanel() {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String imagePath = getImagePathForCurrentState();
                Image image = new ImageIcon(imagePath).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                // Debugging: Print the image path being drawn
                System.out.println("Drawing background: " + imagePath);
            }
        };

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateSmallBoxPositions();
            }
        });

        return panel;
    }

    private String getImagePathForCurrentState() {
        return switch (currentState) {
            case BEDROOM -> BEDROOM_IMAGE;
            case BEDROOM2 -> BEDROOM2_IMAGE;
            case BAKERY -> BAKERY_IMAGE;
            case BAKERY2 -> BAKERY2_IMAGE;
            case BAKERY3 -> BAKERY3_IMAGE; // Added case for BAKERY3
            default -> BAKERY_IMAGE; // Default to bakery image
        };
    }

    private void setup() {
        bedroomPanel.setPreferredSize(new Dimension(3240, 2160));
        addInteractiveDoor();
        addMainBoundingBoxes();
        createSmallBoundingBoxes();
        addImages();

        bedroomPanel.revalidate();
        bedroomPanel.repaint();
    }

    private void addInteractiveDoor() {
        JLabel doorLabel = new JLabel(new ImageIcon("data/door.png"));
        doorLabel.setBounds(2400, 800, 200, 200);
        doorLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sceneController.switchToMainGameScreen();
            }
        });
        bedroomPanel.add(doorLabel);
    }

    private void addMainBoundingBoxes() {
        // First bounding box (right side)
        JLabel rightBox = createBoundingBox(1200, 470, 200, 200);
        rightBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentState = (currentState == SceneState.BEDROOM) ?
                              SceneState.BEDROOM2 : SceneState.BEDROOM;
                updateBoundingBoxesVisibility();
                bedroomPanel.repaint();
            }
        });
        bedroomPanel.add(rightBox);

        // Second bounding box (left side)
        JLabel leftBox = createBoundingBox(100, 470, 200, 200);
        leftBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentState = SceneState.BAKERY;
                smallBoxesVisible = true;
                updateBoundingBoxesVisibility();
                bedroomPanel.repaint();
            }
        });
        bedroomPanel.add(leftBox);
    }

    private JLabel createBoundingBox(int x, int y, int width, int height) {
        JLabel boundingBox = new JLabel();
        boundingBox.setBounds(x, y, width, height);
        boundingBox.setOpaque(false); // Make the box transparent
        boundingBox.setBorder(null); // Remove the border
        return boundingBox;
    }

    private void createSmallBoundingBoxes() {
        int totalWidth = (3 * SMALL_BOX_WIDTH) + (2 * SMALL_BOX_SPACING);
        int startX = (bedroomPanel.getPreferredSize().width - totalWidth) / 2;
        int yPosition = bedroomPanel.getPreferredSize().height - SMALL_BOX_Y_OFFSET + SMALL_BOX_Y_ADJUSTMENT; // Adjusted Y position

        for (int i = 0; i < smallBoundingBoxes.length; i++) {
            final int index = i;
            JLabel smallBox = new JLabel();

            int xPos = startX + i * (SMALL_BOX_WIDTH + SMALL_BOX_SPACING);
            smallBox.setBounds(xPos, yPosition, SMALL_BOX_WIDTH, SMALL_BOX_HEIGHT);

          
            smallBox.setBackground(new Color(0, 0, 255, 50)); 
          
            smallBox.setVisible(false);

            smallBox.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Clicked small box " + index);
                    switch (index) {
                        case 0:
                            currentState = SceneState.BAKERY2;
                            bedroomPanel.repaint();
                            break;
                        case 1:
                            currentState = SceneState.BAKERY3; // Change to BAKERY3
                            bedroomPanel.repaint();
                            break;
                        case 2:
                            showAnimation(); // play animtion
                            break;
                    }
                }
            });

            smallBoundingBoxes[i] = smallBox;
            bedroomPanel.add(smallBox); // add poxes to screen 
        }
    }

    private void addImages() {
        // Add images to the same spot where the bounding boxes are
        linkedListImage.addImage("data/image1.png", 1200, 470, 200, 200);
        linkedListImage.addImage("data/image2.png", 100, 470, 200, 200);
        linkedListImage.displayImages(bedroomPanel);
    }
//play animations and transitions
    private void showAnimation() {
        JPanel animationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(GIF_PATH).getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        animationPanel.setLayout(null);
        animationPanel.setSize(bedroomPanel.getSize());

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bedroomPanel.removeAll();
                currentState = SceneState.BAKERY; // Set to BAKERY state to use the end bakery image
                bedroomPanel.revalidate();
                bedroomPanel.repaint();

                // Transition to BakeryScene2
                sceneController.switchToBakeryScene2();
            }
        });
        timer.setRepeats(false);
        timer.start();

        bedroomPanel.add(animationPanel);
        bedroomPanel.revalidate();
        bedroomPanel.repaint();
    }

    private void updateSmallBoxPositions() {
        if (bedroomPanel.getWidth() == 0 || bedroomPanel.getHeight() == 0) {
            return;
        }

        int totalWidth = (3 * SMALL_BOX_WIDTH) + (2 * SMALL_BOX_SPACING);
        int startX = (bedroomPanel.getWidth() - totalWidth) / 2;
        int yPosition = bedroomPanel.getHeight() - SMALL_BOX_Y_OFFSET + SMALL_BOX_Y_ADJUSTMENT; // Adjusted Y position

        for (int i = 0; i < smallBoundingBoxes.length; i++) {
            if (smallBoundingBoxes[i] != null) {
                int xPos = startX + i * (SMALL_BOX_WIDTH + SMALL_BOX_SPACING);
                smallBoundingBoxes[i].setBounds(xPos, yPosition, SMALL_BOX_WIDTH, SMALL_BOX_HEIGHT);
            }
        }
    }

    private void updateBoundingBoxesVisibility() {
        System.out.println("Updating box visibility: " + smallBoxesVisible + " for state: " + currentState);

        for (JLabel smallBox : smallBoundingBoxes) {
            if (smallBox != null) {
                smallBox.setVisible(smallBoxesVisible);
            }
        }
        bedroomPanel.revalidate();
        bedroomPanel.repaint();
    }
//returns the main panel for display
    public JPanel getPanel() {
        return bedroomPanel;
    }
}
