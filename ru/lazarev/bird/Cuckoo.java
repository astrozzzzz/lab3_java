package ru.lazarev.bird;

import java.util.Random;

public class Cuckoo extends Bird {
    public Cuckoo() {
        super("Кукушка");
    }

    @Override
    public void sing() {
        Random random = new Random();
        int times = random.nextInt(10) + 1;
        for (int i = 0; i < times; i++) {
            System.out.println("ку-ку");
        }
    }
}
