package com.finalbakery;
import javax.swing.ImageIcon;


public class InventoryItem {
    private ImageIcon image;

    public InventoryItem(String imagePath) {
        this.image = new ImageIcon(imagePath);  // Load image from file
    }

    public ImageIcon getImage() {
        return image;
    }
}
