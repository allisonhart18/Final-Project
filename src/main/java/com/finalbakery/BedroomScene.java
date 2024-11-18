package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BedroomScene {
    // Constants for scene states and dimensions
    private static final String BEDROOM_IMAGE = "data/bedroom.png";
    private static final String BEDROOM2_IMAGE = "data/bedroompt2.png";
    private static final String BAKERY_IMAGE = "data/bakeryscene.png";
    private static final String BAKERY2_IMAGE = "data/bakery2.png";
    
    private static final int SMALL_BOX_WIDTH = 100;
    private static final int SMALL_BOX_HEIGHT = 100;
    private static final int SMALL_BOX_SPACING = 50;
    private static final int SMALL_BOX_Y_OFFSET = 300;
    
    private enum SceneState {
        BEDROOM,
        BEDROOM2,
        BAKERY,
        BAKERY2
    }
    
    private SceneState currentState = SceneState.BEDROOM;
    private final JPanel bedroomPanel;
    private final SceneController sceneController;
    private final InventoryBox inventoryBox;
    private final JLabel[] smallBoundingBoxes;
    private boolean smallBoxesVisible = false;

    public BedroomScene(SceneController sceneController) {
        this.sceneController = sceneController;
        this.inventoryBox = new InventoryBox();
        this.bedroomPanel = createBedroomPanel();
        this.smallBoundingBoxes = new JLabel[3];
        setup();
    }

    private JPanel createBedroomPanel() {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String imagePath = getImagePathForCurrentState();
                Image image = new ImageIcon(imagePath).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
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
        };
    }

    private void setup() {
        bedroomPanel.setPreferredSize(new Dimension(3240, 2160));
        addInteractiveDoor();
        addMainBoundingBoxes();
        createSmallBoundingBoxes();
        addInventoryBox();

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
        boundingBox.setOpaque(true);
        boundingBox.setBackground(new Color(255, 0, 0, 50));
        return boundingBox;
    }

    private void createSmallBoundingBoxes() {
        int totalWidth = (3 * SMALL_BOX_WIDTH) + (2 * SMALL_BOX_SPACING);
        int startX = (bedroomPanel.getPreferredSize().width - totalWidth) / 2;
        int yPosition = bedroomPanel.getPreferredSize().height - SMALL_BOX_Y_OFFSET;

        for (int i = 0; i < smallBoundingBoxes.length; i++) {
            final int index = i;
            JLabel smallBox = new JLabel();
            
            int xPos = startX + i * (SMALL_BOX_WIDTH + SMALL_BOX_SPACING);
            smallBox.setBounds(xPos, yPosition, SMALL_BOX_WIDTH, SMALL_BOX_HEIGHT);
            
            smallBox.setOpaque(true);
            smallBox.setBackground(new Color(0, 0, 255, 50));
            smallBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            smallBox.setVisible(false);
            
            smallBox.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (index == 0) {
                        currentState = SceneState.BAKERY2;
                        bedroomPanel.repaint();
                    }
                    System.out.println("Clicked small box " + index);
                }
            });
            
            smallBoundingBoxes[i] = smallBox;
            bedroomPanel.add(smallBox);
        }
    }

    private void updateSmallBoxPositions() {
        if (bedroomPanel.getWidth() == 0 || bedroomPanel.getHeight() == 0) {
            return;
        }
        
        int totalWidth = (3 * SMALL_BOX_WIDTH) + (2 * SMALL_BOX_SPACING);
        int startX = (bedroomPanel.getWidth() - totalWidth) / 2;
        int yPosition = bedroomPanel.getHeight() - SMALL_BOX_Y_OFFSET;

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

    private void addInventoryBox() {
        bedroomPanel.add(inventoryBox.getPanel());
    }

    public JPanel getPanel() {
        return bedroomPanel;
    }

    public InventoryBox getInventoryBox() {
        return inventoryBox;
    }
}