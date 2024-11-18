package com.finalbakery;

import javax.swing.*;

public class SceneController {
    private App app;

    // Constructor accepting an App instance
    public SceneController(App app) {
        this.app = app;
    }

    // Method to switch scenes by replacing the content of the main frame
    public void switchScene(JPanel newScene) {
        JFrame mainFrame = app.getMainFrame();
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(newScene);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    // Method to switch to the main game screen (optional specific logic)
    public void switchToMainGameScreen() {
        MainGameScreen mainGameScreen = new MainGameScreen(app);
        switchScene(mainGameScreen.getPanel());
    }
}
