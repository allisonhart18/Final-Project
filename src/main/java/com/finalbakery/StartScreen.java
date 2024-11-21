/*
 * Allison Hart
 * 11/19
 * 
 *
 *  initializes and returns the start panel to be used in the main application
 */
package com.finalbakery;

import javax.swing.*;

public class StartScreen {
    private App app;
    private StartPanel startPanel;

    public StartScreen(App app) {
        this.app = app;
        startPanel = new StartPanel(app); // Initialize the start panel
    }

    public JPanel getPanel() {
        return startPanel; // Return the panel to be used in App
    }
}
