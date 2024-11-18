package com.finalbakery;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractScene {
    protected final JPanel scenePanel;
    protected final SceneController sceneController;
    protected final InventoryBox inventoryBox;
    
    protected static final int SMALL_BOX_WIDTH = 100;
    protected static final int SMALL_BOX_HEIGHT = 100;
    protected static final int SMALL_BOX_SPACING = 50;
    
    protected AbstractScene(SceneController sceneController) {
        this.sceneController = sceneController;
        this.inventoryBox = new InventoryBox();
        this.scenePanel = createScenePanel();
        setup();
    }
    
    protected JPanel createScenePanel() {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String imagePath = getCurrentBackgroundPath();
                Image image = new ImageIcon(imagePath).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        panel.setPreferredSize(new Dimension(3240, 2160));
        return panel;
    }
    
    protected abstract String getCurrentBackgroundPath();
    protected abstract void setup();
    
    protected JLabel createInteractiveBox(int x, int y, int width, int height, Color color) {
        JLabel box = new JLabel();
        box.setBounds(x, y, width, height);
        box.setOpaque(true);
        box.setBackground(color);
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return box;
    }
    
    public JPanel getPanel() {
        return scenePanel;
    }
    
    public InventoryBox getInventoryBox() {
        return inventoryBox;
    }
}