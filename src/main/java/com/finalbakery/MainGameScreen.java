package com.finalbakery;

import javax.swing.*;

public class MainGameScreen {
    private JPanel mainPanel;

    public MainGameScreen(App app) {
        mainPanel = new JPanel();
        mainPanel.add(new JLabel("Welcome to the Main Game!"));
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
