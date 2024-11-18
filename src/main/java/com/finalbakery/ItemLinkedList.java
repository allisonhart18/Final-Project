package com.finalbakery;

public class ItemLinkedList {
    private Node head;

    private class Node {
        Item item;
        Node next;

        public Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }

    public ItemLinkedList() {
        head = null;
    }

    // Add item to the list
    public void addItem(Item item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Get item by index
    public Item getItem(int index) {
        Node current = head;
        for (int i = 0; current != null && i < index; i++) {
            current = current.next;
        }
        return current != null ? current.item : null;
    }

    // Remove item by index
    public void removeItem(int index) {
        if (head == null) return;

        if (index == 0) {
            head = head.next;
            return;
        }

        Node current = head;
        for (int i = 0; current != null && i < index - 1; i++) {
            current = current.next;
        }

        if (current != null && current.next != null) {
            current.next = current.next.next;
        }
    }

    // Get the size of the linked list
    public int size() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }
}
