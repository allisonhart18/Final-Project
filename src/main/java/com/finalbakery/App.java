/*
 * Allison Hart
 * 11/19
 * 
 * this class calls all of my other classes so that they appear in order
 * 
 * 
 */


 package com.finalbakery;

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 
 public class App {
     public static final String MAIN_GAME_SCREEN = "MainGameScreen";
     private JFrame mainFrame; // main app. window
     private SceneController sceneController;
     private BedroomScene bedroomScene;
     private StartScreen startScreen;
     private boolean isAnimationComplete = false; //sees if animation gifs are done
 
     public App() {
        //initialize main frame and scenes
         mainFrame = new JFrame("Final Bakery Game");
         sceneController = new SceneController(this);
         bedroomScene = new BedroomScene(sceneController);
         startScreen = new StartScreen(this);
     }
 
     public void setup() {
         mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         mainFrame.setSize(3240, 2160);
         mainFrame.setLocationRelativeTo(null);
 
         //start panel called
         JPanel startPanel = startScreen.getPanel();
         mainFrame.add(startPanel, BorderLayout.CENTER);
 
         mainFrame.setVisible(true);
     }
 
     public void startGame() {
        //tranistion from star to animation
         startScreen.getPanel().setVisible(false);
         showAnimation();
     }
 
     private void showAnimation() {
        //show intro animation
         JPanel animationPanel = new JPanel() {
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.drawImage(new ImageIcon("data/introAnimation.gif").getImage(), 0, 0, getWidth(), getHeight(), this);
             }
         };
         animationPanel.setLayout(null);
         animationPanel.setSize(mainFrame.getSize());
 // after 6 seconds bedroom scene opens
         Timer timer = new Timer(6000, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 mainFrame.getContentPane().removeAll(); // Clear previous components
                 mainFrame.add(bedroomScene.getPanel(), BorderLayout.CENTER);
                 mainFrame.revalidate();
                 mainFrame.repaint();
             }
         });
         timer.setRepeats(false);
         timer.start();
 
         mainFrame.getContentPane().add(animationPanel);
         mainFrame.revalidate();
         mainFrame.repaint();
     }
 
     public JFrame getMainFrame() {
         return mainFrame;
     }
 
     public static void main(String[] args) {
         App app = new App();
         app.setup();
     }
 }
 