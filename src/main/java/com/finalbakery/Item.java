package com.finalbakery;
import javax.swing.ImageIcon;


public class Item {
    private String name;
    private ImageIcon image;

    public Item(String name, String imagePath) {
        this.name = name;
        this.image = new ImageIcon(imagePath);
    }

    public String getName() {
        return name;
    }

    public ImageIcon getImage() {
        return image;
    }
}
