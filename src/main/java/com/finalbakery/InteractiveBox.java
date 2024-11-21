/*
 * Allison Hart
 * 11/19
 * 
 * This class helps make all of my bounding boxes
 * 
 */


package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class InteractiveBox extends JLabel {
    private final int index;
    //customize box size and color when needed
    public InteractiveBox(int x, int y, int width, int height, Color color, int index) {
        this.index = index;
        setBounds(x, y, width, height); // position and size
        setOpaque(true);
        setBackground(color); // color when needed
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // border when needed
    }
    //getter for index o fbox
    public int getIndex() {
        return index;
    }
}
