/* Продолжение задания №4.3 */
package ru.lazarev.bird;

/**
 * Представляет птицу Воробья.
 * Воробей поет "чырык".
 */
public class Sparrow extends Bird {

    /**
     * Создает новый объект Воробей.
     * Имя по умолчанию "Воробей".
     */
    public Sparrow() {
        super("Воробей");
    }

    /**
     * Заставляет воробья петь.
     * Выводит "чырык" на экран.
     */
    @Override
    public void sing() {
        System.out.println("чырык");
    }
}