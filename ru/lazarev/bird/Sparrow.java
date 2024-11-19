package ru.lazarev.bird;

public class Sparrow extends Bird {
    public Sparrow() {
        super("Воробей");
    }

    @Override
    public void sing() {
        System.out.println("чырык");
    }
}
