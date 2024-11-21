/*
 * Allison Hart
 * 11/19
 * 
 * interface defines a contract for screens in the application, requiring 
 * setup functionality and access to  main panel
 * 
 */

package com.finalbakery;

import javax.swing.JPanel;

public interface Screen {
    // Method to set up the screen's components
    void setup();
     // Method to retrieve the screen's main panel
    JPanel getPanel(); 
}
