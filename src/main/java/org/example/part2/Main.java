package org.example.part2;

public class Main {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        tree.put(50, "Apple");
        tree.put(30, "Banana");
        tree.put(70, "Cherry");
        tree.put(20, "Date");
        tree.put(40, "Eggplant");

        System.out.println("Tree size: " + tree.size());

        System.out.println("Iterating over tree:");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        System.out.println("\nDeleting key 30...");
        tree.delete(30);
        System.out.println("New size: " + tree.size());

        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}