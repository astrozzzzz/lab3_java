/** Продолжение задания №8.4
 * Задание №3.5
 * Трехмерная точка.
 * Создайте такой подвид сущности Точка из задачи 1.1.1, которая будет иметь не две, а три
 * координаты на плоскости: X,Y,Z.
 */
package ru.lazarev.geometry;

/**
 * Представляет точку в трехмерном пространстве с координатами X, Y, Z.
 * Наследуется от {@link Dot} и добавляет координату Z.
 * Поддерживает клонирование.
 */
public class Dot2 extends Dot {
    private double z;

    /**
     * Создает новую трехмерную точку с указанными координатами.
     * @param x Координата X.
     * @param y Координата Y.
     * @param z Координата Z.
     */
    public Dot2(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    /**
     * Устанавливает координату Z точки.
     * @param z Новое значение координаты Z.
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Возвращает координату Z точки.
     * @return Координата Z.
     */
    public double getZ() {
        return this.z;
    }

    /**
     * Возвращает строковое представление трехмерной точки.
     * Формат: "{X;Y;Z}".
     * @return Строковое представление точки.
     */
    @Override
    public String toString() {
        return "{" + getX() + ";" + getY() + ";" + this.z + "}";
    }

    /**
     * Создает и возвращает копию этого объекта {@code Dot2}.
     * Копируются все три координаты X, Y, Z.
     * @return Клон объекта трехмерной точки.
     * @see Dot#clone()
     */
    @Override
    public Dot2 clone() {
        Dot2 cloned = (Dot2) super.clone();
        return cloned;
    }
}