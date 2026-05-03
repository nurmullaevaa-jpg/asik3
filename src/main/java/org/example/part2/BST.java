package org.example.part2;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Node> {
    private Node root;
    private int size = 0;

    public class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() { return key; }
        public V getValue() { return val; }
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }

        Node current = root;
        Node parent = null;
        int cmp = 0;

        while (current != null) {
            parent = current;
            cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else {
                current.val = val;
                return;
            }
        }

        if (cmp < 0) parent.left = new Node(key, val);
        else parent.right = new Node(key, val);
        size++;
    }

    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return current.val;
        }
        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node current = root;

        while (current != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) current = current.left;
            else current = current.right;
        }

        if (current == null) return;

        if (current.left == null || current.right == null) {
            Node newNode = (current.left == null) ? current.right : current.left;
            if (parent == null) root = newNode;
            else if (current == parent.left) parent.left = newNode;
            else parent.right = newNode;
        }
        else {
            Node successorParent = current;
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.key = successor.key;
            current.val = successor.val;

            if (successorParent != current) successorParent.left = successor.right;
            else successorParent.right = successor.right;
        }
        size--;
    }

    public int size() { // Part 2.2.1
        return size;
    }

    @Override
    public Iterator<Node> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<Node> {
        private Stack<Node> stack = new Stack<>();

        public InOrderIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node x) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node node = stack.pop();
            pushLeft(node.right);
            return node;
        }
    }
}