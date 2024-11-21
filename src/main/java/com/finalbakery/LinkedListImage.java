/*
 * allison Hart
 * 11/20
 * class holds linkedlist which displays the arrow and chef outfit seen
 * in bedroom scene
 */
// manage and display a collection of images using a LinkedList
package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class LinkedListImage {
    private LinkedList<JLabel> imageLabels;

    public LinkedListImage() {
        imageLabels = new LinkedList<>();
    }
// Adds an image with specified properties to the linked list
    public void addImage(String imagePath, int x, int y, int width, int height) {
        JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
        imageLabel.setBounds(x, y, width, height);
        imageLabels.add(imageLabel); // Add to the list
    }
// Displays all images on the provided panel
    public void displayImages(JPanel panel) {
        for (JLabel imageLabel : imageLabels) {
            panel.add(imageLabel); // add each image
        }
        panel.revalidate(); // Refresh the panel layout
        panel.repaint();
    }
}
