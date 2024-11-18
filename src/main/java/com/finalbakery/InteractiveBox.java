
// InteractiveBox.java
package com.finalbakery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class InteractiveBox extends JLabel {
    private final int index;
    
    public InteractiveBox(int x, int y, int width, int height, Color color, int index) {
        this.index = index;
        setBounds(x, y, width, height);
        setOpaque(true);
        setBackground(color);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
    
    public int getIndex() {
        return index;
    }
}
