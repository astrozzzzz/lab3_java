/* Продолжение задания №4.3 */
package ru.lazarev.bird;

import java.util.Random;

/**
 * Представляет птицу Кукушку.
 * Кукушка поет "ку-ку" случайное количество раз.
 */
public class Cuckoo extends Bird {
    private static final int MAX_CUCKOO_SOUNDS = 10; // Максимальное количество "ку-ку"

    /**
     * Создает новый объект Кукушка.
     * Имя по умолчанию "Кукушка".
     */
    public Cuckoo() {
        super("Кукушка");
    }

    /**
     * Заставляет кукушку петь.
     * Выводит "ку-ку" на экран случайное количество раз (от 1 до {@value #MAX_CUCKOO_SOUNDS}).
     */
    @Override
    public void sing() {
        Random random = new Random();
        // Генерирует случайное число от 1 до MAX_CUCKOO_SOUNDS включительно
        int times = random.nextInt(MAX_CUCKOO_SOUNDS) + 1;
        for (int i = 0; i < times; i++) {
            System.out.println("ку-ку");
        }
    }
}