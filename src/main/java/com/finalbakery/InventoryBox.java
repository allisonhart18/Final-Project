package com.finalbakery;
import javax.swing.*;
import java.awt.*;

public class InventoryBox {
    private JPanel panel;
    private ItemLinkedList itemList;

    public InventoryBox() {
        itemList = new ItemLinkedList();
        panel = new JPanel();
        panel.setLayout(new FlowLayout());  // Display items horizontally

        // Initial setup (could be changed later)
        panel.setBounds(2500, 1500, 200, 200);  // Positioning the inventory box
    }

    // Add an item to the inventory box
    public void addItemToInventory(Item item) {
        itemList.addItem(item);
        updateInventoryDisplay();
    }

    // Remove an item from the inventory box
    public void removeItemFromInventory(int index) {
        itemList.removeItem(index);
        updateInventoryDisplay();
    }

    // Update the display of items in the inventory box
    private void updateInventoryDisplay() {
        panel.removeAll();  // Clear the panel
        int itemCount = itemList.size();
        
        // Add all items to the panel
        for (int i = 0; i < itemCount; i++) {
            Item item = itemList.getItem(i);
            JLabel label = new JLabel(item.getImage());
            panel.add(label);
        }

        panel.revalidate();
        panel.repaint();
    }

    // Getter for the panel
    public JPanel getPanel() {
        return panel;
    }

    // Getter for the linked list (if needed)
    public ItemLinkedList getItemList() {
        return itemList;
    }
}
