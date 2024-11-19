package ru.lazarev.bird;

import java.util.Random;

public class Parrot extends Bird {
    private String text;

    public Parrot(String text) {
        super("Попугай");
        this.text = text;
    }

    @Override
    public void sing() {
        Random random = new Random();
        int n = random.nextInt(text.length()) + 1;
        System.out.println(text.substring(0, n));
    }
}
