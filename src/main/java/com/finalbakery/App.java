package com.finalbakery;

import javax.swing.*;

public class App extends JFrame {
    public static final int MAIN_GAME_SCREEN = 1;

    private MidiPlayer midiPlayer;
    private Screen startScreen;
    private Screen mainGameScreen;

    public App() {
        setTitle("Game");
        setSize(3240, 2160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        midiPlayer = new MidiPlayer();
        midiPlayer.loadMidiFiles("data/sound1.mid", "data/sound2.mid");

        startScreen = new StartScreen(this);
        mainGameScreen = new MainGameScreen();

        setContentPane(startScreen.getPanel());
    }

    public void playMidiFiles() {
        midiPlayer.playAll();
    }

    public void changeScreen(int screen) {
        if (screen == MAIN_GAME_SCREEN) {
            setContentPane(mainGameScreen.getPanel());
        } else {
            setContentPane(startScreen.getPanel());
        }
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}
