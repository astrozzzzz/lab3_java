/** Задание №3.3
 * Двусторонняя дорога.
 * Создайте такой подвид сущности Город, полученной в задаче 2.1.10, которая будет гарантировать,
 * что при добавлении дороги из одного города в другой, одновременно будет добавляться и
 * обратная дорога
 */
package ru.lazarev.city;

import java.util.Map;

/**
 * Представляет город с двусторонними дорогами.
 * При добавлении или удалении дороги из этого города в другой,
 * автоматически добавляется или удаляется обратная дорога.
 * Наследуется от {@link City}.
 */
public class BidirectionalCity extends City {

    /**
     * Создает новый двусторонний город с указанным именем.
     * @param name Имя города. Передается в конструктор суперкласса {@link City}.
     */
    public BidirectionalCity(String name) {
        super(name);
    }

    /**
     * Создает новый двусторонний город с указанным именем и начальным набором путей.
     * Все добавляемые пути становятся двусторонними.
     * @param name Имя города. Передается в конструктор суперкласса {@link City}.
     * @param p    Карта путей. Если {@code null}, пути не добавляются.
     *             Каждый путь из карты будет добавлен как двусторонний.
     */
    public BidirectionalCity(String name, Map<City, Integer> p) {
        super(name);
        if (p != null) {
            for (Map.Entry<City, Integer> entry : p.entrySet()) {
                this.addPath(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Добавляет двусторонний путь между текущим городом и указанным городом.
     * Если путь добавляется из A в B, то также добавляется путь из B в A с той же стоимостью.
     * Метод учитывает правила добавления пути из суперкласса {@link City} (например, стоимость не 0,
     * город не null, путь еще не существует).
     * @param city Город назначения.
     * @param cost Стоимость пути.
     */
    @Override
    public void addPath(City city, int cost) {
        if (city == null) {
            System.out.println("Ошибка (BidirectionalCity): не удалось добавить путь. " +
                    "Город назначения не может быть null.");
            return;
        }
        if (city == this) {
            System.out.println("Предупреждение (BidirectionalCity): попытка добавить путь " +
                    "к самому себе. Путь не добавлен.");
            return;
        }
        if (cost == 0) {
            System.out.println("Предупреждение (BidirectionalCity): стоимость пути не может быть 0. Путь в город '"
                    + city.getName() + "' не добавлен.");
            return;
        }

        boolean pathAddedToCurrent = false;
        if (!this.getPaths().containsKey(city)) {
            super.addPath(city, cost);
            pathAddedToCurrent = true;
        } else {
             System.out.println("Информация (BidirectionalCity): путь из " + this.getName()
                     + " в " + city.getName() + " уже существует.");
        }

        if (!city.getPaths().containsKey(this)) {
            city.addPath(this, cost);
        } else if (pathAddedToCurrent && city.getPaths().get(this) != cost) {
            System.out.println("Предупреждение (BidirectionalCity): обнаружен существующий " +
                    "обратный путь с другой стоимостью. " + "Стоимость обратного пути из "
                    + city.getName() + " в " + this.getName() + " может отличаться.");
        }
    }

    /**
     * Удаляет двусторонний путь между текущим городом и указанным городом.
     * Если удаляется путь из A в B, то также удаляется путь из B в A.
     * @param city Город, связь с которым нужно разорвать.
     */
    @Override
    public void removePath(City city) {
        if (city == null) {
            System.out.println("Информация (BidirectionalCity): город для удаления пути не указан (null).");
            return;
        }

        boolean pathRemovedFromCurrent = false;
        if (this.getPaths().containsKey(city)) {
            super.removePath(city);
            pathRemovedFromCurrent = true;
        } else {
             System.out.println("Информация (BidirectionalCity): путь из " + this.getName()
                     + " в " + city.getName() + " не существует. Удаление не требуется.");
        }

        if (city.getPaths().containsKey(this)) {
            city.removePath(this);
        } else if (pathRemovedFromCurrent) {
             System.out.println("Информация (BidirectionalCity): обратный путь из " + city.getName()
                     + " в " + this.getName() + " также не существует.");
        }
    }
}