/* Продолжение задания №4.3 */
package ru.lazarev.bird;

import java.util.Random;

/**
 * Представляет птицу Попугая.
 * Попугай поет часть заданного ему текста.
 */
public class Parrot extends Bird {
    private final String text; // Текст, который может петь попугай

    /**
     * Создает новый объект Попугай с заданным текстом для пения.
     * Имя по умолчанию "Попугай".
     * @param text Текст, который будет петь попугай. Если текст {@code null} или пустой,
     *             попугай будет молчать или выводить соответствующее сообщение при пении.
     */
    public Parrot(String text) {
        super("Попугай");
        if (text == null || text.isEmpty()) {
            System.out.println("Предупреждение: Попугаю не задан текст для пения. Он будет молчать.");
            this.text = "";
        } else {
            this.text = text;
        }
    }

    /**
     * Заставляет попугая петь.
     * Выводит на экран случайную начальную часть текста, который был задан при создании.
     * Если текст не был задан (null или пустой), выводит сообщение о том, что попугай молчит.
     */
    @Override
    public void sing() {
        if (text.isEmpty()) {
            System.out.println(getName() + " молчит, так как ему нечего сказать.");
            return;
        }
        Random random = new Random();
        int lengthSubstring = random.nextInt(text.length()) + 1;
        System.out.println(text.substring(0, lengthSubstring));
    }
}