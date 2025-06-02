/** Продолжение задания №3.5
 * Задание 8.4
 * Клонирование точки.
 * Измените сущность Точка из 3.6.2.Переопределите метод клонирования, унаследованный от
 * класса Object, таким образом, чтобы при его вызове возвращался новый объект Точки, значения
 * полей которого будут копиями оригинальной Точки.
 */
package ru.lazarev.geometry;

/**
 * Представляет точку на двумерной плоскости с координатами X и Y.
 * Поддерживает клонирование.
 */
public class Dot implements Cloneable {
    private double x;
    private double y;

    /**
     * Создает новую точку с указанными координатами.
     * @param x Координата X.
     * @param y Координата Y.
     */
    public Dot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Устанавливает координату X точки.
     * @param x Новое значение координаты X.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Устанавливает координату Y точки.
     * @param y Новое значение координаты Y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Возвращает координату X точки.
     * @return Координата X.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Возвращает координату Y точки.
     * @return Координата Y.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Возвращает строковое представление точки.
     * Формат: "{X;Y}".
     * @return Строковое представление точки.
     */
    @Override
    public String toString() {
        return "{" + this.x + ";" + this.y + "}";
    }

    /**
     * Создает и возвращает копию этого объекта {@code Dot}.
     * @return Клон объекта точки.
     * @see Object#clone()
     */
    @Override
    public Dot clone() {
        try {
            Dot cloned = (Dot) super.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            System.out.println("Критическая ошибка: не удалось клонировать объект Dot. " + e.getMessage());
            return null;
        }
    }
}