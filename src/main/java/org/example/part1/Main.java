package org.example.part1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>(11);
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000);
            String name = "Name" + id;
            table.put(new MyTestingClass(id, name), "Value" + i);
        }

        table.printBucketSizes();
    }
}