package com.finalbakery;

import javax.swing.*;

public class SceneController {
    private App app;

    public SceneController(App app) {
        this.app = app;
    }

    public void switchToMainGameScreen() {
        JPanel mainGameScreenPanel = new JPanel(); // Replace with your actual main game screen panel
        app.getMainFrame().setContentPane(mainGameScreenPanel);
        app.getMainFrame().revalidate();
        app.getMainFrame().repaint();
    }
}
