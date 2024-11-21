/*
 * Allison Hart
 * 11/19
 * 
 * creates a main panel displaying a welcome message for the main game screen.
 * 
 */

package com.finalbakery;

import javax.swing.*;

public class MainGameScreen {
    private JPanel mainPanel; // Panel to hold the main game components

    public MainGameScreen(App app) {
        mainPanel = new JPanel(); // create main panel
        mainPanel.add(new JLabel("Welcome to the Main Game!"));
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
